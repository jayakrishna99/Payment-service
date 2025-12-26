package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.WebhookRelayRequest;
import com.hyperswitch.common.dto.WebhookRelayResponse;
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
 * REST controller for webhook relay operations
 */
@RestController
@RequestMapping("/api/webhooks/relay")
@Tag(name = "Webhook Relay", description = "Webhook relay operations")
public class WebhookRelayController {
    
    private final WebhookDeliveryService webhookDeliveryService;
    
    @Autowired
    public WebhookRelayController(WebhookDeliveryService webhookDeliveryService) {
        this.webhookDeliveryService = webhookDeliveryService;
    }
    
    /**
     * Relay webhook (v1 API)
     * POST /api/webhooks/relay/{merchant_id}/{merchant_connector_account_id}
     */
    @PostMapping("/{merchant_id}/{merchant_connector_account_id}")
    @Operation(
        summary = "Relay webhook (v1)",
        description = "Relays a webhook to merchant endpoint (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook relayed successfully",
            content = @Content(schema = @Schema(implementation = WebhookRelayResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<WebhookRelayResponse>> relayWebhookV1(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("merchant_connector_account_id") String merchantConnectorAccountId,
            @RequestBody WebhookRelayRequest request) {
        return webhookDeliveryService.relayWebhook(merchantId, merchantConnectorAccountId, null, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
}

