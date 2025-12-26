package com.hyperswitch.core.paymentmethods.impl;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.paymentmethods.PaymentMethodSessionService;
import com.hyperswitch.core.paymentmethods.PaymentMethodService;
import com.hyperswitch.storage.entity.PaymentMethodSessionEntity;
import com.hyperswitch.storage.repository.PaymentMethodSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;

/**
 * Implementation of PaymentMethodSessionService
 */
@Service
public class PaymentMethodSessionServiceImpl implements PaymentMethodSessionService {

    private static final Logger log = LoggerFactory.getLogger(PaymentMethodSessionServiceImpl.class);
    private static final int DEFAULT_SESSION_EXPIRY_SECONDS = 900; // 15 minutes
    private static final String SESSION_NOT_FOUND_MSG = "Payment method session not found";
    private static final String SESSION_EXPIRED_MSG = "Payment method session has expired";

    private final PaymentMethodSessionRepository sessionRepository;
    private final PaymentMethodSessionMapper mapper;
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodSessionServiceImpl(
            PaymentMethodSessionRepository sessionRepository,
            PaymentMethodSessionMapper mapper,
            PaymentMethodService paymentMethodService) {
        this.sessionRepository = sessionRepository;
        this.mapper = mapper;
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public Mono<Result<PaymentMethodSessionResponse, PaymentError>> createPaymentMethodSession(
            String merchantId,
            PaymentMethodSessionRequest request) {
        log.info("Creating payment method session for merchant: {}", merchantId);
        
        String sessionId = "pms_" + UUID.randomUUID().toString().replace("-", "");
        String clientSecret = generateClientSecret(sessionId);
        
        int expiresIn = request.getExpiresIn() != null 
            ? request.getExpiresIn() 
            : DEFAULT_SESSION_EXPIRY_SECONDS;
        Instant expiresAt = Instant.now().plusSeconds(expiresIn);
        
        PaymentMethodSessionEntity entity = new PaymentMethodSessionEntity();
        entity.setId(sessionId);
        entity.setMerchantId(merchantId);
        entity.setCustomerId(request.getCustomerId());
        entity.setBilling(mapper.addressToMap(request.getBilling()));
        entity.setPspTokenization(request.getPspTokenization());
        entity.setNetworkTokenization(request.getNetworkTokenization());
        entity.setTokenizationData(request.getTokenizationData());
        entity.setReturnUrl(request.getReturnUrl());
        entity.setExpiresAt(expiresAt);
        entity.setClientSecret(clientSecret);
        entity.setCreatedAt(Instant.now());
        entity.setModifiedAt(Instant.now());
        
        return sessionRepository.save(entity)
            .map(mapper::toPaymentMethodSessionResponse)
            .map(Result::<PaymentMethodSessionResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error creating payment method session", error);
                return Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_SESSION_CREATE_FAILED", 
                        "Failed to create payment method session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentMethodSessionResponse, PaymentError>> getPaymentMethodSession(
            String merchantId,
            String sessionId) {
        log.info("Getting payment method session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .flatMap(entity -> {
                // Check if session has expired
                if (entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>err(
                        PaymentError.of("SESSION_EXPIRED", SESSION_EXPIRED_MSG)
                    ));
                }
                return Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>ok(
                    mapper.toPaymentMethodSessionResponse(entity)
                ));
            })
            .switchIfEmpty(Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>err(
                PaymentError.of("SESSION_NOT_FOUND", SESSION_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment method session", error);
                return Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_SESSION_GET_FAILED",
                        "Failed to get payment method session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentMethodSessionResponse, PaymentError>> updatePaymentMethodSession(
            String merchantId,
            String sessionId,
            PaymentMethodSessionUpdateRequest request) {
        log.info("Updating payment method session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(SESSION_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Check if session has expired
                if (entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.error(new RuntimeException(SESSION_EXPIRED_MSG));
                }
                
                // Update fields
                if (request.getBilling() != null) {
                    entity.setBilling(mapper.addressToMap(request.getBilling()));
                }
                if (request.getPspTokenization() != null) {
                    entity.setPspTokenization(request.getPspTokenization());
                }
                if (request.getNetworkTokenization() != null) {
                    entity.setNetworkTokenization(request.getNetworkTokenization());
                }
                if (request.getTokenizationData() != null) {
                    entity.setTokenizationData(request.getTokenizationData());
                }
                
                entity.setModifiedAt(Instant.now());
                
                return sessionRepository.save(entity);
            })
            .map(mapper::toPaymentMethodSessionResponse)
            .map(Result::<PaymentMethodSessionResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error updating payment method session", error);
                String errorCode = determineErrorCodeForSessionUpdate(error.getMessage());
                return Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to update payment method session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> deletePaymentMethodSession(
            String merchantId,
            String sessionId) {
        log.info("Deleting payment method session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(SESSION_NOT_FOUND_MSG)))
            .flatMap(sessionRepository::delete)
            .then(Mono.just(Result.<Void, PaymentError>ok(null)))
            .onErrorResume(error -> {
                log.error("Error deleting payment method session", error);
                String errorCode = SESSION_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "SESSION_NOT_FOUND"
                    : "PAYMENT_METHOD_SESSION_DELETE_FAILED";
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to delete payment method session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> listPaymentMethodsForSession(
            String merchantId,
            String sessionId) {
        log.info("Listing payment methods for session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(SESSION_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Check if session has expired
                if (entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.error(new RuntimeException(SESSION_EXPIRED_MSG));
                }
                
                // If customer ID is present, list their payment methods
                if (entity.getCustomerId() != null) {
                    return paymentMethodService.listCustomerPaymentMethods(
                        com.hyperswitch.common.types.CustomerId.of(entity.getCustomerId())
                    );
                }
                
                // Otherwise return empty flux
                return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>ok(Flux.empty()));
            })
            .onErrorResume(error -> {
                log.error("Error listing payment methods for session", error);
                String errorCode = determineErrorCodeForSessionList(error.getMessage());
                return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to list payment methods for session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentMethodSessionResponse, PaymentError>> confirmPaymentMethodSession(
            String merchantId,
            String sessionId,
            PaymentMethodSessionConfirmRequest request) {
        log.info("Confirming payment method session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(SESSION_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Check if session has expired
                if (entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.error(new RuntimeException(SESSION_EXPIRED_MSG));
                }
                
                // Create payment method from session confirmation
                PaymentMethodRequest pmRequest = PaymentMethodRequest.builder()
                    .customerId(entity.getCustomerId() != null 
                        ? com.hyperswitch.common.types.CustomerId.of(entity.getCustomerId())
                        : null)
                    .merchantId(com.hyperswitch.common.types.MerchantId.of(merchantId))
                    .paymentMethodType(request.getPaymentMethodType())
                    .paymentMethodSubtype(request.getPaymentMethodSubtype())
                    .paymentMethodData(request.getPaymentMethodData())
                    .build();
                
                return paymentMethodService.createPaymentMethod(pmRequest)
                    .flatMap(pmResult -> {
                        if (pmResult.isErr()) {
                            return Mono.error(new RuntimeException("Failed to create payment method: " + 
                                pmResult.unwrapErr().getMessage()));
                        }
                        
                        PaymentMethodResponse pmResponse = pmResult.unwrap();
                        
                        // Update session with associated payment method
                        List<String> associatedPms = entity.getAssociatedPaymentMethods();
                        if (associatedPms == null) {
                            associatedPms = new ArrayList<>();
                        }
                        associatedPms.add(pmResponse.getPaymentMethodId().getValue());
                        entity.setAssociatedPaymentMethods(associatedPms);
                        entity.setModifiedAt(Instant.now());
                        
                        return sessionRepository.save(entity);
                    });
            })
            .map(mapper::toPaymentMethodSessionResponse)
            .map(Result::<PaymentMethodSessionResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error confirming payment method session", error);
                String errorCode = determineErrorCodeForSessionConfirm(error.getMessage());
                return Mono.just(Result.<PaymentMethodSessionResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to confirm payment method session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> updateSavedPaymentMethodInSession(
            String merchantId,
            String sessionId,
            PaymentMethodSessionUpdateSavedPaymentMethodRequest request) {
        log.info("Updating saved payment method in session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(SESSION_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Check if session has expired
                if (entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.error(new RuntimeException(SESSION_EXPIRED_MSG));
                }
                
                // Update the payment method
                PaymentMethodRequest pmRequest = PaymentMethodRequest.builder()
                    .paymentMethodData(request.getPaymentMethodData())
                    .networkTransactionId(request.getNetworkTransactionId())
                    .connectorMandateDetails(request.getConnectorMandateDetails())
                    .build();
                
                return paymentMethodService.updatePaymentMethod(
                    com.hyperswitch.common.types.PaymentMethodId.of(request.getPaymentMethodId()),
                    pmRequest
                );
            })
            .flatMap(result -> {
                if (result.isOk()) {
                    return Mono.just(result);
                } else {
                    PaymentError paymentError = result.unwrapErr();
                    return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                        PaymentError.of("PAYMENT_METHOD_UPDATE_FAILED", 
                            "Failed to update payment method: " + paymentError.getMessage())
                    ));
                }
            })
            .onErrorResume(error -> {
                log.error("Error updating saved payment method in session", error);
                String errorCode = determineErrorCodeForSessionUpdate(error.getMessage());
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to update saved payment method in session: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> deleteSavedPaymentMethodFromSession(
            String merchantId,
            String sessionId,
            PaymentMethodSessionDeleteSavedPaymentMethodRequest request) {
        log.info("Deleting saved payment method from session: {} for merchant: {}", sessionId, merchantId);
        
        return sessionRepository.findByIdAndMerchantId(sessionId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(SESSION_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Check if session has expired
                if (entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.error(new RuntimeException(SESSION_EXPIRED_MSG));
                }
                
                // Delete the payment method
                return paymentMethodService.deletePaymentMethod(
                    com.hyperswitch.common.types.PaymentMethodId.of(request.getPaymentMethodId())
                );
            })
            .flatMap(result -> {
                if (result.isOk()) {
                    return Mono.just(Result.<Void, PaymentError>ok(null));
                } else {
                    PaymentError paymentError = result.unwrapErr();
                    return Mono.just(Result.<Void, PaymentError>err(
                        PaymentError.of("PAYMENT_METHOD_DELETE_FAILED", 
                            "Failed to delete payment method: " + paymentError.getMessage())
                    ));
                }
            })
            .onErrorResume(error -> {
                log.error("Error deleting saved payment method from session", error);
                String errorCode = determineErrorCodeForSessionDelete(error.getMessage());
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to delete saved payment method from session: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Generate client secret for payment method session
     */
    private String generateClientSecret(String sessionId) {
        // In production, this should use proper encryption
        return "pms_" + UUID.randomUUID().toString().replace("-", "") + "_" + sessionId.substring(0, 8);
    }
    
    /**
     * Determine error code for session update operations
     */
    private String determineErrorCodeForSessionUpdate(String errorMessage) {
        if (SESSION_NOT_FOUND_MSG.equals(errorMessage)) {
            return "SESSION_NOT_FOUND";
        }
        if (SESSION_EXPIRED_MSG.equals(errorMessage)) {
            return "SESSION_EXPIRED";
        }
        return "PAYMENT_METHOD_SESSION_UPDATE_FAILED";
    }
    
    /**
     * Determine error code for session list operations
     */
    private String determineErrorCodeForSessionList(String errorMessage) {
        if (SESSION_NOT_FOUND_MSG.equals(errorMessage)) {
            return "SESSION_NOT_FOUND";
        }
        if (SESSION_EXPIRED_MSG.equals(errorMessage)) {
            return "SESSION_EXPIRED";
        }
        return "PAYMENT_METHOD_SESSION_LIST_FAILED";
    }
    
    /**
     * Determine error code for session confirm operations
     */
    private String determineErrorCodeForSessionConfirm(String errorMessage) {
        if (SESSION_NOT_FOUND_MSG.equals(errorMessage)) {
            return "SESSION_NOT_FOUND";
        }
        if (SESSION_EXPIRED_MSG.equals(errorMessage)) {
            return "SESSION_EXPIRED";
        }
        return "PAYMENT_METHOD_SESSION_CONFIRM_FAILED";
    }
    
    /**
     * Determine error code for session delete operations
     */
    private String determineErrorCodeForSessionDelete(String errorMessage) {
        if (SESSION_NOT_FOUND_MSG.equals(errorMessage)) {
            return "SESSION_NOT_FOUND";
        }
        if (SESSION_EXPIRED_MSG.equals(errorMessage)) {
            return "SESSION_EXPIRED";
        }
        return "PAYMENT_METHOD_SESSION_DELETE_PM_FAILED";
    }
}

