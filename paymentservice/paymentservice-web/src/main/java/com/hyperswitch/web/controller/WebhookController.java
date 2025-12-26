package com.hyperswitch.web.controller;

import com.hyperswitch.connectors.ConnectorService;
import com.hyperswitch.connectors.WebhookPayload;
import com.hyperswitch.common.types.PaymentId;
import com.hyperswitch.core.payments.PaymentService;
import com.hyperswitch.core.fraudcheck.FraudCheckService;
import com.hyperswitch.common.types.FraudCheckStatus;
import com.hyperswitch.web.config.ConnectorWebhookConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Webhook controller for handling payment processor webhooks
 */
@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final ConnectorService connectorService;
    private final PaymentService paymentService;
    private final FraudCheckService fraudCheckService;
    private final ConnectorWebhookConfig webhookConfig;
    private final com.hyperswitch.core.webhooks.WebhookDeliveryService webhookDeliveryService;

    @Autowired
    public WebhookController(
            ConnectorService connectorService, 
            PaymentService paymentService,
            FraudCheckService fraudCheckService,
            ConnectorWebhookConfig webhookConfig,
            com.hyperswitch.core.webhooks.WebhookDeliveryService webhookDeliveryService) {
        this.connectorService = connectorService;
        this.paymentService = paymentService;
        this.fraudCheckService = fraudCheckService;
        this.webhookConfig = webhookConfig;
        this.webhookDeliveryService = webhookDeliveryService;
    }

    @PostMapping("/{connector}")
    public Mono<ResponseEntity<Map<String, String>>> handleWebhook(
            @PathVariable String connector,
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload) {
        
        log.info("Received webhook from connector: {}", connector);
        
        // Get webhook secret from configuration
        String signature = headers.getOrDefault("x-signature", headers.getOrDefault("stripe-signature", ""));
        String secret = webhookConfig.getWebhookSecret(connector.toLowerCase());
        
        if (secret.isEmpty()) {
            log.warn("Webhook secret not configured for connector: {}", connector);
            return Mono.just(ResponseEntity.status(500).body(
                Map.of("error", "Webhook secret not configured")
            ));
        }
        
        return connectorService.getConnector(connector)
            .verifyWebhook(payload, signature, secret)
            .flatMap(verified -> {
                if (Boolean.FALSE.equals(verified)) {
                    log.warn("Webhook signature verification failed for connector: {}", connector);
                    return Mono.just(ResponseEntity.status(401).body(
                        Map.of("error", "Invalid signature")
                    ));
                }
                
                return connectorService.getConnector(connector)
                    .parseWebhook(payload)
                    .flatMap(webhookPayload -> {
                        log.info("Processing webhook event: {} for payment: {}", 
                            webhookPayload.getEventType(), webhookPayload.getPaymentId());
                        
                        // Process webhook based on event type
                        return processWebhookEvent(webhookPayload)
                            .thenReturn(ResponseEntity.ok(Map.of("status", "processed")));
                    });
            })
            .onErrorResume(error -> {
                log.error("Error processing webhook", error);
                return Mono.just(ResponseEntity.status(500).body(
                    Map.of("error", "Webhook processing failed")
                ));
            });
    }

    private Mono<Void> processWebhookEvent(WebhookPayload payload) {
        log.info("Processing webhook event: {} for payment: {}", 
            payload.getEventType(), payload.getPaymentId());
        
        if (payload.getPaymentId() == null) {
            log.warn("Webhook payload missing payment ID");
            return Mono.empty();
        }
        
        // Process webhook events based on event type
        String eventType = payload.getEventType();
        
        if (eventType != null && eventType.contains("payment_intent")) {
            return processPaymentWebhook(payload);
        } else if (eventType != null && eventType.contains("refund")) {
            return processRefundWebhook(payload);
        } else if (eventType != null && (eventType.contains("fraud") || eventType.contains("frm"))) {
            return processFraudWebhook(payload);
        } else {
            log.debug("Unhandled webhook event type: {}", eventType);
            return Mono.empty();
        }
    }
    
    private Mono<Void> processPaymentWebhook(WebhookPayload payload) {
        try {
            PaymentId paymentId = PaymentId.of(payload.getPaymentId());
            
            // Get current payment status and process webhook event
            return paymentService.getPayment(paymentId)
                .flatMap(result -> {
                    if (result.isOk()) {
                        log.info("Payment status retrieved for webhook: {} with status: {}", 
                            paymentId, result.unwrap().getStatus());
                        // In a full implementation, we would update payment status based on webhook event
                        // The paymentService is used here to retrieve payment information
                        return Mono.<Void>empty();
                    } else {
                        log.warn("Payment not found for webhook: {}", paymentId);
                        return Mono.<Void>empty();
                    }
                })
                .onErrorResume(error -> {
                    log.error("Error processing payment webhook", error);
                    return Mono.<Void>empty();
                });
        } catch (Exception e) {
            log.error("Invalid payment ID in webhook: {}", payload.getPaymentId(), e);
            return Mono.empty();
        }
    }
    
    private Mono<Void> processRefundWebhook(WebhookPayload payload) {
        log.info("Processing refund webhook for payment: {}", payload.getPaymentId());
        // Refund webhook processing implementation
        // This would update refund status based on webhook event
        return Mono.empty();
    }
    
    /**
     * Process fraud check webhook events from fraud risk management (FRM) providers
     * Handles events like frm_approved, frm_rejected, frm_manual_review
     */
    private Mono<Void> processFraudWebhook(WebhookPayload payload) {
        log.info("Processing fraud webhook for payment: {}, event: {}", 
            payload.getPaymentId(), payload.getEventType());
        
        if (payload.getPaymentId() == null || payload.getAttemptId() == null) {
            log.warn("Fraud webhook missing payment ID or attempt ID");
            return Mono.empty();
        }
        
        String eventType = payload.getEventType();
        String merchantId = payload.getMerchantId() != null ? payload.getMerchantId() : "default";
        
        // Map webhook event type to fraud check status
        FraudCheckStatus fraudStatus = mapEventTypeToFraudStatus(eventType);
        
        // Update fraud check status from webhook
        return fraudCheckService.updateFraudCheckStatus(
            merchantId,
            payload.getFrmId() != null ? payload.getFrmId() : "frm_" + payload.getPaymentId(),
            fraudStatus,
            payload.getFrmScore(),
            payload.getFrmReason()
        )
        .flatMap(result -> {
            if (result.isLeft()) {
                log.error("Failed to update fraud check status: {}", result.getLeft().getMessage());
                return Mono.<Void>empty();
            }
            
            return handleFraudStatusResult(payload, fraudStatus);
        })
        .onErrorResume(error -> {
            log.error("Error processing fraud webhook", error);
            return Mono.empty();
            });
    }
    
    /**
     * Map webhook event type to fraud check status
     */
    private FraudCheckStatus mapEventTypeToFraudStatus(String eventType) {
        if (eventType == null) {
            return FraudCheckStatus.PENDING;
        }
        
        String lowerEventType = eventType.toLowerCase();
        if (lowerEventType.contains("approved") || lowerEventType.contains("legit")) {
            return FraudCheckStatus.LEGIT;
        } else if (lowerEventType.contains("rejected") || lowerEventType.contains("fraud")) {
            return FraudCheckStatus.FRAUD;
        } else if (lowerEventType.contains("manual_review")) {
            return FraudCheckStatus.MANUAL_REVIEW;
        } else {
            return FraudCheckStatus.PENDING;
        }
    }
    
    /**
     * Handle fraud status result based on fraud check status
     */
    private Mono<Void> handleFraudStatusResult(WebhookPayload payload, FraudCheckStatus fraudStatus) {
        return switch (fraudStatus) {
            case LEGIT -> {
                log.info("Fraud check approved for payment: {}, proceeding with payment", payload.getPaymentId());
                yield Mono.<Void>empty();
            }
            case FRAUD -> voidPaymentAfterFraudRejection(payload);
            case MANUAL_REVIEW, PENDING -> {
                log.info("Fraud check requires manual review for payment: {}", payload.getPaymentId());
                yield Mono.<Void>empty();
            }
            case TRANSACTION_FAILURE -> {
                log.warn("Fraud check transaction failure for payment: {}", payload.getPaymentId());
                yield Mono.<Void>empty();
            }
        };
    }
    
    /**
     * Void payment after fraud rejection
     */
    private Mono<Void> voidPaymentAfterFraudRejection(WebhookPayload payload) {
        log.warn("Fraud check rejected for payment: {}, voiding payment", payload.getPaymentId());
        try {
            PaymentId paymentId = PaymentId.of(payload.getPaymentId());
            com.hyperswitch.common.dto.VoidPaymentRequest voidRequest = new com.hyperswitch.common.dto.VoidPaymentRequest();
            voidRequest.setCancellationReason("Fraud check rejected");
            return paymentService.voidPayment(paymentId, voidRequest)
                .then()
                .onErrorResume(error -> {
                    log.error("Failed to void payment after fraud rejection", error);
                    return Mono.empty();
                });
        } catch (Exception e) {
            log.error("Invalid payment ID in fraud webhook", e);
            return Mono.empty();
        }
    }
    
    /**
     * List webhook events with filtering
     * GET /api/webhooks/events?merchantId=xxx&eventType=xxx&connector=xxx&deliveryStatus=xxx&startDate=xxx&endDate=xxx&limit=xxx&offset=xxx
     */
    @GetMapping("/events")
    public Mono<ResponseEntity<java.util.List<com.hyperswitch.storage.entity.WebhookEventEntity>>> listWebhookEvents(
            @RequestParam String merchantId,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String connector,
            @RequestParam(required = false) String deliveryStatus,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false, defaultValue = "50") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {
        
        log.info("Listing webhook events for merchant: {}", merchantId);
        
        java.time.Instant start = null;
        java.time.Instant end = null;
        
        try {
            if (startDate != null && !startDate.isEmpty()) {
                start = java.time.Instant.parse(startDate);
            }
            if (endDate != null && !endDate.isEmpty()) {
                end = java.time.Instant.parse(endDate);
            }
        } catch (Exception e) {
            log.warn("Invalid date format in webhook event listing request", e);
            return Mono.just(ResponseEntity.badRequest().build());
        }
        
        return webhookDeliveryService.listWebhookEvents(
            merchantId, eventType, connector, deliveryStatus, start, end, limit, offset)
            .collectList()
            .map(ResponseEntity::ok)
            .onErrorResume(error -> {
                log.error("Error listing webhook events", error);
                return Mono.just(ResponseEntity.status(500).build());
            });
    }
}

