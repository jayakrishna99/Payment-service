package com.hyperswitch.core.files.impl;

import com.hyperswitch.common.dto.CreateFileRequest;
import com.hyperswitch.common.dto.CreateFileResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.files.FileService;
import com.hyperswitch.storage.entity.FileMetadataEntity;
import com.hyperswitch.storage.repository.FileMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of FileService
 */
@Service
public class FileServiceImpl implements FileService {
    
    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    
    private final FileMetadataRepository fileMetadataRepository;
    
    @Autowired
    public FileServiceImpl(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }
    
    @Override
    public Mono<Result<CreateFileResponse, PaymentError>> createFile(
            String merchantId,
            CreateFileRequest request) {
        log.info("Creating file for merchant: {}, fileName: {}", merchantId, request.getFileName());
        
        String fileId = "file_" + UUID.randomUUID().toString().replace("-", "");
        Instant now = Instant.now();
        
        FileMetadataEntity entity = new FileMetadataEntity();
        entity.setFileId(fileId);
        entity.setMerchantId(merchantId);
        entity.setFileName(request.getFileName());
        entity.setFileSize(request.getFileSize() != null ? request.getFileSize() : 0);
        entity.setFileType(request.getFileType());
        entity.setAvailable(false);
        entity.setCreatedAt(now);
        
        // In production, this would upload the file to a storage provider (S3, etc.)
        // For now, just save metadata
        return fileMetadataRepository.save(entity)
            .map(this::toCreateFileResponse)
            .map(Result::<CreateFileResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error creating file", error);
                return Mono.just(Result.<CreateFileResponse, PaymentError>err(
                    PaymentError.of("FILE_CREATE_FAILED",
                        "Failed to create file: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<CreateFileResponse, PaymentError>> getFile(
            String merchantId,
            String fileId) {
        log.info("Getting file: {} for merchant: {}", fileId, merchantId);
        
        return fileMetadataRepository.findByFileIdAndMerchantId(fileId, merchantId)
            .map(this::toCreateFileResponse)
            .map(Result::<CreateFileResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<CreateFileResponse, PaymentError>err(
                PaymentError.of("FILE_NOT_FOUND",
                    "File with ID " + fileId + " not found")
            )))
            .onErrorResume(error -> {
                log.error("Error getting file", error);
                return Mono.just(Result.<CreateFileResponse, PaymentError>err(
                    PaymentError.of("FILE_RETRIEVAL_FAILED",
                        "Failed to get file: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> deleteFile(
            String merchantId,
            String fileId) {
        log.info("Deleting file: {} for merchant: {}", fileId, merchantId);
        
        return fileMetadataRepository.findByFileIdAndMerchantId(fileId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException("File not found")))
            .flatMap(entity -> {
                // In production, this would delete the file from storage provider
                return fileMetadataRepository.delete(entity)
                    .thenReturn(Result.<Void, PaymentError>ok(null));
            })
            .onErrorResume(error -> {
                log.error("Error deleting file", error);
                if (error.getMessage() != null && error.getMessage().contains("not found")) {
                    return Mono.just(Result.<Void, PaymentError>err(
                        PaymentError.of("FILE_NOT_FOUND",
                            "File with ID " + fileId + " not found")
                    ));
                }
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of("FILE_DELETE_FAILED",
                        "Failed to delete file: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Convert FileMetadataEntity to CreateFileResponse
     */
    private CreateFileResponse toCreateFileResponse(FileMetadataEntity entity) {
        CreateFileResponse response = new CreateFileResponse();
        response.setFileId(entity.getFileId());
        response.setFileName(entity.getFileName());
        response.setFileSize(entity.getFileSize());
        response.setFileType(entity.getFileType());
        response.setFileUrl("/api/files/" + entity.getFileId());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}

