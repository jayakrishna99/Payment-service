package com.hyperswitch.core.mandates.impl;

import com.hyperswitch.common.dto.MandateRequest;
import com.hyperswitch.common.dto.MandateResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.MandateId;
import com.hyperswitch.common.types.MandateStatus;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.mandates.MandateService;
import com.hyperswitch.storage.entity.MandateEntity;
import com.hyperswitch.storage.repository.MandateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Implementation of MandateService
 */
@Service
public class MandateServiceImpl implements MandateService {

    private static final Logger log = LoggerFactory.getLogger(MandateServiceImpl.class);
    private static final String MANDATE_NOT_FOUND_MSG = "Mandate not found";

    private final MandateRepository mandateRepository;
    private final MandateMapper mandateMapper;
    private final com.hyperswitch.core.metrics.PaymentMetrics paymentMetrics;

    @Autowired
    public MandateServiceImpl(
            MandateRepository mandateRepository,
            MandateMapper mandateMapper,
            com.hyperswitch.core.metrics.PaymentMetrics paymentMetrics) {
        this.mandateRepository = mandateRepository;
        this.mandateMapper = mandateMapper;
        this.paymentMetrics = paymentMetrics;
    }

    @Override
    public Mono<Result<MandateResponse, PaymentError>> createMandate(String merchantId, MandateRequest request) {
        log.info("Creating mandate for customer: {}, merchant: {}", request.getCustomerId(), merchantId);
        
        return Mono.fromCallable(() -> {
            MandateId mandateId = MandateId.generate();
            Instant now = Instant.now();
            
            Long mandateAmount = null;
            String mandateCurrency = null;
            if (request.getMandateAmount() != null) {
                // Convert Amount to minor units (cents)
                mandateAmount = request.getMandateAmount().getValue()
                    .multiply(java.math.BigDecimal.valueOf(100))
                    .longValue();
                mandateCurrency = request.getMandateAmount().getCurrencyCode();
            }
            
            return MandateEntity.builder()
                .mandateId(mandateId.getValue())
                .customerId(request.getCustomerId())
                .merchantId(merchantId)
                .paymentMethodId(request.getPaymentMethodId())
                .mandateStatus(MandateStatus.PENDING)
                .mandateType(request.getMandateType())
                .mandateAmount(mandateAmount)
                .mandateCurrency(mandateCurrency)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .metadata(request.getMetadata())
                .connector("stripe") // Default connector, should be determined by routing
                .createdAt(now)
                .build();
        })
        .flatMap(mandateRepository::save)
        .map(saved -> {
            MandateResponse response = mandateMapper.toMandateResponse(saved);
            paymentMetrics.incrementMandateCreated();
            return Result.<MandateResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating mandate", error);
            return Mono.just(Result.<MandateResponse, PaymentError>err(PaymentError.of(
                "MANDATE_CREATE_FAILED",
                "Failed to create mandate: " + error.getMessage()
            )));
        });
    }

    @Override
    public Mono<Result<MandateResponse, PaymentError>> getMandate(MandateId mandateId) {
        log.info("Getting mandate: {}", mandateId);
        
        return mandateRepository.findByMandateId(mandateId.getValue())
            .map(mandateMapper::toMandateResponse)
            .map(Result::<MandateResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<MandateResponse, PaymentError>err(
                PaymentError.of("MANDATE_NOT_FOUND", MANDATE_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting mandate", error);
                return Mono.just(Result.<MandateResponse, PaymentError>err(PaymentError.of(
                    "MANDATE_GET_FAILED",
                    "Failed to get mandate: " + error.getMessage()
                )));
            });
    }

    @Override
    public Flux<MandateResponse> listCustomerMandates(String customerId) {
        log.info("Listing mandates for customer: {}", customerId);
        
        return mandateRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
            .map(mandateMapper::toMandateResponse)
            .onErrorResume(error -> {
                log.error("Error listing customer mandates", error);
                return Flux.empty();
            });
    }

    @Override
    public Mono<Result<MandateResponse, PaymentError>> revokeMandate(MandateId mandateId) {
        log.info("Revoking mandate: {}", mandateId);
        
        return mandateRepository.findByMandateId(mandateId.getValue())
            .flatMap(existing -> {
                existing.setMandateStatus(MandateStatus.REVOKED);
                existing.setUpdatedBy("system"); // Should be from auth context
                return mandateRepository.save(existing);
            })
            .map(mandateMapper::toMandateResponse)
            .map(response -> {
                paymentMetrics.incrementMandateRevoked();
                return Result.<MandateResponse, PaymentError>ok(response);
            })
            .switchIfEmpty(Mono.just(Result.<MandateResponse, PaymentError>err(
                PaymentError.of("MANDATE_NOT_FOUND", MANDATE_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error revoking mandate", error);
                return Mono.just(Result.<MandateResponse, PaymentError>err(PaymentError.of(
                    "MANDATE_REVOKE_FAILED",
                    "Failed to revoke mandate: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<MandateResponse, PaymentError>> getActiveMandate(String customerId, String paymentMethodId) {
        log.info("Getting active mandate for customer: {}, payment method: {}", customerId, paymentMethodId);
        
        return mandateRepository.findActiveMandateByCustomerAndPaymentMethod(customerId, paymentMethodId)
            .filter(mandate -> !isMandateExpired(mandate)) // Exclude expired mandates
            .map(mandateMapper::toMandateResponse)
            .map(Result::<MandateResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<MandateResponse, PaymentError>err(
                PaymentError.of("MANDATE_NOT_FOUND", "No active mandate found")
            )))
            .onErrorResume(error -> {
                log.error("Error getting active mandate", error);
                return Mono.just(Result.<MandateResponse, PaymentError>err(PaymentError.of(
                    "MANDATE_GET_FAILED",
                    "Failed to get active mandate: " + error.getMessage()
                )));
            });
    }
    
    @Override
    public Mono<Integer> expireMandates() {
        log.info("Checking for expired mandates");
        Instant now = Instant.now();
        
        return mandateRepository.findAll()
            .filter(mandate -> 
                mandate.getMandateStatus() == MandateStatus.ACTIVE && 
                isMandateExpired(mandate)
            )
            .flatMap(mandate -> {
                log.info("Expiring mandate: {} (end date: {})", mandate.getMandateId(), mandate.getEndDate());
                mandate.setMandateStatus(MandateStatus.INACTIVE);
                mandate.setUpdatedBy("system");
                return mandateRepository.save(mandate);
            })
            .count()
            .map(Long::intValue)
            .doOnNext(count -> {
                if (count > 0) {
                    log.info("Expired {} mandate(s)", count);
                }
            })
            .onErrorResume(error -> {
                log.error("Error expiring mandates", error);
                return Mono.just(0);
            });
    }
    
    @Override
    public boolean isMandateExpired(MandateEntity mandate) {
        if (mandate.getEndDate() == null) {
            // Mandates without end date don't expire
            return false;
        }
        
        Instant now = Instant.now();
        boolean expired = mandate.getEndDate().isBefore(now);
        
        // Also check if mandate hasn't started yet
        if (mandate.getStartDate() != null && mandate.getStartDate().isAfter(now)) {
            return false; // Not started yet, not expired
        }
        
        return expired;
    }
}

