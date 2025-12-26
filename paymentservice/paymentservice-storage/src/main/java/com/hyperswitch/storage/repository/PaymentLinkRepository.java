package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PaymentLinkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for PaymentLink entities
 */
public interface PaymentLinkRepository extends ReactiveCrudRepository<PaymentLinkEntity, String> {
    
    /**
     * Find payment link by payment_link_id
     */
    Mono<PaymentLinkEntity> findByPaymentLinkId(String paymentLinkId);
    
    /**
     * Find payment link by payment_id
     */
    Mono<PaymentLinkEntity> findByPaymentId(String paymentId);
    
    /**
     * Find payment links by merchant_id with pagination
     */
    Flux<PaymentLinkEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId, Pageable pageable);
    
    /**
     * Find payment links by merchant_id and payment_id
     */
    Mono<PaymentLinkEntity> findByMerchantIdAndPaymentId(String merchantId, String paymentId);
    
    /**
     * Check if payment link exists by payment_link_id
     */
    Mono<Boolean> existsByPaymentLinkId(String paymentLinkId);
    
    /**
     * Find active (non-expired) payment links by merchant_id
     */
    @Query("SELECT * FROM payment_link WHERE merchant_id = :merchantId " +
           "AND (expires_at IS NULL OR expires_at > CURRENT_TIMESTAMP) " +
           "ORDER BY created_at DESC")
    Flux<PaymentLinkEntity> findActivePaymentLinksByMerchantId(String merchantId, Pageable pageable);
}

