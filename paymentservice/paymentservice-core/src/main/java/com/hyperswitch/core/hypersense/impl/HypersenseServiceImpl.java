package com.hyperswitch.core.hypersense.impl;

import com.hyperswitch.common.dto.HypersenseTokenResponse;
import com.hyperswitch.common.dto.VerifyTokenRequest;
import com.hyperswitch.common.dto.VerifyTokenResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.hypersense.HypersenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

/**
 * Implementation of HypersenseService
 */
@Service
public class HypersenseServiceImpl implements HypersenseService {
    
    private static final Logger log = LoggerFactory.getLogger(HypersenseServiceImpl.class);
    
    @Override
    public Mono<Result<HypersenseTokenResponse, PaymentError>> getToken(
            String merchantId) {
        
        log.info("Getting Hypersense token for merchant: {}", merchantId);
        
        return Mono.fromCallable(() -> {
            String token = generateToken(merchantId);
            
            HypersenseTokenResponse response = new HypersenseTokenResponse();
            response.setToken(token);
            response.setTokenType("Bearer");
            response.setExpiresAt(Instant.now().plusSeconds(3600)); // 1 hour expiry
            
            // In production, this would:
            // 1. Authenticate user credentials
            // 2. Generate JWT token with proper claims
            // 3. Store token in cache/database
            // 4. Return token with expiry
            
            return Result.<HypersenseTokenResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting Hypersense token: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("TOKEN_GENERATION_FAILED",
                "Failed to generate token: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<VerifyTokenResponse, PaymentError>> verifyToken(
            String merchantId,
            VerifyTokenRequest request) {
        
        log.info("Verifying Hypersense token for merchant: {}", merchantId);
        
        return Mono.fromCallable(() -> {
            if (request.getToken() == null || request.getToken().isEmpty()) {
                VerifyTokenResponse response = new VerifyTokenResponse();
                response.setValid(Boolean.FALSE);
                response.setMessage("Token is required");
                return Result.<VerifyTokenResponse, PaymentError>ok(response);
            }
            
            // In production, this would:
            // 1. Validate JWT token signature
            // 2. Check token expiry
            // 3. Verify token against stored tokens
            // 4. Check if token is revoked
            
            VerifyTokenResponse response = new VerifyTokenResponse();
            response.setValid(Boolean.TRUE);
            response.setMessage("Token is valid");
            
            return Result.<VerifyTokenResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error verifying token: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("TOKEN_VERIFICATION_FAILED",
                "Failed to verify token: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> signOut(
            String merchantId,
            String token) {
        
        log.info("Signing out Hypersense token for merchant: {}", merchantId);
        
        return Mono.fromCallable(() -> {
            // In production, this would:
            // 1. Revoke token in cache/database
            // 2. Add token to blacklist
            // 3. Invalidate session
            
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error signing out: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SIGNOUT_FAILED",
                "Failed to sign out: " + error.getMessage())));
        });
    }
    
    private String generateToken(String merchantId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String tokenData = merchantId + ":" + uuid + ":" + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }
}

