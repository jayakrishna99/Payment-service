package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.AuthenticationEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Authentication entity
 */
@Repository
public interface AuthenticationRepository extends R2dbcRepository<AuthenticationEntity, String> {
    
    Mono<AuthenticationEntity> findByAuthenticationId(String authenticationId);
    
    Flux<AuthenticationEntity> findByMerchantId(String merchantId);
    
    Flux<AuthenticationEntity> findByPaymentId(String paymentId);
    
    Flux<AuthenticationEntity> findByPaymentMethodId(String paymentMethodId);
    
    Mono<AuthenticationEntity> findByAuthenticationIdAndMerchantId(String authenticationId, String merchantId);
}

