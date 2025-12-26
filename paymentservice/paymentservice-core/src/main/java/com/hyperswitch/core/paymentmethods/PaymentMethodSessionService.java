package com.hyperswitch.core.paymentmethods;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Payment Method Session service for v2 API
 */
public interface PaymentMethodSessionService {
    
    /**
     * Create a new payment method session
     */
    Mono<Result<PaymentMethodSessionResponse, PaymentError>> createPaymentMethodSession(
        String merchantId,
        PaymentMethodSessionRequest request
    );
    
    /**
     * Retrieve a payment method session by ID
     */
    Mono<Result<PaymentMethodSessionResponse, PaymentError>> getPaymentMethodSession(
        String merchantId,
        String sessionId
    );
    
    /**
     * Update a payment method session
     */
    Mono<Result<PaymentMethodSessionResponse, PaymentError>> updatePaymentMethodSession(
        String merchantId,
        String sessionId,
        PaymentMethodSessionUpdateRequest request
    );
    
    /**
     * Delete a payment method session
     */
    Mono<Result<Void, PaymentError>> deletePaymentMethodSession(
        String merchantId,
        String sessionId
    );
    
    /**
     * List enabled payment methods for a session
     */
    Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> listPaymentMethodsForSession(
        String merchantId,
        String sessionId
    );
    
    /**
     * Confirm a payment method session
     */
    Mono<Result<PaymentMethodSessionResponse, PaymentError>> confirmPaymentMethodSession(
        String merchantId,
        String sessionId,
        PaymentMethodSessionConfirmRequest request
    );
    
    /**
     * Update a saved payment method in a session
     */
    Mono<Result<PaymentMethodResponse, PaymentError>> updateSavedPaymentMethodInSession(
        String merchantId,
        String sessionId,
        PaymentMethodSessionUpdateSavedPaymentMethodRequest request
    );
    
    /**
     * Delete a saved payment method from a session
     */
    Mono<Result<Void, PaymentError>> deleteSavedPaymentMethodFromSession(
        String merchantId,
        String sessionId,
        PaymentMethodSessionDeleteSavedPaymentMethodRequest request
    );
}

