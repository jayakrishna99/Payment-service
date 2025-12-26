package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.FileMetadataEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for FileMetadata entity
 */
@Repository
public interface FileMetadataRepository extends ReactiveCrudRepository<FileMetadataEntity, String> {
    
    @Query("SELECT * FROM file_metadata WHERE file_id = :fileId AND merchant_id = :merchantId")
    Mono<FileMetadataEntity> findByFileIdAndMerchantId(String fileId, String merchantId);
    
    Mono<FileMetadataEntity> findByFileId(String fileId);
}

