package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.CreateFileRequest;
import com.hyperswitch.common.dto.CreateFileResponse;
import com.hyperswitch.core.files.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for file operations
 */
@RestController
@RequestMapping("/api/files")
@Tag(name = "Files", description = "File management operations")
public class FileController {
    
    private final FileService fileService;
    
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    
    /**
     * Create file
     * POST /api/files
     */
    @PostMapping
    @Operation(
        summary = "Create file",
        description = "Creates a new file"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "File created successfully",
            content = @Content(schema = @Schema(implementation = CreateFileResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<CreateFileResponse>> createFile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody CreateFileRequest request) {
        return fileService.createFile(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Retrieve file
     * GET /api/files/{file_id}
     */
    @GetMapping("/{file_id}")
    @Operation(
        summary = "Retrieve file",
        description = "Retrieves a file by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "File retrieved successfully",
            content = @Content(schema = @Schema(implementation = CreateFileResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "File not found")
    })
    public Mono<ResponseEntity<CreateFileResponse>> getFile(
            @PathVariable("file_id") String fileId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return fileService.getFile(merchantId, fileId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Delete file
     * DELETE /api/files/{file_id}
     */
    @DeleteMapping("/{file_id}")
    @Operation(
        summary = "Delete file",
        description = "Deletes a file by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "File deleted successfully"
        ),
        @ApiResponse(responseCode = "404", description = "File not found")
    })
    public Mono<ResponseEntity<Void>> deleteFile(
            @PathVariable("file_id") String fileId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return fileService.deleteFile(merchantId, fileId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

