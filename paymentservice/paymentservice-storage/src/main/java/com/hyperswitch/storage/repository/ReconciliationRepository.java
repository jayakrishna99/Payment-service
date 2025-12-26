package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.ReconciliationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for reconciliation entities
 */
public interface ReconciliationRepository extends ReactiveCrudRepository<ReconciliationEntity, String> {

    Mono<ReconciliationEntity> findByReconciliationId(String reconciliationId);

    Flux<ReconciliationEntity> findByMerchantId(String merchantId);

    Flux<ReconciliationEntity> findByMerchantIdAndStatus(String merchantId, String status);

    Flux<ReconciliationEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);
}

