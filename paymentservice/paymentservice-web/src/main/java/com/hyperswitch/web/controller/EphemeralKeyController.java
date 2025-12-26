package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.EphemeralKeyRequest;
import com.hyperswitch.common.dto.EphemeralKeyResponse;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.core.ephemeralkeys.EphemeralKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for ephemeral key management
 */
@RestController
@RequestMapping("/api/ephemeral_keys")
@Tag(name = "Ephemeral Keys", description = "Manage ephemeral keys for secure client-side payment operations")
public class EphemeralKeyController {

    private final EphemeralKeyService ephemeralKeyService;

    @Autowired
    public EphemeralKeyController(EphemeralKeyService ephemeralKeyService) {
        this.ephemeralKeyService = ephemeralKeyService;
    }

    /**
     * Create an ephemeral key
     * POST /api/ephemeral_keys
     */
    @PostMapping
    @Operation(
        summary = "Create ephemeral key",
        description = "Creates a new ephemeral key for a customer. Ephemeral keys are short-lived keys that allow secure client-side operations without exposing the main API key."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Ephemeral key created successfully",
            content = @Content(schema = @Schema(implementation = EphemeralKeyResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request"
        )
    })
    public Mono<ResponseEntity<EphemeralKeyResponse>> createEphemeralKey(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Ephemeral key creation request", required = true)
            @RequestBody EphemeralKeyRequest request) {
        return ephemeralKeyService.createEphemeralKey(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Delete an ephemeral key
     * DELETE /api/ephemeral_keys/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete ephemeral key",
        description = "Deletes an ephemeral key. This revokes access for the key immediately."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ephemeral key deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Ephemeral key not found")
    })
    public Mono<ResponseEntity<EphemeralKeyResponse>> deleteEphemeralKey(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Ephemeral Key ID", required = true)
            @PathVariable String id) {
        return ephemeralKeyService.deleteEphemeralKey(merchantId, id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

