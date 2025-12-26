package com.hyperswitch.core.fraudcheck.impl;

import com.hyperswitch.common.dto.FraudCheckResponse;
import com.hyperswitch.common.dto.CreatePaymentRequest;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.FraudCheckStatus;
import com.hyperswitch.common.types.FraudCheckType;
import com.hyperswitch.core.fraudcheck.FraudCheckService;
import com.hyperswitch.core.fraudcheck.FraudRulesEngine;
import com.hyperswitch.storage.entity.FraudCheckEntity;
import com.hyperswitch.storage.repository.FraudCheckRepository;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of FraudCheckService
 */
@Service
public class FraudCheckServiceImpl implements FraudCheckService {

    private static final Logger log = LoggerFactory.getLogger(FraudCheckServiceImpl.class);
    private static final String FRAUD_CHECK_NOT_FOUND = "Fraud check not found";

    private final FraudCheckRepository fraudCheckRepository;
    private final FraudRulesEngine fraudRulesEngine;

    @Autowired
    public FraudCheckServiceImpl(
            FraudCheckRepository fraudCheckRepository,
            FraudRulesEngine fraudRulesEngine) {
        this.fraudCheckRepository = fraudCheckRepository;
        this.fraudRulesEngine = fraudRulesEngine;
    }

    @Override
    public Mono<Either<PaymentError, FraudCheckResponse>> performFraudCheck(
        String merchantId,
        String paymentId,
        String attemptId,
        String frmName,
        FraudCheckType fraudCheckType
    ) {
        return performFraudCheck(merchantId, paymentId, attemptId, frmName, fraudCheckType, null, null);
    }
    
    /**
     * Perform fraud check with payment request and metadata for risk scoring
     */
    public Mono<Either<PaymentError, FraudCheckResponse>> performFraudCheck(
        String merchantId,
        String paymentId,
        String attemptId,
        String frmName,
        FraudCheckType fraudCheckType,
        CreatePaymentRequest paymentRequest,
        Map<String, Object> paymentMetadata
    ) {
        log.info("Performing fraud check for payment: {}, attempt: {}, merchant: {}", paymentId, attemptId, merchantId);
        
        return Mono.defer(() -> {
            String frmId = "frm_" + UUID.randomUUID().toString().replace("-", "");
            Instant now = Instant.now();
            
            // Calculate risk score if payment request is provided
            Integer riskScore = null;
            FraudCheckStatus initialStatus = FraudCheckStatus.PENDING;
            String reason = null;
            
            if (paymentRequest != null && fraudRulesEngine != null) {
                riskScore = fraudRulesEngine.calculateRiskScore(paymentRequest, paymentMetadata);
                
                if (fraudRulesEngine.shouldBlockPayment(riskScore, merchantId)) {
                    initialStatus = FraudCheckStatus.FRAUD;
                    reason = "Payment blocked due to high risk score: " + riskScore;
                } else if (fraudRulesEngine.requiresManualReview(riskScore, merchantId)) {
                    initialStatus = FraudCheckStatus.MANUAL_REVIEW;
                    reason = "Payment requires manual review. Risk score: " + riskScore;
                } else {
                    initialStatus = FraudCheckStatus.LEGIT;
                    reason = "Payment approved. Risk score: " + riskScore;
                }
            }
            
            FraudCheckEntity entity = new FraudCheckEntity();
            entity.setFrmId(frmId);
            entity.setPaymentId(paymentId);
            entity.setMerchantId(merchantId);
            entity.setAttemptId(attemptId);
            entity.setFrmName(frmName);
            entity.setFrmTransactionType(fraudCheckType.name());
            entity.setFrmStatus(initialStatus.name());
            entity.setFrmScore(riskScore);
            entity.setFrmReason(reason);
            entity.setCreatedAt(now);
            entity.setModifiedAt(now);
            
            return fraudCheckRepository.save(entity)
                .map(savedEntity -> FraudCheckMapper.toFraudCheckResponse(savedEntity))
                .map(response -> Either.<PaymentError, FraudCheckResponse>right(response))
                .onErrorResume(error -> {
                    log.error("Error performing fraud check", error);
                    return Mono.just(Either.left(PaymentError.of("FRAUD_CHECK_FAILED", error.getMessage())));
                });
        });
    }

    @Override
    public Mono<Either<PaymentError, FraudCheckResponse>> getFraudCheck(String merchantId, String frmId) {
        log.info("Retrieving fraud check: {} for merchant: {}", frmId, merchantId);
        
        return fraudCheckRepository.findByFrmId(frmId)
            .filter(entity -> entity.getMerchantId().equals(merchantId))
            .map(entity -> FraudCheckMapper.toFraudCheckResponse(entity))
            .map(response -> Either.<PaymentError, FraudCheckResponse>right(response))
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", FRAUD_CHECK_NOT_FOUND))));
    }

    @Override
    public Flux<FraudCheckResponse> getFraudChecksByPayment(String merchantId, String paymentId) {
        log.info("Listing fraud checks for payment: {} and merchant: {}", paymentId, merchantId);
        
        return fraudCheckRepository.findByMerchantIdAndPaymentId(merchantId, paymentId)
            .map(entity -> FraudCheckMapper.toFraudCheckResponse(entity));
    }

    @Override
    public Mono<Either<PaymentError, FraudCheckResponse>> updateFraudCheckStatus(
        String merchantId,
        String frmId,
        FraudCheckStatus status,
        Integer score,
        String reason
    ) {
        log.info("Updating fraud check status: {} for merchant: {}", frmId, merchantId);
        
        return fraudCheckRepository.findByFrmId(frmId)
            .filter(entity -> entity.getMerchantId().equals(merchantId))
            .flatMap(entity -> {
                entity.setFrmStatus(status.name());
                if (score != null) {
                    entity.setFrmScore(score);
                }
                if (reason != null) {
                    entity.setFrmReason(reason);
                }
                entity.setModifiedAt(Instant.now());
                return fraudCheckRepository.save(entity);
            })
            .map(entity -> FraudCheckMapper.toFraudCheckResponse(entity))
            .map(response -> Either.<PaymentError, FraudCheckResponse>right(response))
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", FRAUD_CHECK_NOT_FOUND))));
    }
}

