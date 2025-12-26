package com.hyperswitch.core.connectoronboarding.impl;

import com.hyperswitch.common.dto.ConnectorOnboardingRequest;
import com.hyperswitch.common.dto.ConnectorOnboardingResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.connectoronboarding.ConnectorOnboardingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of ConnectorOnboardingService
 */
@Service
public class ConnectorOnboardingServiceImpl implements ConnectorOnboardingService {
    
    private static final Logger log = LoggerFactory.getLogger(ConnectorOnboardingServiceImpl.class);
    
    @Override
    public Mono<Result<ConnectorOnboardingResponse, PaymentError>> getActionUrl(
            ConnectorOnboardingRequest request) {
        
        log.info("Getting action URL for connector onboarding: connector={}, merchant={}", 
                request.getConnectorId(), request.getMerchantId());
        
        return Mono.fromCallable(() -> {
            String trackingId = request.getTrackingId() != null 
                ? request.getTrackingId() 
                : generateTrackingId();
            
            ConnectorOnboardingResponse response = new ConnectorOnboardingResponse();
            response.setConnectorId(request.getConnectorId());
            response.setMerchantId(request.getMerchantId());
            response.setTrackingId(trackingId);
            response.setActionUrl("https://onboarding." + request.getConnectorId() + ".com/connect?tracking_id=" + trackingId);
            response.setStatus("PENDING");
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            
            // In production, this would:
            // 1. Generate secure onboarding URL
            // 2. Store onboarding session in database
            // 3. Return action URL for redirect
            
            return Result.<ConnectorOnboardingResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting action URL: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("ACTION_URL_GENERATION_FAILED",
                "Failed to generate action URL: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ConnectorOnboardingResponse, PaymentError>> syncOnboarding(
            ConnectorOnboardingRequest request) {
        
        log.info("Syncing onboarding status: connector={}, merchant={}, tracking={}", 
                request.getConnectorId(), request.getMerchantId(), request.getTrackingId());
        
        return Mono.fromCallable(() -> {
            ConnectorOnboardingResponse response = new ConnectorOnboardingResponse();
            response.setConnectorId(request.getConnectorId());
            response.setMerchantId(request.getMerchantId());
            response.setTrackingId(request.getTrackingId());
            response.setStatus("COMPLETED");
            response.setUpdatedAt(Instant.now());
            
            // In production, this would:
            // 1. Query connector API for onboarding status
            // 2. Update local database with status
            // 3. Return updated status
            
            return Result.<ConnectorOnboardingResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error syncing onboarding: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("ONBOARDING_SYNC_FAILED",
                "Failed to sync onboarding status: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ConnectorOnboardingResponse, PaymentError>> resetTrackingId(
            ConnectorOnboardingRequest request) {
        
        log.info("Resetting tracking ID: connector={}, merchant={}", 
                request.getConnectorId(), request.getMerchantId());
        
        return Mono.fromCallable(() -> {
            String newTrackingId = generateTrackingId();
            
            ConnectorOnboardingResponse response = new ConnectorOnboardingResponse();
            response.setConnectorId(request.getConnectorId());
            response.setMerchantId(request.getMerchantId());
            response.setTrackingId(newTrackingId);
            response.setStatus("RESET");
            response.setUpdatedAt(Instant.now());
            
            // In production, this would:
            // 1. Invalidate old tracking ID
            // 2. Generate new tracking ID
            // 3. Update database records
            
            return Result.<ConnectorOnboardingResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error resetting tracking ID: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("TRACKING_ID_RESET_FAILED",
                "Failed to reset tracking ID: " + error.getMessage())));
        });
    }
    
    private String generateTrackingId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

