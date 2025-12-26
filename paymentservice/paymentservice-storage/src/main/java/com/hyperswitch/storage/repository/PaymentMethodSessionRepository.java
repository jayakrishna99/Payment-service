package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PaymentMethodSessionEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Repository for Payment Method Session entities
 */
@Repository
public interface PaymentMethodSessionRepository extends ReactiveCrudRepository<PaymentMethodSessionEntity, String> {
    
    /**
     * Find payment method session by merchant ID and ID
     */
    @Query("SELECT * FROM payment_method_session WHERE id = :id AND merchant_id = :merchantId")
    Mono<PaymentMethodSessionEntity> findByIdAndMerchantId(String id, String merchantId);
    
    /**
     * Find payment method session by customer ID
     */
    @Query("SELECT * FROM payment_method_session WHERE customer_id = :customerId AND merchant_id = :merchantId ORDER BY created_at DESC")
    reactor.core.publisher.Flux<PaymentMethodSessionEntity> findByCustomerIdAndMerchantId(String customerId, String merchantId);
    
    /**
     * Delete expired sessions
     */
    @Query("DELETE FROM payment_method_session WHERE expires_at < :now")
    Mono<Void> deleteExpiredSessions(Instant now);
}

