package com.hyperswitch.storage.repository;

import com.hyperswitch.common.types.MandateStatus;
import com.hyperswitch.storage.entity.MandateEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Mandate entities
 */
public interface MandateRepository extends ReactiveCrudRepository<MandateEntity, String> {
    
    /**
     * Find mandate by mandate_id
     */
    Mono<MandateEntity> findByMandateId(String mandateId);
    
    /**
     * Find mandates by customer_id
     */
    Flux<MandateEntity> findByCustomerIdOrderByCreatedAtDesc(String customerId);
    
    /**
     * Find mandates by customer_id and status
     */
    Flux<MandateEntity> findByCustomerIdAndMandateStatusOrderByCreatedAtDesc(
            String customerId, MandateStatus status);
    
    /**
     * Find mandates by merchant_id with pagination
     */
    Flux<MandateEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId, Pageable pageable);
    
    /**
     * Find active mandates by customer_id and payment_method_id
     */
    @Query("SELECT * FROM mandate WHERE customer_id = :customerId " +
           "AND payment_method_id = :paymentMethodId " +
           "AND mandate_status = 'ACTIVE' " +
           "ORDER BY created_at DESC LIMIT 1")
    Mono<MandateEntity> findActiveMandateByCustomerAndPaymentMethod(
            String customerId, String paymentMethodId);
    
    /**
     * Find mandates by original_payment_id
     */
    Flux<MandateEntity> findByOriginalPaymentId(String originalPaymentId);
    
    /**
     * Check if mandate exists by mandate_id
     */
    Mono<Boolean> existsByMandateId(String mandateId);
}

