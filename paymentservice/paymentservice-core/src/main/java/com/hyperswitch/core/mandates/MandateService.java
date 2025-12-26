package com.hyperswitch.core.mandates;

import com.hyperswitch.common.dto.MandateRequest;
import com.hyperswitch.common.dto.MandateResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.MandateId;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for managing mandates and recurring payments
 */
public interface MandateService {
    
    /**
     * Create a new mandate
     */
    Mono<Result<MandateResponse, PaymentError>> createMandate(String merchantId, MandateRequest request);
    
    /**
     * Get mandate by ID
     */
    Mono<Result<MandateResponse, PaymentError>> getMandate(MandateId mandateId);
    
    /**
     * List mandates for a customer
     */
    Flux<MandateResponse> listCustomerMandates(String customerId);
    
    /**
     * Revoke a mandate
     */
    Mono<Result<MandateResponse, PaymentError>> revokeMandate(MandateId mandateId);
    
    /**
     * Get active mandate for customer and payment method
     */
    Mono<Result<MandateResponse, PaymentError>> getActiveMandate(String customerId, String paymentMethodId);
    
    /**
     * Check and expire mandates that have passed their end date
     */
    Mono<Integer> expireMandates();
    
    /**
     * Check if a mandate is expired based on its end date
     */
    boolean isMandateExpired(com.hyperswitch.storage.entity.MandateEntity mandate);
}

