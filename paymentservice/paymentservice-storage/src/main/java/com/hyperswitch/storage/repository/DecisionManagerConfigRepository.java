package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.DecisionManagerConfigEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DecisionManagerConfigRepository extends ReactiveCrudRepository<DecisionManagerConfigEntity, String> {
    
    Mono<DecisionManagerConfigEntity> findByMerchantIdAndProfileIdAndConfigType(
        String merchantId, 
        String profileId, 
        String configType
    );
    
    Mono<DecisionManagerConfigEntity> findByMerchantIdAndConfigType(
        String merchantId, 
        String configType
    );
}

