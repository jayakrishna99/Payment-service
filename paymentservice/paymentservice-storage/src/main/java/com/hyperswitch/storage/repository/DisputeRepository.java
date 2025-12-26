package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.DisputeEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for dispute entities
 */
public interface DisputeRepository extends ReactiveCrudRepository<DisputeEntity, String> {

    Mono<DisputeEntity> findByDisputeId(String disputeId);

    Mono<DisputeEntity> findByMerchantIdAndDisputeId(String merchantId, String disputeId);

    Flux<DisputeEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);

    Flux<DisputeEntity> findByPaymentId(String paymentId);

    Flux<DisputeEntity> findByMerchantIdAndPaymentId(String merchantId, String paymentId);

    @Query("SELECT * FROM dispute WHERE merchant_id = :merchantId AND dispute_status = :status ORDER BY created_at DESC")
    Flux<DisputeEntity> findByMerchantIdAndDisputeStatus(String merchantId, String status);

    @Query("SELECT * FROM dispute WHERE merchant_id = :merchantId AND dispute_stage = :stage ORDER BY created_at DESC")
    Flux<DisputeEntity> findByMerchantIdAndDisputeStage(String merchantId, String stage);

    @Query("SELECT * FROM dispute WHERE merchant_id = :merchantId AND payment_id = :paymentId AND connector_dispute_id = :connectorDisputeId")
    Mono<DisputeEntity> findByMerchantIdAndPaymentIdAndConnectorDisputeId(
        String merchantId, 
        String paymentId, 
        String connectorDisputeId
    );
}

