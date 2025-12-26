package com.hyperswitch.core.authentication;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for authentication operations
 */
public interface AuthenticationService {
    
    /**
     * Create authentication
     */
    Mono<Result<AuthenticationResponse, PaymentError>> createAuthentication(
            String merchantId,
            AuthenticationCreateRequest request);
    
    /**
     * Check authentication eligibility
     */
    Mono<Result<AuthenticationEligibilityResponse, PaymentError>> checkEligibility(
            String authenticationId,
            String merchantId,
            AuthenticationEligibilityRequest request);
    
    /**
     * Authenticate payment
     */
    Mono<Result<AuthenticationAuthenticateResponse, PaymentError>> authenticate(
            String authenticationId,
            String merchantId,
            AuthenticationAuthenticateRequest request);
    
    /**
     * Eligibility check
     */
    Mono<Result<AuthenticationEligibilityResponse, PaymentError>> eligibilityCheck(
            String authenticationId,
            String merchantId,
            AuthenticationEligibilityRequest request);
    
    /**
     * Retrieve eligibility check
     */
    Mono<Result<AuthenticationEligibilityResponse, PaymentError>> retrieveEligibilityCheck(
            String eligibilityCheckId,
            String merchantId);
    
    /**
     * Sync authentication
     */
    Mono<Result<AuthenticationResponse, PaymentError>> syncAuthentication(
            String authenticationId,
            String merchantId,
            AuthenticationSyncRequest request);
    
    /**
     * Sync authentication post update
     */
    Mono<Result<AuthenticationResponse, PaymentError>> syncAuthenticationPostUpdate(
            String authenticationId,
            String merchantId,
            AuthenticationSyncRequest request);
    
    /**
     * Get authentication session token
     */
    Mono<Result<AuthenticationSessionTokenResponse, PaymentError>> getSessionToken(
            String authenticationId,
            String merchantId,
            AuthenticationSessionTokenRequest request);
    
    /**
     * Retrieve authentication
     */
    Mono<Result<AuthenticationResponse, PaymentError>> getAuthentication(
            String authenticationId,
            String merchantId);
    
    /**
     * Authentication redirect
     */
    Mono<Result<AuthenticationResponse, PaymentError>> redirect(
            String authenticationId,
            String merchantId,
            java.util.Map<String, String> queryParams);
}

