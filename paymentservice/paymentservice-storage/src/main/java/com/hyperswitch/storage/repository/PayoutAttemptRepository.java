package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PayoutAttemptEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for payout attempt entities
 */
public interface PayoutAttemptRepository extends ReactiveCrudRepository<PayoutAttemptEntity, String> {

    Mono<PayoutAttemptEntity> findByPayoutAttemptId(String payoutAttemptId);

    Flux<PayoutAttemptEntity> findByPayoutId(String payoutId);

    Mono<PayoutAttemptEntity> findByPayoutIdAndMerchantId(String payoutId, String merchantId);
}

