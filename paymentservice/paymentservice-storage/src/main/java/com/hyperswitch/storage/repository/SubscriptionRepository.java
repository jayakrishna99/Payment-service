package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.SubscriptionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for subscription entities
 */
public interface SubscriptionRepository extends ReactiveCrudRepository<SubscriptionEntity, String> {

    Mono<SubscriptionEntity> findBySubscriptionId(String subscriptionId);

    Mono<SubscriptionEntity> findByMerchantIdAndSubscriptionId(String merchantId, String subscriptionId);

    Flux<SubscriptionEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);

    Flux<SubscriptionEntity> findByCustomerId(String customerId);

    Flux<SubscriptionEntity> findByMerchantIdAndCustomerId(String merchantId, String customerId);

    Flux<SubscriptionEntity> findByMerchantIdAndStatus(String merchantId, String status);
}

