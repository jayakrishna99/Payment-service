package com.hyperswitch.core.oidc;

import com.hyperswitch.common.dto.JwksResponse;
import com.hyperswitch.common.dto.OidcDiscoveryResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for OIDC operations
 */
public interface OidcService {
    
    /**
     * Get OIDC discovery configuration
     */
    Mono<Result<OidcDiscoveryResponse, PaymentError>> getDiscovery();
    
    /**
     * Get JWKS (JSON Web Key Set)
     */
    Mono<Result<JwksResponse, PaymentError>> getJwks();
}

