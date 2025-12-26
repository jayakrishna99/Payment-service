package com.hyperswitch.core.paymentsessions;

import com.hyperswitch.common.dto.CreatePaymentSessionRequest;
import com.hyperswitch.common.dto.PaymentSessionResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service for managing payment sessions (v2 API)
 */
public interface PaymentSessionService {
    
    /**
     * Create a new payment session
     */
    Mono<Result<PaymentSessionResponse, PaymentError>> createSession(CreatePaymentSessionRequest request);
    
    /**
     * Get a payment session by session ID
     */
    Mono<Result<PaymentSessionResponse, PaymentError>> getSession(String sessionId, String merchantId);
    
    /**
     * Get a payment session by session token
     */
    Mono<Result<PaymentSessionResponse, PaymentError>> getSessionByToken(String sessionToken);
    
    /**
     * Complete a payment session (link to payment)
     */
    Mono<Result<PaymentSessionResponse, PaymentError>> completeSession(
            String sessionId,
            String paymentId,
            String merchantId);
    
    /**
     * Cancel a payment session
     */
    Mono<Result<PaymentSessionResponse, PaymentError>> cancelSession(String sessionId, String merchantId);
}

