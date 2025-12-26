package com.hyperswitch.core.paymentlinks;

import com.hyperswitch.common.dto.PaymentLinkRequest;
import com.hyperswitch.common.dto.PaymentLinkResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.PaymentLinkId;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for managing payment links
 */
public interface PaymentLinkService {
    
    /**
     * Create a new payment link
     */
    Mono<Result<PaymentLinkResponse, PaymentError>> createPaymentLink(PaymentLinkRequest request);
    
    /**
     * Get payment link by ID
     */
    Mono<Result<PaymentLinkResponse, PaymentError>> getPaymentLink(PaymentLinkId paymentLinkId);
    
    /**
     * Get payment link by payment ID
     */
    Mono<Result<PaymentLinkResponse, PaymentError>> getPaymentLinkByPaymentId(String paymentId);
    
    /**
     * List payment links for a merchant
     */
    Flux<PaymentLinkResponse> listPaymentLinks(String merchantId, int page, int size);
    
    /**
     * Validate payment link (check if expired, etc.)
     */
    Mono<Result<PaymentLinkResponse, PaymentError>> validatePaymentLink(PaymentLinkId paymentLinkId);
}

