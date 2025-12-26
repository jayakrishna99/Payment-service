package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.FraudCheckEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for fraud check entities
 */
public interface FraudCheckRepository extends ReactiveCrudRepository<FraudCheckEntity, String> {

    Mono<FraudCheckEntity> findByFrmId(String frmId);

    Mono<FraudCheckEntity> findByPaymentIdAndAttemptId(String paymentId, String attemptId);

    Flux<FraudCheckEntity> findByPaymentId(String paymentId);

    Flux<FraudCheckEntity> findByMerchantIdAndPaymentId(String merchantId, String paymentId);

    Flux<FraudCheckEntity> findByMerchantIdAndFrmStatus(String merchantId, String status);
}

