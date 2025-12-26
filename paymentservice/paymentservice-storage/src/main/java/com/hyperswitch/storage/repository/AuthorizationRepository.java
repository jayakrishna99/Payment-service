package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.AuthorizationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Authorization entities
 */
@Repository
public interface AuthorizationRepository extends ReactiveCrudRepository<AuthorizationEntity, String> {
    
    Mono<AuthorizationEntity> findByAuthorizationIdAndMerchantId(String authorizationId, String merchantId);
    
    Flux<AuthorizationEntity> findByPaymentIdAndMerchantId(String paymentId, String merchantId);
    
    Mono<AuthorizationEntity> findByPaymentIdAndMerchantIdOrderByCreatedAtDesc(String paymentId, String merchantId);
}

