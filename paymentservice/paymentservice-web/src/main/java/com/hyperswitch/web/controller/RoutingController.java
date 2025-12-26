package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.routing.RoutingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for routing operations
 */
@RestController
@RequestMapping("/api/routing")
@Tag(name = "Routing", description = "Routing configuration and management operations")
public class RoutingController {

    private final RoutingService routingService;

    @Autowired
    public RoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    /**
     * Create routing configuration
     * POST /api/routing
     */
    @PostMapping
    @Operation(
        summary = "Create routing configuration",
        description = "Creates a new routing configuration with algorithm settings"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing configuration created successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request"
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> createRoutingConfig(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody RoutingConfigRequest request) {
        return routingService.createRoutingConfig(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * List routing configurations
     * GET /api/routing
     */
    @GetMapping
    @Operation(
        summary = "List routing configurations",
        description = "Lists all routing configurations for a merchant with optional filtering"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing configurations retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<RoutingConfigResponse>>> listRoutingConfigs(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) String transactionType) {
        return routingService.listRoutingConfigs(merchantId, limit, offset, transactionType)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Retrieve routing configuration
     * GET /api/routing/{algorithm_id}
     */
    @GetMapping("/{algorithm_id}")
    @Operation(
        summary = "Retrieve routing configuration",
        description = "Retrieves a routing configuration by its algorithm ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing configuration retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Routing configuration not found"
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> getRoutingConfig(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Algorithm ID", required = true)
            @PathVariable("algorithm_id") String algorithmId) {
        return routingService.getRoutingConfig(merchantId, algorithmId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Activate routing algorithm
     * POST /api/routing/{algorithm_id}/activate
     */
    @PostMapping("/{algorithm_id}/activate")
    @Operation(
        summary = "Activate routing algorithm",
        description = "Activates a routing algorithm for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing algorithm activated successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Routing algorithm not found"
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> activateRoutingAlgorithm(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Algorithm ID", required = true)
            @PathVariable("algorithm_id") String algorithmId,
            @RequestBody RoutingActivateRequest request) {
        return routingService.activateRoutingAlgorithm(merchantId, algorithmId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Deactivate routing configuration
     * POST /api/routing/deactivate
     */
    @PostMapping("/deactivate")
    @Operation(
        summary = "Deactivate routing configuration",
        description = "Deactivates the currently active routing configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing configuration deactivated successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No active routing configuration found"
        )
    })
    public Mono<ResponseEntity<Void>> deactivateRoutingConfig(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody RoutingActivateRequest request) {
        return routingService.deactivateRoutingConfig(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Set default routing configuration
     * POST /api/routing/default
     */
    @PostMapping("/default")
    @Operation(
        summary = "Set default routing configuration",
        description = "Sets a routing configuration as the default for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Default routing configuration set successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> setDefaultRoutingConfig(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody RoutingDefaultRequest request) {
        return routingService.setDefaultRoutingConfig(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get default routing configuration
     * GET /api/routing/default
     */
    @GetMapping("/default")
    @Operation(
        summary = "Get default routing configuration",
        description = "Retrieves the default routing configuration for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Default routing configuration retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No default routing configuration found"
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> getDefaultRoutingConfig(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.getDefaultRoutingConfig(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get active routing configuration
     * GET /api/routing/active
     */
    @GetMapping("/active")
    @Operation(
        summary = "Get active routing configuration",
        description = "Retrieves the currently active routing configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Active routing configuration retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No active routing configuration found"
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> getActiveRoutingConfig(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.getActiveRoutingConfig(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * List routing configurations for profile
     * GET /api/routing/list/profile
     */
    @GetMapping("/list/profile")
    @Operation(
        summary = "List routing configurations for profile",
        description = "Lists routing configurations for a profile"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing configurations retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<RoutingConfigResponse>>> listRoutingConfigsForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset) {
        return routingService.listRoutingConfigsForProfile(merchantId, limit, offset)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Set default routing for profile
     * POST /api/routing/default/profile/{profile_id}
     */
    @PostMapping("/default/profile/{profile_id}")
    @Operation(
        summary = "Set default routing for profile",
        description = "Sets a routing configuration as the default for a profile"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Default routing for profile set successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> setDefaultRoutingForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Profile ID", required = true)
            @PathVariable("profile_id") String profileId,
            @RequestBody RoutingDefaultRequest request) {
        return routingService.setDefaultRoutingForProfile(merchantId, profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get default routing for profile
     * GET /api/routing/default/profile
     */
    @GetMapping("/default/profile")
    @Operation(
        summary = "Get default routing for profile",
        description = "Retrieves the default routing configuration for a profile"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Default routing for profile retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No default routing configuration found for profile"
        )
    })
    public Mono<ResponseEntity<RoutingConfigResponse>> getDefaultRoutingForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.getDefaultRoutingForProfile(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Upsert decision manager config
     * PUT /api/routing/decision
     */
    @PutMapping("/decision")
    @Operation(
        summary = "Upsert decision manager config",
        description = "Creates or updates the decision manager configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Decision manager config upserted successfully",
            content = @Content(schema = @Schema(implementation = DecisionManagerConfigResponse.class))
        )
    })
    public Mono<ResponseEntity<DecisionManagerConfigResponse>> upsertDecisionManagerConfig(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody DecisionManagerConfigRequest request) {
        return routingService.upsertDecisionManagerConfig(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get decision manager config
     * GET /api/routing/decision
     */
    @GetMapping("/decision")
    @Operation(
        summary = "Get decision manager config",
        description = "Retrieves the decision manager configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Decision manager config retrieved successfully",
            content = @Content(schema = @Schema(implementation = DecisionManagerConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Decision manager config not found"
        )
    })
    public Mono<ResponseEntity<DecisionManagerConfigResponse>> getDecisionManagerConfig(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.getDecisionManagerConfig(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Delete decision manager config
     * DELETE /api/routing/decision
     */
    @DeleteMapping("/decision")
    @Operation(
        summary = "Delete decision manager config",
        description = "Deletes the decision manager configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Decision manager config deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Decision manager config not found"
        )
    })
    public Mono<ResponseEntity<Void>> deleteDecisionManagerConfig(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.deleteDecisionManagerConfig(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Upsert surcharge decision manager config
     * PUT /api/routing/decision/surcharge
     */
    @PutMapping("/decision/surcharge")
    @Operation(
        summary = "Upsert surcharge decision manager config",
        description = "Creates or updates the surcharge decision manager configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Surcharge decision manager config upserted successfully",
            content = @Content(schema = @Schema(implementation = DecisionManagerConfigResponse.class))
        )
    })
    public Mono<ResponseEntity<DecisionManagerConfigResponse>> upsertSurchargeDecisionManagerConfig(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody DecisionManagerConfigRequest request) {
        return routingService.upsertSurchargeDecisionManagerConfig(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get surcharge decision manager config
     * GET /api/routing/decision/surcharge
     */
    @GetMapping("/decision/surcharge")
    @Operation(
        summary = "Get surcharge decision manager config",
        description = "Retrieves the surcharge decision manager configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Surcharge decision manager config retrieved successfully",
            content = @Content(schema = @Schema(implementation = DecisionManagerConfigResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Surcharge decision manager config not found"
        )
    })
    public Mono<ResponseEntity<DecisionManagerConfigResponse>> getSurchargeDecisionManagerConfig(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.getSurchargeDecisionManagerConfig(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Delete surcharge decision manager config
     * DELETE /api/routing/decision/surcharge
     */
    @DeleteMapping("/decision/surcharge")
    @Operation(
        summary = "Delete surcharge decision manager config",
        description = "Deletes the surcharge decision manager configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Surcharge decision manager config deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Surcharge decision manager config not found"
        )
    })
    public Mono<ResponseEntity<Void>> deleteSurchargeDecisionManagerConfig(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return routingService.deleteSurchargeDecisionManagerConfig(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Evaluate routing rule
     * POST /api/routing/evaluate
     */
    @PostMapping("/evaluate")
    @Operation(
        summary = "Evaluate routing rule",
        description = "Evaluates a routing rule against a payment request"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing rule evaluated successfully",
            content = @Content(schema = @Schema(implementation = RoutingEvaluationResponse.class))
        )
    })
    public Mono<ResponseEntity<RoutingEvaluationResponse>> evaluateRoutingRule(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody RoutingEvaluationRequest request) {
        return routingService.evaluateRoutingRule(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Update gateway score (feedback)
     * POST /api/routing/feedback
     */
    @PostMapping("/feedback")
    @Operation(
        summary = "Update gateway score",
        description = "Updates the gateway score based on transaction feedback"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Gateway score updated successfully"
        )
    })
    public Mono<ResponseEntity<Void>> updateGatewayScore(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody GatewayScoreUpdateRequest request) {
        return routingService.updateGatewayScore(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Migrate routing rules
     * POST /api/routing/rule/migrate
     */
    @PostMapping("/rule/migrate")
    @Operation(
        summary = "Migrate routing rules",
        description = "Migrates routing rules from one algorithm to another"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing rules migrated successfully"
        )
    })
    public Mono<ResponseEntity<Void>> migrateRoutingRules(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody RoutingRuleMigrationRequest request) {
        return routingService.migrateRoutingRules(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

