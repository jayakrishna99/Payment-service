package com.hyperswitch.routing;

import com.hyperswitch.common.enums.Connector;
import com.hyperswitch.common.dto.CreatePaymentRequest;
import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Intelligent routing service to select the best payment processor
 */
public interface RoutingService {
    
    /**
     * Select the best connector for a payment request
     */
    Mono<List<Connector>> selectConnectors(CreatePaymentRequest request, String merchantId);
    
    /**
     * Get routing algorithm being used
     */
    RoutingAlgorithm getAlgorithm();
    
    /**
     * Create routing configuration
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> createRoutingConfig(
        String merchantId,
        RoutingConfigRequest request
    );
    
    /**
     * List routing configurations
     */
    Mono<Result<Flux<RoutingConfigResponse>, PaymentError>> listRoutingConfigs(
        String merchantId,
        Integer limit,
        Integer offset,
        String transactionType
    );
    
    /**
     * Retrieve routing configuration
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> getRoutingConfig(
        String merchantId,
        String algorithmId
    );
    
    /**
     * Activate routing algorithm
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> activateRoutingAlgorithm(
        String merchantId,
        String algorithmId,
        RoutingActivateRequest request
    );
    
    /**
     * Deactivate routing configuration
     */
    Mono<Result<Void, PaymentError>> deactivateRoutingConfig(
        String merchantId,
        RoutingActivateRequest request
    );
    
    /**
     * Set default routing configuration
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> setDefaultRoutingConfig(
        String merchantId,
        RoutingDefaultRequest request
    );
    
    /**
     * Get default routing configuration
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> getDefaultRoutingConfig(
        String merchantId
    );
    
    /**
     * Get active routing configuration
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> getActiveRoutingConfig(
        String merchantId
    );
    
    /**
     * List routing configurations for profile
     */
    Mono<Result<Flux<RoutingConfigResponse>, PaymentError>> listRoutingConfigsForProfile(
        String merchantId,
        Integer limit,
        Integer offset
    );
    
    /**
     * Set default routing for profile
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> setDefaultRoutingForProfile(
        String merchantId,
        String profileId,
        RoutingDefaultRequest request
    );
    
    /**
     * Get default routing for profile
     */
    Mono<Result<RoutingConfigResponse, PaymentError>> getDefaultRoutingForProfile(
        String merchantId
    );
    
    /**
     * Upsert decision manager config
     */
    Mono<Result<DecisionManagerConfigResponse, PaymentError>> upsertDecisionManagerConfig(
        String merchantId,
        DecisionManagerConfigRequest request
    );
    
    /**
     * Get decision manager config
     */
    Mono<Result<DecisionManagerConfigResponse, PaymentError>> getDecisionManagerConfig(
        String merchantId
    );
    
    /**
     * Delete decision manager config
     */
    Mono<Result<Void, PaymentError>> deleteDecisionManagerConfig(
        String merchantId
    );
    
    /**
     * Upsert surcharge decision manager config
     */
    Mono<Result<DecisionManagerConfigResponse, PaymentError>> upsertSurchargeDecisionManagerConfig(
        String merchantId,
        DecisionManagerConfigRequest request
    );
    
    /**
     * Get surcharge decision manager config
     */
    Mono<Result<DecisionManagerConfigResponse, PaymentError>> getSurchargeDecisionManagerConfig(
        String merchantId
    );
    
    /**
     * Delete surcharge decision manager config
     */
    Mono<Result<Void, PaymentError>> deleteSurchargeDecisionManagerConfig(
        String merchantId
    );
    
    /**
     * Evaluate routing rule
     */
    Mono<Result<RoutingEvaluationResponse, PaymentError>> evaluateRoutingRule(
        String merchantId,
        RoutingEvaluationRequest request
    );
    
    /**
     * Update gateway score (feedback)
     */
    Mono<Result<Void, PaymentError>> updateGatewayScore(
        String merchantId,
        GatewayScoreUpdateRequest request
    );
    
    /**
     * Migrate routing rules
     */
    Mono<Result<Void, PaymentError>> migrateRoutingRules(
        String merchantId,
        RoutingRuleMigrationRequest request
    );
}

