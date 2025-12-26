package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.ApplePayVerifiedDomainEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Apple Pay verified domains
 */
@Repository
public interface ApplePayVerifiedDomainRepository extends ReactiveCrudRepository<ApplePayVerifiedDomainEntity, Long> {
    
    @Query("SELECT * FROM apple_pay_verified_domains WHERE merchant_id = :merchantId AND merchant_connector_account_id = :mcaId")
    Flux<ApplePayVerifiedDomainEntity> findByMerchantIdAndMerchantConnectorAccountId(
            String merchantId, String mcaId);
    
    @Query("SELECT DISTINCT domain_name FROM apple_pay_verified_domains WHERE merchant_id = :merchantId AND merchant_connector_account_id = :mcaId")
    Flux<String> findDistinctDomainNamesByMerchantIdAndMerchantConnectorAccountId(
            String merchantId, String mcaId);
    
    @Query("SELECT * FROM apple_pay_verified_domains WHERE merchant_id = :merchantId AND merchant_connector_account_id = :mcaId AND domain_name = :domainName")
    Mono<ApplePayVerifiedDomainEntity> findByMerchantIdAndMerchantConnectorAccountIdAndDomainName(
            String merchantId, String mcaId, String domainName);
}

