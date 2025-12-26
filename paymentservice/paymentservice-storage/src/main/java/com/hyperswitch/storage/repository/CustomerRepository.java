package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Customer entities
 */
public interface CustomerRepository extends ReactiveCrudRepository<CustomerEntity, String> {
    
    /**
     * Find customer by customer_id (business ID)
     */
    Mono<CustomerEntity> findByCustomerId(String customerId);
    
    /**
     * Find customer by merchant_id and customer_id
     */
    Mono<CustomerEntity> findByMerchantIdAndCustomerId(String merchantId, String customerId);
    
    /**
     * Find customers by merchant_id with pagination
     */
    Flux<CustomerEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId, Pageable pageable);
    
    /**
     * Find customers by merchant_id without pagination
     */
    Flux<CustomerEntity> findByMerchantIdOrderByCreatedAtDesc(String merchantId);
    
    /**
     * Find customer by email
     */
    @Query("SELECT * FROM customer WHERE email = :email AND merchant_id = :merchantId LIMIT 1")
    Mono<CustomerEntity> findByEmailAndMerchantId(String email, String merchantId);
    
    /**
     * Check if customer exists by customer_id
     */
    Mono<Boolean> existsByCustomerId(String customerId);
    
    /**
     * Delete customer by customer_id (soft delete would be better, but for now hard delete)
     */
    Mono<Void> deleteByCustomerId(String customerId);
}

