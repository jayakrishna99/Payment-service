package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.RoutingAlgorithmV2Request;
import com.hyperswitch.common.dto.RoutingAlgorithmV2Response;
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
 * REST controller for routing operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/routing-algorithms")
@Tag(name = "Routing V2", description = "Routing algorithm operations (v2)")
public class RoutingV2Controller {
    
    private final RoutingService routingService;
    
    @Autowired
    public RoutingV2Controller(RoutingService routingService) {
        this.routingService = routingService;
    }
    
    /**
     * Create routing algorithm (v2 API)
     * POST /api/v2/routing-algorithms
     */
    @PostMapping
    @Operation(
        summary = "Create routing algorithm (v2)",
        description = "Creates a new routing algorithm using v2 API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing algorithm created successfully",
            content = @Content(schema = @Schema(implementation = RoutingAlgorithmV2Response.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RoutingAlgorithmV2Response>> createRoutingAlgorithmV2(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody RoutingAlgorithmV2Request request) {
        return routingService.createRoutingAlgorithmV2(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get routing algorithm (v2 API)
     * GET /api/v2/routing-algorithms/{algorithm_id}
     */
    @GetMapping("/{algorithm_id}")
    @Operation(
        summary = "Get routing algorithm (v2)",
        description = "Retrieves a routing algorithm using v2 API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Routing algorithm retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingAlgorithmV2Response.class))
        ),
        @ApiResponse(responseCode = "404", description = "Routing algorithm not found")
    })
    public Mono<ResponseEntity<RoutingAlgorithmV2Response>> getRoutingAlgorithmV2(
            @RequestHeader("merchant_id") String merchantId,
            @PathVariable("algorithm_id") String algorithmId) {
        return routingService.getRoutingAlgorithmV2(merchantId, algorithmId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

