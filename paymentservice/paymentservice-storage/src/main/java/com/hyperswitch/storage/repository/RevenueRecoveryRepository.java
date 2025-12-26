package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.RevenueRecoveryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Reactive repository for revenue recovery entities
 */
public interface RevenueRecoveryRepository extends ReactiveCrudRepository<RevenueRecoveryEntity, String> {

    Mono<RevenueRecoveryEntity> findByRecoveryId(String recoveryId);

    Mono<RevenueRecoveryEntity> findByPaymentIdAndAttemptId(String paymentId, String attemptId);

    Flux<RevenueRecoveryEntity> findByMerchantId(String merchantId);

    Flux<RevenueRecoveryEntity> findByMerchantIdAndRecoveryStatus(String merchantId, String status);

    Flux<RevenueRecoveryEntity> findByRecoveryStatusAndNextRetryAtBefore(String status, Instant before);

    Flux<RevenueRecoveryEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);
}

