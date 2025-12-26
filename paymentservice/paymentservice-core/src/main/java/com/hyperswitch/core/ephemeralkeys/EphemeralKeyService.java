package com.hyperswitch.core.ephemeralkeys;

import com.hyperswitch.common.dto.EphemeralKeyRequest;
import com.hyperswitch.common.dto.EphemeralKeyResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service for managing ephemeral keys
 */
public interface EphemeralKeyService {
    
    /**
     * Create an ephemeral key for a customer
     */
    Mono<Result<EphemeralKeyResponse, PaymentError>> createEphemeralKey(
            String merchantId,
            EphemeralKeyRequest request);
    
    /**
     * Delete an ephemeral key
     */
    Mono<Result<EphemeralKeyResponse, PaymentError>> deleteEphemeralKey(
            String merchantId,
            String ephemeralKeyId);
    
    /**
     * Validate an ephemeral key secret
     */
    Mono<Result<EphemeralKeyResponse, PaymentError>> validateEphemeralKey(
            String merchantId,
            String secret);
}

