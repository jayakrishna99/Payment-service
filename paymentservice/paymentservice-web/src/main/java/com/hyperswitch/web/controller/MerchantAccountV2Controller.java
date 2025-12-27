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
 * REST controller for merchant account operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/merchant-accounts")
@Tag(name = "Merchant Account V2", description = "Merchant account management operations (v2)")
public class MerchantAccountV2Controller {
    
    private final MerchantAccountService merchantAccountService;
    
    @Autowired
    public MerchantAccountV2Controller(MerchantAccountService merchantAccountService) {
        this.merchantAccountService = merchantAccountService;
    }
    
    /**
     * Create merchant account
     * POST /api/v2/merchant-accounts
     */
    @PostMapping
    @Operation(
        summary = "Create merchant account",
        description = "Creates a new merchant account for an organization"
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
            @RequestHeader("X-Organization-Id") String organizationId,
            @RequestBody MerchantAccountCreateRequest request) {
        return merchantAccountService.createMerchantAccount(organizationId, request)
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
     * GET /api/v2/merchant-accounts/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Get merchant account",
        description = "Retrieves a merchant account by ID"
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
     * PUT /api/v2/merchant-accounts/{id}
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Update merchant account",
        description = "Updates an existing merchant account"
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
     * List profiles for merchant account
     * GET /api/v2/merchant-accounts/{id}/profiles
     */
    @GetMapping("/{id}/profiles")
    @Operation(
        summary = "List profiles",
        description = "Lists all profiles for a merchant account"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Profiles retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<ProfileResponse>>> listProfiles(
            @PathVariable("id") String id) {
        return merchantAccountService.listProfiles(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Toggle KV mode
     * POST /api/v2/merchant-accounts/{id}/kv
     */
    @PostMapping("/{id}/kv")
    @Operation(
        summary = "Toggle KV mode",
        description = "Enables or disables KV (Key-Value) mode for a merchant account"
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
     * GET /api/v2/merchant-accounts/{id}/kv
     */
    @GetMapping("/{id}/kv")
    @Operation(
        summary = "Get KV status",
        description = "Retrieves the KV (Key-Value) mode status for a merchant account"
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
}

