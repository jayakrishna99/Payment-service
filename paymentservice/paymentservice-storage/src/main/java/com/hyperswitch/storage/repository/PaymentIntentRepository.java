package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PaymentIntentEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PaymentIntentRepository extends ReactiveCrudRepository<PaymentIntentEntity, String> {
    
    Mono<PaymentIntentEntity> findByPaymentIdAndMerchantId(String paymentId, String merchantId);
    
    Mono<PaymentIntentEntity> findByPaymentId(String paymentId);
    
    @Query("SELECT * FROM payment_intent WHERE merchant_id = :merchantId ORDER BY created_at DESC LIMIT :limit")
    reactor.core.publisher.Flux<PaymentIntentEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId, int limit);
    
    @Query("SELECT * FROM payment_intent WHERE merchant_id = :merchantId AND created_at >= :startDate AND created_at <= :endDate ORDER BY created_at DESC")
    reactor.core.publisher.Flux<PaymentIntentEntity> findByMerchantIdAndCreatedAtBetween(
        String merchantId, 
        java.time.Instant startDate, 
        java.time.Instant endDate
    );
}

