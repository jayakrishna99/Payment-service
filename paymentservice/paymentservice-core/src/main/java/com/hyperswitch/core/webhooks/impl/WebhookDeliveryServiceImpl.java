package com.hyperswitch.core.webhooks.impl;

import com.hyperswitch.common.dto.WebhookEvent;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.webhooks.WebhookDeliveryService;
import com.hyperswitch.storage.entity.WebhookEventEntity;
import com.hyperswitch.storage.repository.WebhookEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of WebhookDeliveryService
 */
@Service
public class WebhookDeliveryServiceImpl implements WebhookDeliveryService {

    private static final Logger log = LoggerFactory.getLogger(WebhookDeliveryServiceImpl.class);
    private static final int MAX_RETRY_ATTEMPTS = 5;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    private final WebhookEventRepository webhookEventRepository;
    private final WebClient webClient;

    @Autowired
    public WebhookDeliveryServiceImpl(WebhookEventRepository webhookEventRepository) {
        this.webhookEventRepository = webhookEventRepository;
        this.webClient = WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024)) // 1MB
            .build();
    }

    @Override
    public Mono<Result<Void, PaymentError>> deliverWebhook(WebhookEvent event) {
        log.info("Delivering webhook event: {} to merchant: {}", event.getEventId(), event.getMerchantId());
        
        // Save webhook event
        WebhookEventEntity entity = toEntity(event);
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedAt(Instant.now());
        entity.setModifiedAt(Instant.now());
        entity.setAttemptCount(0);
        entity.setDeliveryStatus("PENDING");
        
        return webhookEventRepository.save(entity)
            .flatMap(saved -> {
                // Get merchant webhook URL (in production, this would come from merchant configuration)
                String webhookUrl = getMerchantWebhookUrl(event.getMerchantId());
                
                if (webhookUrl == null || webhookUrl.isEmpty()) {
                    log.warn("No webhook URL configured for merchant: {}", event.getMerchantId());
                    saved.setDeliveryStatus("FAILED");
                    saved.setModifiedAt(Instant.now());
                    return webhookEventRepository.save(saved)
                        .thenReturn(Result.<Void, PaymentError>err(
                            PaymentError.of("WEBHOOK_URL_NOT_CONFIGURED", "Webhook URL not configured for merchant")
                        ));
                }
                
                saved.setDeliveryUrl(webhookUrl);
                
                // Deliver webhook
                return deliverWebhookToUrl(saved, webhookUrl)
                    .flatMap(success -> {
                        if (Boolean.TRUE.equals(success)) {
                            saved.setDeliveryStatus("DELIVERED");
                            saved.setDeliveredAt(Instant.now());
                        } else {
                            saved.setDeliveryStatus("FAILED");
                        }
                        saved.setLastAttemptAt(Instant.now());
                        saved.setAttemptCount(saved.getAttemptCount() + 1);
                        saved.setModifiedAt(Instant.now());
                        
                        return webhookEventRepository.save(saved)
                            .thenReturn(Result.<Void, PaymentError>ok(null));
                    });
            })
            .onErrorResume(error -> {
                log.error("Error delivering webhook event: {}", event.getEventId(), error);
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of("WEBHOOK_DELIVERY_FAILED", "Failed to deliver webhook: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> retryWebhookDelivery(String eventId) {
        log.info("Retrying webhook delivery for event: {}", eventId);
        
        return webhookEventRepository.findByEventId(eventId)
            .flatMap(entity -> {
                if (entity.getAttemptCount() >= MAX_RETRY_ATTEMPTS) {
                    entity.setDeliveryStatus("RETRIES_EXCEEDED");
                    entity.setModifiedAt(Instant.now());
                    return webhookEventRepository.save(entity)
                        .thenReturn(Result.<Void, PaymentError>err(
                            PaymentError.of("MAX_RETRIES_EXCEEDED", "Maximum retry attempts exceeded")
                        ));
                }
                
                String webhookUrl = entity.getDeliveryUrl();
                if (webhookUrl == null || webhookUrl.isEmpty()) {
                    webhookUrl = getMerchantWebhookUrl(entity.getMerchantId());
                    entity.setDeliveryUrl(webhookUrl);
                }
                
                return deliverWebhookToUrl(entity, webhookUrl)
                    .flatMap(success -> {
                        if (Boolean.TRUE.equals(success)) {
                            entity.setDeliveryStatus("DELIVERED");
                            entity.setDeliveredAt(Instant.now());
                        } else {
                            entity.setDeliveryStatus("FAILED");
                        }
                        entity.setLastAttemptAt(Instant.now());
                        entity.setAttemptCount(entity.getAttemptCount() + 1);
                        entity.setModifiedAt(Instant.now());
                        
                        return webhookEventRepository.save(entity)
                            .thenReturn(Result.<Void, PaymentError>ok(null));
                    });
            })
            .switchIfEmpty(Mono.just(Result.<Void, PaymentError>err(
                PaymentError.of("WEBHOOK_EVENT_NOT_FOUND", "Webhook event not found: " + eventId)
            )))
            .onErrorResume(error -> {
                log.error("Error retrying webhook delivery: {}", eventId, error);
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of("WEBHOOK_RETRY_FAILED", "Failed to retry webhook: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<WebhookEvent, PaymentError>> getWebhookStatus(String eventId) {
        return webhookEventRepository.findByEventId(eventId)
            .map(this::toWebhookEvent)
            .map(Result::<WebhookEvent, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<WebhookEvent, PaymentError>err(
                PaymentError.of("WEBHOOK_EVENT_NOT_FOUND", "Webhook event not found: " + eventId)
            )))
            .onErrorResume(error -> {
                log.error("Error getting webhook status: {}", eventId, error);
                return Mono.just(Result.<WebhookEvent, PaymentError>err(
                    PaymentError.of("WEBHOOK_STATUS_RETRIEVAL_FAILED", "Failed to get webhook status: " + error.getMessage())
                ));
            });
    }

    private Mono<Boolean> deliverWebhookToUrl(WebhookEventEntity entity, String webhookUrl) {
        return webClient.post()
            .uri(webhookUrl)
            .header("Content-Type", "application/json")
            .header("X-Webhook-Event-Id", entity.getEventId())
            .header("X-Webhook-Event-Type", entity.getEventType())
            .bodyValue(entity.getData())
            .retrieve()
            .toBodilessEntity()
            .map(response -> {
                org.springframework.http.HttpStatusCode status = response.getStatusCode();
                boolean success = status.is2xxSuccessful();
                log.info("Webhook delivery to {} returned status: {}", webhookUrl, status);
                return success;
            })
            .timeout(REQUEST_TIMEOUT)
            .onErrorReturn(false);
    }

    private String getMerchantWebhookUrl(String merchantId) {
        // In production, this would fetch from merchant configuration
        // For now, return a placeholder
        return System.getenv("MERCHANT_WEBHOOK_URL_" + merchantId);
    }

    private WebhookEventEntity toEntity(WebhookEvent event) {
        WebhookEventEntity entity = new WebhookEventEntity();
        entity.setEventId(event.getEventId());
        entity.setEventType(event.getEventType().name());
        entity.setPaymentId(event.getPaymentId());
        entity.setMerchantId(event.getMerchantId());
        entity.setConnector(event.getConnector());
        entity.setData(event.getData());
        entity.setDeliveryStatus(event.getDeliveryStatus());
        return entity;
    }

    @Override
    public Flux<WebhookEventEntity> listWebhookEvents(
            String merchantId,
            String eventType,
            String connector,
            String deliveryStatus,
            Instant startDate,
            Instant endDate,
            Integer limit,
            Integer offset) {
        
        log.debug("Listing webhook events: merchant={}, eventType={}, connector={}, status={}, startDate={}, endDate={}",
            merchantId, eventType, connector, deliveryStatus, startDate, endDate);
        
        Flux<WebhookEventEntity> query = buildBaseQuery(merchantId, eventType, connector, deliveryStatus, startDate, endDate);
        query = applyAdditionalFilters(query, connector, deliveryStatus, startDate, endDate);
        query = applyPagination(query, limit, offset);
        
        return query.sort(this::compareByCreatedAt);
    }
    
    /**
     * Build base query based on provided filters
     */
    private Flux<WebhookEventEntity> buildBaseQuery(
            String merchantId,
            String eventType,
            String connector,
            String deliveryStatus,
            Instant startDate,
            Instant endDate) {
        
        if (eventType != null && startDate != null && endDate != null) {
            return webhookEventRepository.findByMerchantIdAndEventTypeAndCreatedAtBetween(
                merchantId, eventType, startDate, endDate);
        }
        if (startDate != null && endDate != null) {
            return webhookEventRepository.findByMerchantIdAndCreatedAtBetween(merchantId, startDate, endDate);
        }
        if (eventType != null) {
            return webhookEventRepository.findByMerchantIdAndEventType(merchantId, eventType);
        }
        if (connector != null) {
            return webhookEventRepository.findByMerchantIdAndConnector(merchantId, connector);
        }
        if (deliveryStatus != null) {
            return webhookEventRepository.findByMerchantIdAndDeliveryStatus(merchantId, deliveryStatus);
        }
        return webhookEventRepository.findByMerchantId(merchantId);
    }
    
    /**
     * Apply additional filters to the query
     */
    private Flux<WebhookEventEntity> applyAdditionalFilters(
            Flux<WebhookEventEntity> query,
            String connector,
            String deliveryStatus,
            Instant startDate,
            Instant endDate) {
        
        if (connector != null && !connector.isEmpty()) {
            query = query.filter(e -> connector.equals(e.getConnector()));
        }
        if (deliveryStatus != null && !deliveryStatus.isEmpty()) {
            query = query.filter(e -> deliveryStatus.equals(e.getDeliveryStatus()));
        }
        if (startDate != null) {
            query = query.filter(e -> e.getCreatedAt() != null && !e.getCreatedAt().isBefore(startDate));
        }
        if (endDate != null) {
            query = query.filter(e -> e.getCreatedAt() != null && !e.getCreatedAt().isAfter(endDate));
        }
        return query;
    }
    
    /**
     * Apply pagination to the query
     */
    private Flux<WebhookEventEntity> applyPagination(
            Flux<WebhookEventEntity> query,
            Integer limit,
            Integer offset) {
        
        if (offset != null && offset > 0) {
            query = query.skip(offset);
        }
        if (limit != null && limit > 0) {
            query = query.take(limit);
        }
        return query;
    }
    
    /**
     * Compare webhook events by created date (descending order)
     */
    private int compareByCreatedAt(WebhookEventEntity a, WebhookEventEntity b) {
        if (a.getCreatedAt() == null && b.getCreatedAt() == null) {
            return 0;
        }
        if (a.getCreatedAt() == null) {
            return 1;
        }
        if (b.getCreatedAt() == null) {
            return -1;
        }
        return b.getCreatedAt().compareTo(a.getCreatedAt()); // Descending order (newest first)
    }

    private WebhookEvent toWebhookEvent(WebhookEventEntity entity) {
        WebhookEvent event = new WebhookEvent();
        event.setEventId(entity.getEventId());
        event.setEventType(com.hyperswitch.common.types.WebhookEventType.valueOf(entity.getEventType()));
        event.setPaymentId(entity.getPaymentId());
        event.setMerchantId(entity.getMerchantId());
        event.setConnector(entity.getConnector());
        event.setData(entity.getData());
        event.setDeliveryStatus(entity.getDeliveryStatus());
        event.setCreatedAt(entity.getCreatedAt());
        event.setDeliveredAt(entity.getDeliveredAt());
        return event;
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.WebhookResponse, PaymentError>> processRecoveryWebhook(
            String merchantId,
            String paymentId,
            String connectorId,
            com.hyperswitch.common.dto.WebhookRequest request) {
        log.info("Processing recovery webhook for merchant: {}, payment: {}, connector: {}", 
            merchantId, paymentId, connectorId);
        
        // In production, this would process the recovery webhook and update payment status
        // For now, create a webhook event and return success
        WebhookEvent event = new WebhookEvent();
        event.setEventId("wh_" + UUID.randomUUID().toString().replace("-", ""));
        event.setEventType(com.hyperswitch.common.types.WebhookEventType.PAYMENT_INTENT_SUCCESS);
        event.setPaymentId(paymentId);
        event.setMerchantId(merchantId);
        event.setConnector(connectorId);
        event.setData(request.getData());
        event.setDeliveryStatus("PROCESSED");
        event.setCreatedAt(Instant.now());
        
        return deliverWebhook(event)
            .map(result -> {
                if (result.isOk()) {
                    com.hyperswitch.common.dto.WebhookResponse response = 
                        new com.hyperswitch.common.dto.WebhookResponse();
                    response.setProcessed(true);
                    response.setWebhookId(event.getEventId());
                    response.setMessage("Recovery webhook processed successfully");
                    return Result.<com.hyperswitch.common.dto.WebhookResponse, PaymentError>ok(response);
                } else {
                    return Result.<com.hyperswitch.common.dto.WebhookResponse, PaymentError>err(result.unwrapErr());
                }
            })
            .onErrorResume(error -> {
                log.error("Error processing recovery webhook", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.WebhookResponse, PaymentError>err(
                    PaymentError.of("RECOVERY_WEBHOOK_PROCESSING_FAILED",
                        "Failed to process recovery webhook: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.WebhookRelayResponse, PaymentError>> relayWebhook(
            String merchantId,
            String merchantConnectorAccountId,
            String profileId,
            com.hyperswitch.common.dto.WebhookRelayRequest request) {
        log.info("Relaying webhook for merchant: {}, connectorAccount: {}, profile: {}", 
            merchantId, merchantConnectorAccountId, profileId);
        
        String relayId = "relay_" + UUID.randomUUID().toString().replace("-", "");
        
        // In production, this would:
        // 1. Get merchant webhook URL from configuration
        // 2. Forward the webhook to the merchant endpoint
        // 3. Return the response
        
        return webClient.post()
            .uri(getMerchantWebhookUrl(merchantId))
            .headers(headers -> {
                if (request.getHeaders() != null) {
                    request.getHeaders().forEach(headers::set);
                }
                headers.set("Content-Type", "application/json");
                headers.set("X-Webhook-Relay-Id", relayId);
            })
            .bodyValue(request.getPayload())
            .retrieve()
            .toEntity(String.class)
            .map(response -> {
                com.hyperswitch.common.dto.WebhookRelayResponse relayResponse = 
                    new com.hyperswitch.common.dto.WebhookRelayResponse();
                relayResponse.setRelayId(relayId);
                relayResponse.setStatus(response.getStatusCode().is2xxSuccessful() ? "SUCCESS" : "FAILED");
                relayResponse.setStatusCode(response.getStatusCode().value());
                relayResponse.setResponseBody(response.getBody());
                relayResponse.setRelayedAt(Instant.now());
                return Result.<com.hyperswitch.common.dto.WebhookRelayResponse, PaymentError>ok(relayResponse);
            })
            .timeout(REQUEST_TIMEOUT)
            .onErrorResume(error -> {
                log.error("Error relaying webhook: {}", error.getMessage(), error);
                com.hyperswitch.common.dto.WebhookRelayResponse relayResponse = 
                    new com.hyperswitch.common.dto.WebhookRelayResponse();
                relayResponse.setRelayId(relayId);
                relayResponse.setStatus("FAILED");
                relayResponse.setStatusCode(500);
                relayResponse.setRelayedAt(Instant.now());
                return Mono.just(Result.<com.hyperswitch.common.dto.WebhookRelayResponse, PaymentError>ok(relayResponse));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.NetworkTokenWebhookResponse, PaymentError>> processNetworkTokenWebhook(
            String connector,
            com.hyperswitch.common.dto.NetworkTokenWebhookRequest request) {
        log.info("Processing network token requestor webhook for connector: {}, paymentMethod: {}", 
            connector, request.getPaymentMethodId());
        
        String webhookId = "nt_wh_" + UUID.randomUUID().toString().replace("-", "");
        
        // In production, this would:
        // 1. Verify webhook source/authorization
        // 2. Process network token webhook
        // 3. Update payment method status
        // 4. Trigger outgoing webhook if needed
        
        com.hyperswitch.common.dto.NetworkTokenWebhookResponse response = 
            new com.hyperswitch.common.dto.NetworkTokenWebhookResponse();
        response.setWebhookId(webhookId);
        response.setStatus("PROCESSED");
        response.setMessage("Network token webhook processed successfully");
        response.setProcessedAt(Instant.now());
        
        return Mono.just(Result.<com.hyperswitch.common.dto.NetworkTokenWebhookResponse, PaymentError>ok(response))
            .onErrorResume(error -> {
                log.error("Error processing network token webhook: {}", error.getMessage(), error);
                return Mono.just(Result.<com.hyperswitch.common.dto.NetworkTokenWebhookResponse, PaymentError>err(
                    PaymentError.of("NETWORK_TOKEN_WEBHOOK_PROCESSING_FAILED",
                        "Failed to process network token webhook: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.WebhookResponse, PaymentError>> processRecoveryWebhookV2(
            String merchantId,
            String profileId,
            String connectorId,
            com.hyperswitch.common.dto.WebhookRequest request) {
        log.info("Processing recovery webhook (v2) for merchant: {}, profile: {}, connector: {}", 
            merchantId, profileId, connectorId);
        
        // Extract payment ID from request data
        String paymentId = null;
        if (request.getData() != null) {
            Object paymentIdObj = request.getData().get("payment_id");
            if (paymentIdObj == null) {
                paymentIdObj = request.getData().get("paymentId");
            }
            if (paymentIdObj != null) {
                paymentId = paymentIdObj.toString();
            }
        }
        
        // In production, this would process the recovery webhook with profile context
        return processRecoveryWebhook(merchantId, paymentId, connectorId, request);
    }
    
    @Override
    public Flux<com.hyperswitch.storage.entity.WebhookEventEntity> listInitialWebhookAttempts(
            String merchantId,
            com.hyperswitch.common.dto.WebhookEventListRequest request) {
        log.info("Listing initial webhook attempts for merchant: {}", merchantId);
        
        return listWebhookEvents(
            merchantId,
            request.getEventType(),
            request.getConnector(),
            request.getDeliveryStatus(),
            request.getStartDate(),
            request.getEndDate(),
            request.getLimit(),
            request.getOffset()
        );
    }
    
    @Override
    public Flux<com.hyperswitch.common.dto.WebhookAttemptResponse> listWebhookAttempts(
            String merchantId,
            String initialAttemptId) {
        log.info("Listing webhook attempts for merchant: {}, initialAttempt: {}", merchantId, initialAttemptId);
        
        // In production, this would retrieve all attempts for a given initial attempt ID
        // For now, return the webhook event as a single attempt
        return webhookEventRepository.findByEventId(initialAttemptId)
            .map(entity -> {
                com.hyperswitch.common.dto.WebhookAttemptResponse response = 
                    new com.hyperswitch.common.dto.WebhookAttemptResponse();
                response.setAttemptId(entity.getId());
                response.setEventId(entity.getEventId());
                response.setStatus(entity.getDeliveryStatus());
                response.setAttemptedAt(entity.getLastAttemptAt() != null ? entity.getLastAttemptAt() : entity.getCreatedAt());
                response.setAttemptNumber(entity.getAttemptCount());
                return response;
            })
            .flux()
            .onErrorResume(error -> {
                log.error("Error listing webhook attempts: {}", error.getMessage(), error);
                return Flux.empty();
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> retryWebhookDeliveryWithMerchantId(
            String merchantId,
            String eventId) {
        log.info("Retrying webhook delivery for merchant: {}, event: {}", merchantId, eventId);
        
        // Verify the event belongs to the merchant
        return webhookEventRepository.findByEventId(eventId)
            .flatMap(entity -> {
                if (!merchantId.equals(entity.getMerchantId())) {
                    return Mono.just(Result.<Void, PaymentError>err(
                        PaymentError.of("WEBHOOK_EVENT_NOT_FOUND", 
                            "Webhook event not found for merchant: " + merchantId)
                    ));
                }
                return retryWebhookDelivery(eventId);
            })
            .switchIfEmpty(Mono.just(Result.<Void, PaymentError>err(
                PaymentError.of("WEBHOOK_EVENT_NOT_FOUND", "Webhook event not found: " + eventId)
            )));
    }
}

