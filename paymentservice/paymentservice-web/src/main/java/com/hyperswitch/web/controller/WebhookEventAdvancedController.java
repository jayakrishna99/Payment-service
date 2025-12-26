package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.WebhookAttemptResponse;
import com.hyperswitch.common.dto.WebhookEventListRequest;
import com.hyperswitch.core.webhooks.WebhookDeliveryService;
import com.hyperswitch.storage.entity.WebhookEventEntity;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

/**
 * REST controller for advanced webhook event listing operations
 */
@RestController
@RequestMapping("/api/webhooks")
@Tag(name = "Webhook Events Advanced", description = "Advanced webhook event listing operations")
public class WebhookEventAdvancedController {
    
    private final WebhookDeliveryService webhookDeliveryService;
    
    @Autowired
    public WebhookEventAdvancedController(WebhookDeliveryService webhookDeliveryService) {
        this.webhookDeliveryService = webhookDeliveryService;
    }
    
    /**
     * List initial webhook delivery attempts
     * POST /api/webhooks/{merchant_id}/events
     */
    @PostMapping("/{merchant_id}/events")
    @Operation(
        summary = "List initial webhook delivery attempts",
        description = "Lists initial webhook delivery attempts with filtering"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook events retrieved successfully"
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<List<WebhookEventEntity>>> listInitialWebhookAttempts(
            @PathVariable("merchant_id") String merchantId,
            @RequestBody WebhookEventListRequest request) {
        return webhookDeliveryService.listInitialWebhookAttempts(merchantId, request)
            .collectList()
            .map(ResponseEntity::ok);
    }
    
    /**
     * List webhook delivery attempts for an initial attempt
     * GET /api/webhooks/{merchant_id}/{initial_attempt_id}/attempts
     */
    @GetMapping("/{merchant_id}/{initial_attempt_id}/attempts")
    @Operation(
        summary = "List webhook delivery attempts",
        description = "Lists all delivery attempts for a given initial webhook attempt"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook attempts retrieved successfully"
        ),
        @ApiResponse(responseCode = "404", description = "Initial attempt not found")
    })
    public Mono<ResponseEntity<List<WebhookAttemptResponse>>> listWebhookAttempts(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("initial_attempt_id") String initialAttemptId) {
        return webhookDeliveryService.listWebhookAttempts(merchantId, initialAttemptId)
            .collectList()
            .map(ResponseEntity::ok);
    }
    
    /**
     * Retry webhook delivery (with merchant ID in path)
     * POST /api/webhooks/{merchant_id}/{event_id}/retry
     */
    @PostMapping("/{merchant_id}/{event_id}/retry")
    @Operation(
        summary = "Retry webhook delivery",
        description = "Retries webhook delivery for a specific event (with merchant ID in path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook retry initiated successfully"
        ),
        @ApiResponse(responseCode = "404", description = "Webhook event not found")
    })
    public Mono<ResponseEntity<Void>> retryWebhookDelivery(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("event_id") String eventId) {
        return webhookDeliveryService.retryWebhookDeliveryWithMerchantId(merchantId, eventId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

