package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.WebhookEvent;
import com.hyperswitch.core.webhooks.WebhookDeliveryService;
import com.hyperswitch.web.controller.PaymentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.Map;

/**
 * REST controller for webhook event management
 */
@RestController
@RequestMapping("/api/webhooks/events")
@Tag(name = "Webhook Events", description = "Manage and track webhook events")
public class WebhookEventController {

    private final WebhookDeliveryService webhookDeliveryService;

    @Autowired
    public WebhookEventController(WebhookDeliveryService webhookDeliveryService) {
        this.webhookDeliveryService = webhookDeliveryService;
    }

    /**
     * Get webhook event status
     * GET /api/webhooks/events/{eventId}
     */
    @GetMapping("/{eventId}")
    @Operation(summary = "Get webhook event status", description = "Retrieve the delivery status of a webhook event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Webhook event found"),
        @ApiResponse(responseCode = "404", description = "Webhook event not found")
    })
    public Mono<ResponseEntity<WebhookEvent>> getWebhookStatus(@PathVariable String eventId) {
        return webhookDeliveryService.getWebhookStatus(eventId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Retry webhook delivery
     * POST /api/webhooks/events/{eventId}/retry
     */
    @PostMapping("/{eventId}/retry")
    @Operation(summary = "Retry webhook delivery", description = "Retry delivering a failed webhook event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Webhook retry initiated"),
        @ApiResponse(responseCode = "404", description = "Webhook event not found"),
        @ApiResponse(responseCode = "400", description = "Maximum retries exceeded")
    })
    public Mono<ResponseEntity<Map<String, String>>> retryWebhookDelivery(@PathVariable String eventId) {
        return webhookDeliveryService.retryWebhookDelivery(eventId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(Map.of("status", "retry_initiated", "eventId", eventId));
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

