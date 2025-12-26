package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PayoutEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for payout entities
 */
public interface PayoutRepository extends ReactiveCrudRepository<PayoutEntity, String> {

    Mono<PayoutEntity> findByPayoutId(String payoutId);

    Mono<PayoutEntity> findByMerchantIdAndPayoutId(String merchantId, String payoutId);

    Flux<PayoutEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);

    Flux<PayoutEntity> findByMerchantIdAndStatus(String merchantId, String status);
}

