package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.WebhookEventEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for WebhookEvent entities
 */
@Repository
public interface WebhookEventRepository extends ReactiveCrudRepository<WebhookEventEntity, String> {
    
    Mono<WebhookEventEntity> findByEventId(String eventId);
    
    Flux<WebhookEventEntity> findByMerchantId(String merchantId);
    
    Flux<WebhookEventEntity> findByPaymentId(String paymentId);
    
    Flux<WebhookEventEntity> findByMerchantIdAndDeliveryStatus(String merchantId, String deliveryStatus);
    
    Flux<WebhookEventEntity> findByMerchantIdAndEventType(String merchantId, String eventType);
    
    Flux<WebhookEventEntity> findByMerchantIdAndConnector(String merchantId, String connector);
    
    Flux<WebhookEventEntity> findByMerchantIdAndCreatedAtBetween(
        String merchantId, 
        java.time.Instant startDate, 
        java.time.Instant endDate
    );
    
    Flux<WebhookEventEntity> findByMerchantIdAndEventTypeAndCreatedAtBetween(
        String merchantId,
        String eventType,
        java.time.Instant startDate,
        java.time.Instant endDate
    );
}

