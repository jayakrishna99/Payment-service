package com.hyperswitch.core.authentication.impl;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.authentication.AuthenticationService;
import com.hyperswitch.storage.entity.AuthenticationEntity;
import com.hyperswitch.storage.repository.AuthenticationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

/**
 * Implementation of AuthenticationService
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    
    private final AuthenticationRepository authenticationRepository;
    
    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }
    
    @Override
    public Mono<Result<AuthenticationResponse, PaymentError>> createAuthentication(
            String merchantId,
            AuthenticationCreateRequest request) {
        log.info("Creating authentication for merchant: {}, payment method: {}", merchantId, request.getPaymentMethodId());
        
        String authenticationId = "auth_" + UUID.randomUUID().toString().replace("-", "");
        Instant now = Instant.now();
        
        AuthenticationEntity entity = new AuthenticationEntity();
        entity.setAuthenticationId(authenticationId);
        entity.setMerchantId(merchantId);
        entity.setPaymentMethodId(request.getPaymentMethodId());
        entity.setPaymentId(request.getPaymentId());
        entity.setAuthenticationConnector(request.getAuthenticationConnector());
        entity.setAuthenticationType(request.getAuthenticationType());
        entity.setAuthenticationData(request.getAuthenticationData());
        entity.setAuthenticationStatus("PENDING");
        entity.setAuthenticationLifecycleStatus("PENDING");
        entity.setCreatedAt(now);
        entity.setModifiedAt(now);
        
        return authenticationRepository.save(entity)
            .map(this::toAuthenticationResponse)
            .map(Result::<AuthenticationResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error creating authentication", error);
                return Mono.just(Result.<AuthenticationResponse, PaymentError>err(
                    PaymentError.of("AUTHENTICATION_CREATE_FAILED",
                        "Failed to create authentication: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<AuthenticationEligibilityResponse, PaymentError>> checkEligibility(
            String authenticationId,
            String merchantId,
            AuthenticationEligibilityRequest request) {
        log.info("Checking eligibility for authentication: {} for merchant: {}", authenticationId, merchantId);
        
        return authenticationRepository.findByAuthenticationIdAndMerchantId(authenticationId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("Authentication not found")))
            .map(entity -> {
                AuthenticationEligibilityResponse response = new AuthenticationEligibilityResponse();
                response.setEligible(true);
                response.setEligibilityCheckId("elig_check_" + UUID.randomUUID().toString().replace("-", ""));
                response.setAvailableMethods(java.util.Arrays.asList("OTP", "BIOMETRIC", "PUSH_NOTIFICATION"));
                response.setReason("Authentication is eligible");
                response.setMetadata(new HashMap<>());
                return Result.<AuthenticationEligibilityResponse, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error checking eligibility", error);
                return Mono.just(Result.<AuthenticationEligibilityResponse, PaymentError>err(
                    PaymentError.of("ELIGIBILITY_CHECK_FAILED",
                        "Failed to check eligibility: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<AuthenticationAuthenticateResponse, PaymentError>> authenticate(
            String authenticationId,
            String merchantId,
            AuthenticationAuthenticateRequest request) {
        log.info("Authenticating payment for authentication: {} for merchant: {}", authenticationId, merchantId);
        
        return authenticationRepository.findByAuthenticationIdAndMerchantId(authenticationId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("Authentication not found")))
            .flatMap(entity -> {
                // Update authentication status
                entity.setAuthenticationStatus("IN_PROGRESS");
                entity.setAuthenticationLifecycleStatus("IN_PROGRESS");
                entity.setAuthenticationData(request.getAuthenticationData());
                entity.setModifiedAt(Instant.now());
                
                return authenticationRepository.save(entity)
                    .map(savedEntity -> {
                        AuthenticationAuthenticateResponse response = new AuthenticationAuthenticateResponse();
                        response.setAuthenticationId(savedEntity.getAuthenticationId());
                        response.setAuthenticationStatus(savedEntity.getAuthenticationStatus());
                        response.setAuthenticationLifecycleStatus(savedEntity.getAuthenticationLifecycleStatus());
                        response.setMetadata(new HashMap<>());
                        return Result.<AuthenticationAuthenticateResponse, PaymentError>ok(response);
                    });
            })
            .onErrorResume(error -> {
                log.error("Error authenticating", error);
                return Mono.just(Result.<AuthenticationAuthenticateResponse, PaymentError>err(
                    PaymentError.of("AUTHENTICATION_FAILED",
                        "Failed to authenticate: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<AuthenticationEligibilityResponse, PaymentError>> eligibilityCheck(
            String authenticationId,
            String merchantId,
            AuthenticationEligibilityRequest request) {
        log.info("Eligibility check for authentication: {} for merchant: {}", authenticationId, merchantId);
        // Similar to checkEligibility
        return checkEligibility(authenticationId, merchantId, request);
    }
    
    @Override
    public Mono<Result<AuthenticationEligibilityResponse, PaymentError>> retrieveEligibilityCheck(
            String eligibilityCheckId,
            String merchantId) {
        log.info("Retrieving eligibility check: {} for merchant: {}", eligibilityCheckId, merchantId);
        
        // In production, this would retrieve from a separate eligibility check table
        AuthenticationEligibilityResponse response = new AuthenticationEligibilityResponse();
        response.setEligible(true);
        response.setEligibilityCheckId(eligibilityCheckId);
        response.setAvailableMethods(java.util.Arrays.asList("OTP", "BIOMETRIC"));
        response.setReason("Eligibility check retrieved");
        response.setMetadata(new HashMap<>());
        
        return Mono.just(Result.<AuthenticationEligibilityResponse, PaymentError>ok(response));
    }
    
    @Override
    public Mono<Result<AuthenticationResponse, PaymentError>> syncAuthentication(
            String authenticationId,
            String merchantId,
            AuthenticationSyncRequest request) {
        log.info("Syncing authentication: {} for merchant: {}", authenticationId, merchantId);
        
        return authenticationRepository.findByAuthenticationIdAndMerchantId(authenticationId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("Authentication not found")))
            .flatMap(entity -> {
                // In production, this would sync with the connector
                entity.setModifiedAt(Instant.now());
                return authenticationRepository.save(entity)
                    .map(this::toAuthenticationResponse)
                    .map(Result::<AuthenticationResponse, PaymentError>ok);
            })
            .onErrorResume(error -> {
                log.error("Error syncing authentication", error);
                return Mono.just(Result.<AuthenticationResponse, PaymentError>err(
                    PaymentError.of("SYNC_FAILED",
                        "Failed to sync authentication: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<AuthenticationResponse, PaymentError>> syncAuthenticationPostUpdate(
            String authenticationId,
            String merchantId,
            AuthenticationSyncRequest request) {
        log.info("Syncing authentication post update: {} for merchant: {}", authenticationId, merchantId);
        // Similar to syncAuthentication
        return syncAuthentication(authenticationId, merchantId, request);
    }
    
    @Override
    public Mono<Result<AuthenticationSessionTokenResponse, PaymentError>> getSessionToken(
            String authenticationId,
            String merchantId,
            AuthenticationSessionTokenRequest request) {
        log.info("Getting session token for authentication: {} for merchant: {}", authenticationId, merchantId);
        
        return authenticationRepository.findByAuthenticationIdAndMerchantId(authenticationId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("Authentication not found")))
            .map(entity -> {
                AuthenticationSessionTokenResponse response = new AuthenticationSessionTokenResponse();
                response.setSessionToken("session_token_" + UUID.randomUUID().toString().replace("-", ""));
                response.setExpiresIn(3600);
                response.setMetadata(new HashMap<>());
                return Result.<AuthenticationSessionTokenResponse, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error getting session token", error);
                return Mono.just(Result.<AuthenticationSessionTokenResponse, PaymentError>err(
                    PaymentError.of("SESSION_TOKEN_FAILED",
                        "Failed to get session token: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<AuthenticationResponse, PaymentError>> getAuthentication(
            String authenticationId,
            String merchantId) {
        log.info("Getting authentication: {} for merchant: {}", authenticationId, merchantId);
        
        return authenticationRepository.findByAuthenticationIdAndMerchantId(authenticationId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("Authentication not found")))
            .map(this::toAuthenticationResponse)
            .map(Result::<AuthenticationResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error getting authentication", error);
                return Mono.just(Result.<AuthenticationResponse, PaymentError>err(
                    PaymentError.of("AUTHENTICATION_NOT_FOUND",
                        "Authentication not found: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<AuthenticationResponse, PaymentError>> redirect(
            String authenticationId,
            String merchantId,
            java.util.Map<String, String> queryParams) {
        log.info("Handling redirect for authentication: {} for merchant: {}", authenticationId, merchantId);
        
        return authenticationRepository.findByAuthenticationIdAndMerchantId(authenticationId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("Authentication not found")))
            .flatMap(entity -> {
                // In production, this would process redirect parameters and update authentication
                entity.setModifiedAt(Instant.now());
                return authenticationRepository.save(entity)
                    .map(this::toAuthenticationResponse)
                    .map(Result::<AuthenticationResponse, PaymentError>ok);
            })
            .onErrorResume(error -> {
                log.error("Error handling redirect", error);
                return Mono.just(Result.<AuthenticationResponse, PaymentError>err(
                    PaymentError.of("REDIRECT_FAILED",
                        "Failed to handle redirect: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Convert AuthenticationEntity to AuthenticationResponse
     */
    private AuthenticationResponse toAuthenticationResponse(AuthenticationEntity entity) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAuthenticationId(entity.getAuthenticationId());
        response.setMerchantId(entity.getMerchantId());
        response.setPaymentMethodId(entity.getPaymentMethodId());
        response.setPaymentId(entity.getPaymentId());
        response.setAuthenticationConnector(entity.getAuthenticationConnector());
        response.setConnectorAuthenticationId(entity.getConnectorAuthenticationId());
        response.setAuthenticationStatus(entity.getAuthenticationStatus());
        response.setAuthenticationLifecycleStatus(entity.getAuthenticationLifecycleStatus());
        response.setAuthenticationType(entity.getAuthenticationType());
        response.setAuthenticationData(entity.getAuthenticationData());
        response.setErrorMessage(entity.getErrorMessage());
        response.setErrorCode(entity.getErrorCode());
        response.setCreatedAt(entity.getCreatedAt());
        response.setModifiedAt(entity.getModifiedAt());
        response.setThreeDsMethodUrl(entity.getThreeDsMethodUrl());
        response.setAcsUrl(entity.getAcsUrl());
        response.setChallengeRequest(entity.getChallengeRequest());
        response.setMetadata(new HashMap<>());
        return response;
    }
}

