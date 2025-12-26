package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ConnectorOnboardingRequest;
import com.hyperswitch.common.dto.ConnectorOnboardingResponse;
import com.hyperswitch.core.connectoronboarding.ConnectorOnboardingService;
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
 * REST controller for connector onboarding operations
 */
@RestController
@RequestMapping("/api/connector_onboarding")
@Tag(name = "Connector Onboarding", description = "Connector onboarding operations")
public class ConnectorOnboardingController {
    
    private final ConnectorOnboardingService connectorOnboardingService;
    
    @Autowired
    public ConnectorOnboardingController(ConnectorOnboardingService connectorOnboardingService) {
        this.connectorOnboardingService = connectorOnboardingService;
    }
    
    /**
     * Get action URL
     * POST /api/connector_onboarding/action_url
     */
    @PostMapping("/action_url")
    @Operation(
        summary = "Get action URL",
        description = "Generates an action URL for connector onboarding"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Action URL generated successfully",
            content = @Content(schema = @Schema(implementation = ConnectorOnboardingResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorOnboardingResponse>> getActionUrl(
            @RequestBody ConnectorOnboardingRequest request) {
        return connectorOnboardingService.getActionUrl(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Sync onboarding status
     * POST /api/connector_onboarding/sync
     */
    @PostMapping("/sync")
    @Operation(
        summary = "Sync onboarding status",
        description = "Synchronizes the onboarding status with the connector"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Onboarding status synced successfully",
            content = @Content(schema = @Schema(implementation = ConnectorOnboardingResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorOnboardingResponse>> syncOnboarding(
            @RequestBody ConnectorOnboardingRequest request) {
        return connectorOnboardingService.syncOnboarding(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Reset tracking ID
     * POST /api/connector_onboarding/reset_tracking_id
     */
    @PostMapping("/reset_tracking_id")
    @Operation(
        summary = "Reset tracking ID",
        description = "Resets the tracking ID for connector onboarding"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tracking ID reset successfully",
            content = @Content(schema = @Schema(implementation = ConnectorOnboardingResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorOnboardingResponse>> resetTrackingId(
            @RequestBody ConnectorOnboardingRequest request) {
        return connectorOnboardingService.resetTrackingId(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

