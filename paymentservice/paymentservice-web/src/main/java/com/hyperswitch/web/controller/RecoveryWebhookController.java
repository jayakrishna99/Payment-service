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
 * REST controller for recovery webhook operations
 */
@RestController
@RequestMapping("/api/recovery-webhooks")
@Tag(name = "Recovery Webhooks", description = "Recovery webhook operations for revenue recovery")
public class RecoveryWebhookController {
    
    private final WebhookDeliveryService webhookDeliveryService;
    
    @Autowired
    public RecoveryWebhookController(WebhookDeliveryService webhookDeliveryService) {
        this.webhookDeliveryService = webhookDeliveryService;
    }
    
    /**
     * Receive recovery webhook
     * POST /api/recovery-webhooks/{merchant_id}/{payment_id}
     */
    @PostMapping("/{merchant_id}/{payment_id}")
    @Operation(
        summary = "Receive recovery webhook",
        description = "Receives a recovery webhook for a payment"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook received successfully",
            content = @Content(schema = @Schema(implementation = WebhookResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid webhook")
    })
    public Mono<ResponseEntity<WebhookResponse>> receiveRecoveryWebhook(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("payment_id") String paymentId,
            @RequestHeader(value = "X-Connector-Id", required = false) String connectorId,
            @RequestBody WebhookRequest request) {
        return webhookDeliveryService.processRecoveryWebhook(merchantId, paymentId, connectorId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

