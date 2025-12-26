package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.BlocklistEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Blocklist entity
 */
@Repository
public interface BlocklistRepository extends ReactiveCrudRepository<BlocklistEntity, Long> {
    
    @Query("SELECT * FROM blocklist WHERE merchant_id = :merchantId AND fingerprint_id = :fingerprintId")
    Mono<BlocklistEntity> findByMerchantIdAndFingerprintId(String merchantId, String fingerprintId);
    
    @Query("SELECT * FROM blocklist WHERE merchant_id = :merchantId")
    Flux<BlocklistEntity> findByMerchantId(String merchantId);
    
    @Query("SELECT * FROM blocklist WHERE merchant_id = :merchantId AND data_kind = :dataKind")
    Flux<BlocklistEntity> findByMerchantIdAndDataKind(String merchantId, String dataKind);
}

