package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.RoutingConfigEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoutingConfigRepository extends ReactiveCrudRepository<RoutingConfigEntity, String> {
    
    Flux<RoutingConfigEntity> findByMerchantIdAndEnabledTrue(String merchantId);
    
    Flux<RoutingConfigEntity> findByMerchantIdAndProfileIdAndEnabledTrue(String merchantId, String profileId);
    
    Mono<RoutingConfigEntity> findByMerchantIdAndConnectorAndProfileId(String merchantId, String connector, String profileId);
}

