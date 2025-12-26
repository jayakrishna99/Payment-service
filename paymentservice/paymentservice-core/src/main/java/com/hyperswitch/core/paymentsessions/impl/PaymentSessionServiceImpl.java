package com.hyperswitch.core.paymentsessions.impl;

import com.hyperswitch.common.dto.CreatePaymentSessionRequest;
import com.hyperswitch.common.dto.PaymentSessionResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Amount;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.common.types.SessionStatus;
import com.hyperswitch.core.paymentsessions.PaymentSessionService;
import com.hyperswitch.storage.entity.PaymentSessionEntity;
import com.hyperswitch.storage.repository.PaymentSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of PaymentSessionService
 */
@Service
public class PaymentSessionServiceImpl implements PaymentSessionService {

    private static final Logger log = LoggerFactory.getLogger(PaymentSessionServiceImpl.class);
    private static final int DEFAULT_SESSION_EXPIRY_HOURS = 24;

    private final PaymentSessionRepository sessionRepository;

    @Autowired
    public PaymentSessionServiceImpl(PaymentSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Mono<Result<PaymentSessionResponse, PaymentError>> createSession(CreatePaymentSessionRequest request) {
        log.info("Creating payment session for merchant: {}", request.getMerchantId());
        
        String sessionId = "sess_" + UUID.randomUUID().toString().replace("-", "");
        String sessionToken = generateSessionToken(sessionId);
        Instant expiresAt = request.getExpiresAt() != null 
            ? request.getExpiresAt() 
            : Instant.now().plusSeconds(DEFAULT_SESSION_EXPIRY_HOURS * 3600);
        
        PaymentSessionEntity entity = new PaymentSessionEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setSessionId(sessionId);
        entity.setMerchantId(request.getMerchantId());
        entity.setStatus(SessionStatus.ACTIVE.name());
        entity.setSessionToken(sessionToken);
        entity.setAmount(convertToMinorUnits(request.getAmount()));
        entity.setCurrency(request.getAmount().getCurrencyCode());
        entity.setCustomerId(request.getCustomerId());
        entity.setPaymentMethodId(request.getPaymentMethodId());
        entity.setMetadata(request.getMetadata());
        entity.setExpiresAt(expiresAt);
        entity.setCreatedAt(Instant.now());
        entity.setModifiedAt(Instant.now());
        
        return sessionRepository.save(entity)
            .map(saved -> {
                PaymentSessionResponse response = new PaymentSessionResponse();
                response.setSessionId(saved.getSessionId());
                response.setPaymentId(saved.getPaymentId());
                response.setStatus(saved.getStatus());
                response.setSessionToken(saved.getSessionToken());
                response.setAmount(Amount.of(
                    java.math.BigDecimal.valueOf(saved.getAmount())
                        .divide(java.math.BigDecimal.valueOf(100)),
                    saved.getCurrency()
                ));
                response.setCustomerId(saved.getCustomerId());
                response.setPaymentMethodId(saved.getPaymentMethodId());
                response.setMetadata(saved.getMetadata());
                response.setExpiresAt(saved.getExpiresAt());
                response.setCreatedAt(saved.getCreatedAt());
                
                return Result.<PaymentSessionResponse, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error creating payment session", error);
                return Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                    PaymentError.of("SESSION_CREATION_FAILED", "Failed to create payment session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentSessionResponse, PaymentError>> getSession(String sessionId, String merchantId) {
        return sessionRepository.findBySessionIdAndMerchantId(sessionId, merchantId)
            .map(entity -> Result.<PaymentSessionResponse, PaymentError>ok(toResponse(entity)))
            .switchIfEmpty(Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                PaymentError.of("SESSION_NOT_FOUND", "Payment session not found: " + sessionId)
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment session: {}", sessionId, error);
                return Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                    PaymentError.of("SESSION_RETRIEVAL_FAILED", "Failed to get payment session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentSessionResponse, PaymentError>> getSessionByToken(String sessionToken) {
        return sessionRepository.findBySessionToken(sessionToken)
            .map(entity -> Result.<PaymentSessionResponse, PaymentError>ok(toResponse(entity)))
            .switchIfEmpty(Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                PaymentError.of("SESSION_NOT_FOUND", "Payment session not found for token")
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment session by token", error);
                return Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                    PaymentError.of("SESSION_RETRIEVAL_FAILED", "Failed to get payment session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentSessionResponse, PaymentError>> completeSession(
            String sessionId,
            String paymentId,
            String merchantId) {
        log.info("Completing payment session: {} with payment: {}", sessionId, paymentId);
        
        return sessionRepository.findBySessionIdAndMerchantId(sessionId, merchantId)
            .flatMap(session -> {
                if (!SessionStatus.ACTIVE.name().equals(session.getStatus())) {
                    return Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                        PaymentError.of("INVALID_SESSION_STATUS", "Session is not active")
                    ));
                }
                
                if (session.getExpiresAt().isBefore(Instant.now())) {
                    session.setStatus(SessionStatus.EXPIRED.name());
                    session.setModifiedAt(Instant.now());
                    return sessionRepository.save(session)
                        .map(s -> Result.<PaymentSessionResponse, PaymentError>err(
                            PaymentError.of("SESSION_EXPIRED", "Payment session has expired")
                        ));
                }
                
                session.setPaymentId(paymentId);
                session.setStatus(SessionStatus.COMPLETED.name());
                session.setModifiedAt(Instant.now());
                
                return sessionRepository.save(session)
                    .map(saved -> Result.<PaymentSessionResponse, PaymentError>ok(toResponse(saved)));
            })
            .switchIfEmpty(Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                PaymentError.of("SESSION_NOT_FOUND", "Payment session not found: " + sessionId)
            )))
            .onErrorResume(error -> {
                log.error("Error completing payment session: {}", sessionId, error);
                return Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                    PaymentError.of("SESSION_COMPLETION_FAILED", "Failed to complete payment session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentSessionResponse, PaymentError>> cancelSession(String sessionId, String merchantId) {
        log.info("Cancelling payment session: {}", sessionId);
        
        return sessionRepository.findBySessionIdAndMerchantId(sessionId, merchantId)
            .flatMap(session -> {
                session.setStatus(SessionStatus.CANCELLED.name());
                session.setModifiedAt(Instant.now());
                
                return sessionRepository.save(session)
                    .map(saved -> Result.<PaymentSessionResponse, PaymentError>ok(toResponse(saved)));
            })
            .switchIfEmpty(Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                PaymentError.of("SESSION_NOT_FOUND", "Payment session not found: " + sessionId)
            )))
            .onErrorResume(error -> {
                log.error("Error cancelling payment session: {}", sessionId, error);
                return Mono.just(Result.<PaymentSessionResponse, PaymentError>err(
                    PaymentError.of("SESSION_CANCELLATION_FAILED", "Failed to cancel payment session: " + error.getMessage())
                ));
            });
    }

    private PaymentSessionResponse toResponse(PaymentSessionEntity entity) {
        PaymentSessionResponse response = new PaymentSessionResponse();
        response.setSessionId(entity.getSessionId());
        response.setPaymentId(entity.getPaymentId());
        response.setStatus(entity.getStatus());
        response.setSessionToken(entity.getSessionToken());
        response.setAmount(Amount.of(
            java.math.BigDecimal.valueOf(entity.getAmount())
                .divide(java.math.BigDecimal.valueOf(100)),
            entity.getCurrency()
        ));
        response.setCustomerId(entity.getCustomerId());
        response.setPaymentMethodId(entity.getPaymentMethodId());
        response.setMetadata(entity.getMetadata());
        response.setExpiresAt(entity.getExpiresAt());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }

    private String generateSessionToken(String sessionId) {
        return "sess_token_" + sessionId + "_" + UUID.randomUUID().toString().replace("-", "");
    }

    private Long convertToMinorUnits(Amount amount) {
        return amount.getValue()
            .multiply(java.math.BigDecimal.valueOf(100))
            .longValue();
    }
}

