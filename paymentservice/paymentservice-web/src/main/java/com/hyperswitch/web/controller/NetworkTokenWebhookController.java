package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.NetworkTokenWebhookRequest;
import com.hyperswitch.common.dto.NetworkTokenWebhookResponse;
import com.hyperswitch.core.webhooks.WebhookDeliveryService;
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
 * REST controller for network token requestor webhook operations
 */
@RestController
@RequestMapping("/api/webhooks/network_token_requestor")
@Tag(name = "Network Token Webhook", description = "Network token requestor webhook operations")
public class NetworkTokenWebhookController {
    
    private final WebhookDeliveryService webhookDeliveryService;
    
    @Autowired
    public NetworkTokenWebhookController(WebhookDeliveryService webhookDeliveryService) {
        this.webhookDeliveryService = webhookDeliveryService;
    }
    
    /**
     * Handle network token requestor webhook (GET)
     * GET /api/webhooks/network_token_requestor/{connector}/ref
     */
    @GetMapping("/{connector}/ref")
    @Operation(
        summary = "Handle network token requestor webhook (GET)",
        description = "Handles incoming network token requestor webhook via GET method"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook processed successfully",
            content = @Content(schema = @Schema(implementation = NetworkTokenWebhookResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<NetworkTokenWebhookResponse>> handleNetworkTokenWebhookGet(
            @PathVariable("connector") String connector,
            @RequestBody NetworkTokenWebhookRequest request) {
        return webhookDeliveryService.processNetworkTokenWebhook(connector, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Handle network token requestor webhook (POST)
     * POST /api/webhooks/network_token_requestor/{connector}/ref
     */
    @PostMapping("/{connector}/ref")
    @Operation(
        summary = "Handle network token requestor webhook (POST)",
        description = "Handles incoming network token requestor webhook via POST method"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook processed successfully",
            content = @Content(schema = @Schema(implementation = NetworkTokenWebhookResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<NetworkTokenWebhookResponse>> handleNetworkTokenWebhookPost(
            @PathVariable("connector") String connector,
            @RequestBody NetworkTokenWebhookRequest request) {
        return webhookDeliveryService.processNetworkTokenWebhook(connector, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Handle network token requestor webhook (PUT)
     * PUT /api/webhooks/network_token_requestor/{connector}/ref
     */
    @PutMapping("/{connector}/ref")
    @Operation(
        summary = "Handle network token requestor webhook (PUT)",
        description = "Handles incoming network token requestor webhook via PUT method"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook processed successfully",
            content = @Content(schema = @Schema(implementation = NetworkTokenWebhookResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<NetworkTokenWebhookResponse>> handleNetworkTokenWebhookPut(
            @PathVariable("connector") String connector,
            @RequestBody NetworkTokenWebhookRequest request) {
        return webhookDeliveryService.processNetworkTokenWebhook(connector, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

