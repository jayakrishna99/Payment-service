package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.DynamicRoutingRequest;
import com.hyperswitch.common.dto.DynamicRoutingResponse;
import com.hyperswitch.common.dto.VolumeSplitRequest;
import com.hyperswitch.common.dto.VolumeSplitResponse;
import com.hyperswitch.routing.RoutingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for dynamic routing operations
 */
@RestController
@RequestMapping("/api/account/{account_id}/business_profile/{profile_id}/dynamic_routing")
@Tag(name = "Dynamic Routing", description = "Dynamic routing algorithm operations")
public class DynamicRoutingController {
    
    private final RoutingService routingService;
    
    @Autowired
    public DynamicRoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }
    
    /**
     * Create success-based routing
     * POST /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/success_based/create
     */
    @PostMapping("/success_based/create")
    @Operation(
        summary = "Create success-based routing",
        description = "Creates a success-based routing algorithm"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success-based routing created successfully",
            content = @Content(schema = @Schema(implementation = DynamicRoutingResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DynamicRoutingResponse>> createSuccessBasedRouting(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @RequestBody DynamicRoutingRequest request) {
        return routingService.createSuccessBasedRouting(accountId, profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update success-based routing config
     * PATCH /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/success_based/config/{algorithm_id}
     */
    @PatchMapping("/success_based/config/{algorithm_id}")
    @Operation(
        summary = "Update success-based routing config",
        description = "Updates the configuration of a success-based routing algorithm"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success-based routing config updated successfully",
            content = @Content(schema = @Schema(implementation = DynamicRoutingResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Algorithm not found")
    })
    public Mono<ResponseEntity<DynamicRoutingResponse>> updateSuccessBasedRoutingConfig(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @PathVariable("algorithm_id") String algorithmId,
            @RequestBody DynamicRoutingRequest request) {
        return routingService.updateSuccessBasedRoutingConfig(accountId, profileId, algorithmId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Create elimination routing
     * POST /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/elimination/create
     */
    @PostMapping("/elimination/create")
    @Operation(
        summary = "Create elimination routing",
        description = "Creates an elimination routing algorithm"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Elimination routing created successfully",
            content = @Content(schema = @Schema(implementation = DynamicRoutingResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DynamicRoutingResponse>> createEliminationRouting(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @RequestBody DynamicRoutingRequest request) {
        return routingService.createEliminationRouting(accountId, profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update elimination routing config
     * PATCH /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/elimination/config/{algorithm_id}
     */
    @PatchMapping("/elimination/config/{algorithm_id}")
    @Operation(
        summary = "Update elimination routing config",
        description = "Updates the configuration of an elimination routing algorithm"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Elimination routing config updated successfully",
            content = @Content(schema = @Schema(implementation = DynamicRoutingResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Algorithm not found")
    })
    public Mono<ResponseEntity<DynamicRoutingResponse>> updateEliminationRoutingConfig(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @PathVariable("algorithm_id") String algorithmId,
            @RequestBody DynamicRoutingRequest request) {
        return routingService.updateEliminationRoutingConfig(accountId, profileId, algorithmId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Toggle contract-based routing
     * POST /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/contracts/toggle
     */
    @PostMapping("/contracts/toggle")
    @Operation(
        summary = "Toggle contract-based routing",
        description = "Toggles contract-based routing on or off"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Contract-based routing toggled successfully",
            content = @Content(schema = @Schema(implementation = DynamicRoutingResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DynamicRoutingResponse>> toggleContractBasedRouting(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @RequestBody DynamicRoutingRequest request) {
        return routingService.toggleContractBasedRouting(accountId, profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update contract-based routing config
     * PATCH /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/contracts/config/{algorithm_id}
     */
    @PatchMapping("/contracts/config/{algorithm_id}")
    @Operation(
        summary = "Update contract-based routing config",
        description = "Updates the configuration of a contract-based routing algorithm"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Contract-based routing config updated successfully",
            content = @Content(schema = @Schema(implementation = DynamicRoutingResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Algorithm not found")
    })
    public Mono<ResponseEntity<DynamicRoutingResponse>> updateContractBasedRoutingConfig(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @PathVariable("algorithm_id") String algorithmId,
            @RequestBody DynamicRoutingRequest request) {
        return routingService.updateContractBasedRoutingConfig(accountId, profileId, algorithmId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Set volume split
     * POST /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/set_volume_split
     */
    @PostMapping("/set_volume_split")
    @Operation(
        summary = "Set volume split",
        description = "Sets volume split configuration for routing"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Volume split set successfully",
            content = @Content(schema = @Schema(implementation = VolumeSplitResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<VolumeSplitResponse>> setVolumeSplit(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId,
            @RequestBody VolumeSplitRequest request) {
        return routingService.setVolumeSplit(accountId, profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get volume split
     * GET /api/account/{account_id}/business_profile/{profile_id}/dynamic_routing/get_volume_split
     */
    @GetMapping("/get_volume_split")
    @Operation(
        summary = "Get volume split",
        description = "Retrieves volume split configuration for routing"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Volume split retrieved successfully",
            content = @Content(schema = @Schema(implementation = VolumeSplitResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Volume split not found")
    })
    public Mono<ResponseEntity<VolumeSplitResponse>> getVolumeSplit(
            @PathVariable("account_id") String accountId,
            @PathVariable("profile_id") String profileId) {
        return routingService.getVolumeSplit(accountId, profileId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

