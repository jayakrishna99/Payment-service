package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.RoutingAlgorithmEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoutingAlgorithmRepository extends ReactiveCrudRepository<RoutingAlgorithmEntity, String> {
    
    Flux<RoutingAlgorithmEntity> findByMerchantId(String merchantId);
    
    Flux<RoutingAlgorithmEntity> findByMerchantIdAndProfileId(String merchantId, String profileId);
    
    Mono<RoutingAlgorithmEntity> findByMerchantIdAndIsActiveTrue(String merchantId);
    
    Mono<RoutingAlgorithmEntity> findByMerchantIdAndIsDefaultTrue(String merchantId);
    
    Mono<RoutingAlgorithmEntity> findByMerchantIdAndProfileIdAndIsDefaultTrue(String merchantId, String profileId);
    
    Flux<RoutingAlgorithmEntity> findByMerchantIdOrderByModifiedAtDesc(String merchantId);
    
    Mono<RoutingAlgorithmEntity> findByAlgorithmIdAndMerchantId(String algorithmId, String merchantId);
}

