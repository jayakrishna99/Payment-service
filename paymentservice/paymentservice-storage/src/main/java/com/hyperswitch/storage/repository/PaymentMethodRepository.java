package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PaymentMethodEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for PaymentMethod entities
 */
public interface PaymentMethodRepository extends ReactiveCrudRepository<PaymentMethodEntity, String> {
    
    /**
     * Find payment method by payment_method_id (business ID)
     */
    Mono<PaymentMethodEntity> findByPaymentMethodId(String paymentMethodId);
    
    /**
     * Find payment methods by customer_id
     */
    Flux<PaymentMethodEntity> findByCustomerIdOrderByCreatedAtDesc(String customerId);
    
    /**
     * Find payment methods by customer_id and status
     */
    Flux<PaymentMethodEntity> findByCustomerIdAndStatusOrderByCreatedAtDesc(String customerId, String status);
    
    /**
     * Find payment method by client_secret
     */
    Mono<PaymentMethodEntity> findByClientSecret(String clientSecret);
    
    /**
     * Find payment methods by merchant_id
     */
    Flux<PaymentMethodEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);
    
    /**
     * Check if payment method exists by payment_method_id
     */
    Mono<Boolean> existsByPaymentMethodId(String paymentMethodId);
    
    /**
     * Delete payment method by payment_method_id
     */
    Mono<Void> deleteByPaymentMethodId(String paymentMethodId);
}

