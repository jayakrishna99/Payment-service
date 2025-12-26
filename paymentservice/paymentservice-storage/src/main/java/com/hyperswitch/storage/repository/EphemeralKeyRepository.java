package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.EphemeralKeyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EphemeralKeyRepository extends ReactiveCrudRepository<EphemeralKeyEntity, String> {
    
    Mono<EphemeralKeyEntity> findByIdAndMerchantId(String id, String merchantId);
    
    Mono<EphemeralKeyEntity> findBySecretAndMerchantId(String secret, String merchantId);
}

