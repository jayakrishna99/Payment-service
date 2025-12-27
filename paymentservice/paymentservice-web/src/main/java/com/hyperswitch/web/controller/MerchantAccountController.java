package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.merchantaccount.MerchantAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for merchant account operations (v1 API)
 */
@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Merchant Account", description = "Merchant account management operations (v1)")
public class MerchantAccountController {
    
    private final MerchantAccountService merchantAccountService;
    
    @Autowired
    public MerchantAccountController(MerchantAccountService merchantAccountService) {
        this.merchantAccountService = merchantAccountService;
    }
    
    /**
     * Create merchant account
     * POST /api/accounts
     */
    @PostMapping
    @Operation(
        summary = "Create merchant account",
        description = "Creates a new merchant account (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant account created successfully",
            content = @Content(schema = @Schema(implementation = MerchantAccountResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<MerchantAccountResponse>> createMerchantAccount(
            @RequestBody MerchantAccountCreateV1Request request) {
        return merchantAccountService.createMerchantAccountV1(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * List merchant accounts
     * GET /api/accounts/list
     */
    @GetMapping("/list")
    @Operation(
        summary = "List merchant accounts",
        description = "Lists all merchant accounts (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant accounts retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<MerchantAccountResponse>>> listMerchantAccounts() {
        return merchantAccountService.listMerchantAccounts()
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get merchant account
     * GET /api/accounts/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Get merchant account",
        description = "Retrieves a merchant account by ID (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant account retrieved successfully",
            content = @Content(schema = @Schema(implementation = MerchantAccountResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Merchant account not found")
    })
    public Mono<ResponseEntity<MerchantAccountResponse>> getMerchantAccount(
            @PathVariable("id") String id) {
        return merchantAccountService.getMerchantAccount(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update merchant account
     * POST /api/accounts/{id}
     */
    @PostMapping("/{id}")
    @Operation(
        summary = "Update merchant account",
        description = "Updates an existing merchant account (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant account updated successfully",
            content = @Content(schema = @Schema(implementation = MerchantAccountResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Merchant account not found")
    })
    public Mono<ResponseEntity<MerchantAccountResponse>> updateMerchantAccount(
            @PathVariable("id") String id,
            @RequestBody MerchantAccountUpdateRequest request) {
        return merchantAccountService.updateMerchantAccount(id, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Delete merchant account
     * DELETE /api/accounts/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete merchant account",
        description = "Deletes a merchant account (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant account deleted successfully"
        ),
        @ApiResponse(responseCode = "404", description = "Merchant account not found")
    })
    public Mono<ResponseEntity<Void>> deleteMerchantAccount(
            @PathVariable("id") String id) {
        return merchantAccountService.deleteMerchantAccount(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Toggle KV mode
     * POST /api/accounts/{id}/kv
     */
    @PostMapping("/{id}/kv")
    @Operation(
        summary = "Toggle KV mode",
        description = "Enables or disables KV (Key-Value) mode for a merchant account (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "KV mode toggled successfully",
            content = @Content(schema = @Schema(implementation = ToggleKVResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Merchant account not found")
    })
    public Mono<ResponseEntity<ToggleKVResponse>> toggleKV(
            @PathVariable("id") String id,
            @RequestBody ToggleKVRequest request) {
        return merchantAccountService.toggleKV(id, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get KV status
     * GET /api/accounts/{id}/kv
     */
    @GetMapping("/{id}/kv")
    @Operation(
        summary = "Get KV status",
        description = "Retrieves the KV (Key-Value) mode status for a merchant account (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "KV status retrieved successfully",
            content = @Content(schema = @Schema(implementation = ToggleKVResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Merchant account not found")
    })
    public Mono<ResponseEntity<ToggleKVResponse>> getKVStatus(
            @PathVariable("id") String id) {
        return merchantAccountService.getKVStatus(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Transfer keys
     * POST /api/accounts/transfer
     */
    @PostMapping("/transfer")
    @Operation(
        summary = "Transfer keys",
        description = "Transfers keys between merchant accounts (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Keys transferred successfully",
            content = @Content(schema = @Schema(implementation = TransferKeyResponse.class))
        )
    })
    public Mono<ResponseEntity<TransferKeyResponse>> transferKeys(
            @RequestBody MerchantKeyTransferRequest request) {
        return merchantAccountService.transferKeys(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Toggle all KV
     * POST /api/accounts/kv
     */
    @PostMapping("/kv")
    @Operation(
        summary = "Toggle all KV",
        description = "Toggles KV mode for all merchant accounts (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "KV mode toggled for all merchants",
            content = @Content(schema = @Schema(implementation = ToggleKVResponse.class))
        )
    })
    public Mono<ResponseEntity<ToggleKVResponse>> toggleAllKV(
            @RequestBody ToggleKVRequest request) {
        return merchantAccountService.toggleAllKV(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Enable platform account
     * POST /api/accounts/{id}/platform
     */
    @PostMapping("/{id}/platform")
    @Operation(
        summary = "Enable platform account",
        description = "Enables platform account functionality for a merchant (v1 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Platform account enabled successfully",
            content = @Content(schema = @Schema(implementation = MerchantAccountResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Merchant account not found")
    })
    public Mono<ResponseEntity<MerchantAccountResponse>> enablePlatformAccount(
            @PathVariable("id") String id) {
        return merchantAccountService.enablePlatformAccount(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

