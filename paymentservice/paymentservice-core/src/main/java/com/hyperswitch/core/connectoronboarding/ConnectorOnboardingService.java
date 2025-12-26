package com.hyperswitch.core.connectoronboarding;

import com.hyperswitch.common.dto.ConnectorOnboardingRequest;
import com.hyperswitch.common.dto.ConnectorOnboardingResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for connector onboarding operations
 */
public interface ConnectorOnboardingService {
    
    /**
     * Get action URL for connector onboarding
     */
    Mono<Result<ConnectorOnboardingResponse, PaymentError>> getActionUrl(
            ConnectorOnboardingRequest request);
    
    /**
     * Sync onboarding status
     */
    Mono<Result<ConnectorOnboardingResponse, PaymentError>> syncOnboarding(
            ConnectorOnboardingRequest request);
    
    /**
     * Reset tracking ID
     */
    Mono<Result<ConnectorOnboardingResponse, PaymentError>> resetTrackingId(
            ConnectorOnboardingRequest request);
}

