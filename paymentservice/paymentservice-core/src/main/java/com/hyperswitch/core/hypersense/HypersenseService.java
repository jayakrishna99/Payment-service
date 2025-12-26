package com.hyperswitch.core.hypersense;

import com.hyperswitch.common.dto.HypersenseTokenResponse;
import com.hyperswitch.common.dto.VerifyTokenRequest;
import com.hyperswitch.common.dto.VerifyTokenResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for Hypersense token operations
 */
public interface HypersenseService {
    
    /**
     * Get Hypersense token
     */
    Mono<Result<HypersenseTokenResponse, PaymentError>> getToken(
            String merchantId);
    
    /**
     * Verify Hypersense token
     */
    Mono<Result<VerifyTokenResponse, PaymentError>> verifyToken(
            String merchantId,
            VerifyTokenRequest request);
    
    /**
     * Sign out Hypersense token
     */
    Mono<Result<Void, PaymentError>> signOut(
            String merchantId,
            String token);
}

