package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.ConnectorSuccessRateEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ConnectorSuccessRateRepository extends ReactiveCrudRepository<ConnectorSuccessRateEntity, String> {
    
    Flux<ConnectorSuccessRateEntity> findByMerchantIdAndProfileId(String merchantId, String profileId);
    
    Mono<ConnectorSuccessRateEntity> findByMerchantIdAndConnectorAndProfileIdAndPaymentMethodAndCurrency(
        String merchantId, 
        String connector, 
        String profileId,
        String paymentMethod,
        String currency
    );
    
    Flux<ConnectorSuccessRateEntity> findByMerchantIdAndProfileIdOrderBySuccessRateDesc(
        String merchantId, 
        String profileId
    );
    
    Mono<ConnectorSuccessRateEntity> findByMerchantIdAndConnector(
        String merchantId,
        String connector
    );
}

