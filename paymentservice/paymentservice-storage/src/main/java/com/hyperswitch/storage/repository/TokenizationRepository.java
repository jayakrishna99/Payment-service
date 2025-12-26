package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.TokenizationEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Tokenization entity
 */
@Repository
public interface TokenizationRepository extends ReactiveCrudRepository<TokenizationEntity, String> {
    
    @Query("SELECT * FROM tokenization WHERE id = :id AND merchant_id = :merchantId")
    Mono<TokenizationEntity> findByIdAndMerchantId(String id, String merchantId);
    
    @Query("SELECT * FROM tokenization WHERE customer_id = :customerId AND merchant_id = :merchantId")
    Mono<TokenizationEntity> findByCustomerIdAndMerchantId(String customerId, String merchantId);
}

