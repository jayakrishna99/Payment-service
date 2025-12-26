package com.hyperswitch.core.webhooks;

import com.hyperswitch.common.dto.WebhookEvent;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service for delivering webhooks to merchant endpoints
 */
public interface WebhookDeliveryService {
    
    /**
     * Deliver a webhook event to merchant endpoint
     */
    Mono<Result<Void, PaymentError>> deliverWebhook(WebhookEvent event);
    
    /**
     * Retry webhook delivery
     */
    Mono<Result<Void, PaymentError>> retryWebhookDelivery(String eventId);
    
    /**
     * Get webhook delivery status
     */
    Mono<Result<WebhookEvent, PaymentError>> getWebhookStatus(String eventId);
    
    /**
     * List webhook events with filtering
     */
    reactor.core.publisher.Flux<com.hyperswitch.storage.entity.WebhookEventEntity> listWebhookEvents(
        String merchantId,
        String eventType,
        String connector,
        String deliveryStatus,
        java.time.Instant startDate,
        java.time.Instant endDate,
        Integer limit,
        Integer offset
    );
    
    /**
     * Process recovery webhook
     */
    Mono<Result<com.hyperswitch.common.dto.WebhookResponse, PaymentError>> processRecoveryWebhook(
        String merchantId,
        String paymentId,
        String connectorId,
        com.hyperswitch.common.dto.WebhookRequest request
    );
}

