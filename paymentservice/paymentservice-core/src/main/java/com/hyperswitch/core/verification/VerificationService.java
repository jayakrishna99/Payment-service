package com.hyperswitch.core.verification;

import com.hyperswitch.common.dto.ApplePayMerchantResponse;
import com.hyperswitch.common.dto.ApplePayMerchantVerificationRequest;
import com.hyperswitch.common.dto.ApplePayVerifiedDomainsResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for verification operations
 */
public interface VerificationService {
    
    /**
     * Register Apple Pay merchant
     */
    Mono<Result<ApplePayMerchantResponse, PaymentError>> registerApplePayMerchant(
            String merchantId,
            String profileId,
            ApplePayMerchantVerificationRequest request);
    
    /**
     * Get Apple Pay merchant registration
     */
    Mono<Result<ApplePayMerchantResponse, PaymentError>> getApplePayMerchantRegistration(
            String merchantId);
    
    /**
     * Retrieve Apple Pay verified domains
     */
    Mono<Result<ApplePayVerifiedDomainsResponse, PaymentError>> getApplePayVerifiedDomains(
            String merchantId,
            String merchantConnectorAccountId);
}

