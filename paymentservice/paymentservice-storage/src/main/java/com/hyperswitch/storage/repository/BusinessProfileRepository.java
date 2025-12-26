package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.BusinessProfileEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for BusinessProfile entity
 */
@Repository
public interface BusinessProfileRepository extends ReactiveCrudRepository<BusinessProfileEntity, String> {
    
    @Query("SELECT * FROM business_profile WHERE merchant_id = :merchantId")
    Flux<BusinessProfileEntity> findByMerchantId(String merchantId);
    
    @Query("SELECT * FROM business_profile WHERE profile_id = :profileId AND merchant_id = :merchantId")
    Mono<BusinessProfileEntity> findByProfileIdAndMerchantId(String profileId, String merchantId);
}

