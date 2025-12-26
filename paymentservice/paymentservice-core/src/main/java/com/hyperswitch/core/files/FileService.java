package com.hyperswitch.core.files;

import com.hyperswitch.common.dto.CreateFileRequest;
import com.hyperswitch.common.dto.CreateFileResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for file operations
 */
public interface FileService {
    
    /**
     * Create file
     */
    Mono<Result<CreateFileResponse, PaymentError>> createFile(
            String merchantId,
            CreateFileRequest request);
    
    /**
     * Retrieve file
     */
    Mono<Result<CreateFileResponse, PaymentError>> getFile(
            String merchantId,
            String fileId);
    
    /**
     * Delete file
     */
    Mono<Result<Void, PaymentError>> deleteFile(
            String merchantId,
            String fileId);
}

