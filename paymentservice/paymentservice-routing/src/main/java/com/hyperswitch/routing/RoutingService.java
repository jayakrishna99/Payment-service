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
    
    /**
     * List payout routing configurations
     */
    Mono<Result<Flux<PayoutRoutingResponse>, PaymentError>> listPayoutRoutings(String merchantId);
    
    /**
     * Create payout routing configuration
     */
    Mono<Result<PayoutRoutingResponse, PaymentError>> createPayoutRouting(
        String merchantId,
        PayoutRoutingRequest request
    );
    
    /**
     * Get active payout routing
     */
    Mono<Result<PayoutRoutingResponse, PaymentError>> getActivePayoutRouting(String merchantId);
    
    /**
     * Get default payout routing
     */
    Mono<Result<PayoutRoutingResponse, PaymentError>> getDefaultPayoutRouting(String merchantId);
    
    /**
     * Set default payout routing
     */
    Mono<Result<PayoutRoutingResponse, PaymentError>> setDefaultPayoutRouting(
        String merchantId,
        PayoutRoutingRequest request
    );
    
    /**
     * Activate payout routing
     */
    Mono<Result<PayoutRoutingResponse, PaymentError>> activatePayoutRouting(
        String merchantId,
        String algorithmId
    );
    
    /**
     * Deactivate payout routing
     */
    Mono<Result<Void, PaymentError>> deactivatePayoutRouting(String merchantId);
    
    /**
     * Set default payout routing for profile
     */
    Mono<Result<PayoutRoutingResponse, PaymentError>> setDefaultPayoutRoutingForProfile(
        String merchantId,
        String profileId,
        PayoutRoutingRequest request
    );
    
    /**
     * Get default payout routing for profiles
     */
    Mono<Result<Flux<PayoutRoutingResponse>, PaymentError>> getDefaultPayoutRoutingForProfiles(String merchantId);
    
    /**
     * Create routing algorithm (v2 API)
     */
    Mono<Result<RoutingAlgorithmV2Response, PaymentError>> createRoutingAlgorithmV2(
        String merchantId,
        RoutingAlgorithmV2Request request
    );
    
    /**
     * Get routing algorithm (v2 API)
     */
    Mono<Result<RoutingAlgorithmV2Response, PaymentError>> getRoutingAlgorithmV2(
        String merchantId,
        String algorithmId
    );
    
    /**
     * Create success-based routing
     */
    Mono<Result<DynamicRoutingResponse, PaymentError>> createSuccessBasedRouting(
        String accountId,
        String profileId,
        DynamicRoutingRequest request
    );
    
    /**
     * Update success-based routing config
     */
    Mono<Result<DynamicRoutingResponse, PaymentError>> updateSuccessBasedRoutingConfig(
        String accountId,
        String profileId,
        String algorithmId,
        DynamicRoutingRequest request
    );
    
    /**
     * Create elimination routing
     */
    Mono<Result<DynamicRoutingResponse, PaymentError>> createEliminationRouting(
        String accountId,
        String profileId,
        DynamicRoutingRequest request
    );
    
    /**
     * Update elimination routing config
     */
    Mono<Result<DynamicRoutingResponse, PaymentError>> updateEliminationRoutingConfig(
        String accountId,
        String profileId,
        String algorithmId,
        DynamicRoutingRequest request
    );
    
    /**
     * Toggle contract-based routing
     */
    Mono<Result<DynamicRoutingResponse, PaymentError>> toggleContractBasedRouting(
        String accountId,
        String profileId,
        DynamicRoutingRequest request
    );
    
    /**
     * Update contract-based routing config
     */
    Mono<Result<DynamicRoutingResponse, PaymentError>> updateContractBasedRoutingConfig(
        String accountId,
        String profileId,
        String algorithmId,
        DynamicRoutingRequest request
    );
    
    /**
     * Set volume split
     */
    Mono<Result<VolumeSplitResponse, PaymentError>> setVolumeSplit(
        String accountId,
        String profileId,
        VolumeSplitRequest request
    );
    
    /**
     * Get volume split
     */
    Mono<Result<VolumeSplitResponse, PaymentError>> getVolumeSplit(
        String accountId,
        String profileId
    );
}

