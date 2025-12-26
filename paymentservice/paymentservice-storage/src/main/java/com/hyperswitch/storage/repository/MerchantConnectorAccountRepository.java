package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.MerchantConnectorAccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for Merchant Connector Account entities
 */
public interface MerchantConnectorAccountRepository extends ReactiveCrudRepository<MerchantConnectorAccountEntity, String> {
    
    /**
     * Find by merchant connector ID
     */
    Mono<MerchantConnectorAccountEntity> findByMerchantConnectorId(String merchantConnectorId);
    
    /**
     * Find all by merchant ID
     */
    Flux<MerchantConnectorAccountEntity> findByMerchantId(String merchantId);
    
    /**
     * Find by merchant ID and connector name
     */
    Flux<MerchantConnectorAccountEntity> findByMerchantIdAndConnectorName(String merchantId, String connectorName);
    
    /**
     * Find by merchant ID, connector name, and profile ID
     */
    Mono<MerchantConnectorAccountEntity> findByMerchantIdAndConnectorNameAndProfileId(
        String merchantId, 
        String connectorName, 
        String profileId
    );
    
    /**
     * Check if exists by merchant connector ID
     */
    Mono<Boolean> existsByMerchantConnectorId(String merchantConnectorId);
}

