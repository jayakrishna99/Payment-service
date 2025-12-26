package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PaymentSessionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for PaymentSession entities
 */
@Repository
public interface PaymentSessionRepository extends ReactiveCrudRepository<PaymentSessionEntity, String> {
    
    Mono<PaymentSessionEntity> findBySessionIdAndMerchantId(String sessionId, String merchantId);
    
    Mono<PaymentSessionEntity> findByPaymentIdAndMerchantId(String paymentId, String merchantId);
    
    Mono<PaymentSessionEntity> findBySessionToken(String sessionToken);
}

