package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.WebhookRequest;
import com.hyperswitch.common.dto.WebhookResponse;
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
 * REST controller for recovery webhook operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/webhooks/recovery")
@Tag(name = "Recovery Webhook V2", description = "Recovery webhook operations (v2)")
public class RecoveryWebhookV2Controller {
    
    private final WebhookDeliveryService webhookDeliveryService;
    
    @Autowired
    public RecoveryWebhookV2Controller(WebhookDeliveryService webhookDeliveryService) {
        this.webhookDeliveryService = webhookDeliveryService;
    }
    
    /**
     * Process recovery webhook (v2)
     * POST /api/v2/webhooks/recovery/{merchant_id}/{profile_id}/{connector_id}
     */
    @PostMapping("/{merchant_id}/{profile_id}/{connector_id}")
    @Operation(
        summary = "Process recovery webhook (v2)",
        description = "Processes a recovery webhook with profile context (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Recovery webhook processed successfully",
            content = @Content(schema = @Schema(implementation = WebhookResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<WebhookResponse>> processRecoveryWebhookV2(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("profile_id") String profileId,
            @PathVariable("connector_id") String connectorId,
            @RequestBody WebhookRequest request) {
        return webhookDeliveryService.processRecoveryWebhookV2(merchantId, profileId, connectorId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

