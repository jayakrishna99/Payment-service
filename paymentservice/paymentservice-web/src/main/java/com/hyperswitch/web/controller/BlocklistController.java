package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.BlocklistRequest;
import com.hyperswitch.common.dto.BlocklistResponse;
import com.hyperswitch.common.dto.BlocklistToggleRequest;
import com.hyperswitch.core.blocklist.BlocklistService;
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
 * REST controller for blocklist operations
 */
@RestController
@RequestMapping("/api/blocklist")
@Tag(name = "Blocklist", description = "Blocklist management operations")
public class BlocklistController {
    
    private final BlocklistService blocklistService;
    
    @Autowired
    public BlocklistController(BlocklistService blocklistService) {
        this.blocklistService = blocklistService;
    }
    
    /**
     * List blocked payment methods
     * GET /api/blocklist
     */
    @GetMapping
    @Operation(
        summary = "List blocked payment methods",
        description = "Lists all blocked payment methods for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Blocklist retrieved successfully",
            content = @Content(schema = @Schema(implementation = BlocklistResponse.class))
        )
    })
    public Mono<ResponseEntity<BlocklistResponse>> listBlocklist(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(value = "data_kind", required = false) String dataKind) {
        return blocklistService.listBlocklist(merchantId, dataKind)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Add entry to blocklist
     * POST /api/blocklist
     */
    @PostMapping
    @Operation(
        summary = "Add entry to blocklist",
        description = "Adds a new entry to the blocklist"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Entry added successfully",
            content = @Content(schema = @Schema(implementation = BlocklistResponse.BlocklistEntry.class))
        ),
        @ApiResponse(responseCode = "409", description = "Entry already exists")
    })
    public Mono<ResponseEntity<BlocklistResponse.BlocklistEntry>> addToBlocklist(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody BlocklistRequest request) {
        return blocklistService.addToBlocklist(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Remove entry from blocklist
     * DELETE /api/blocklist
     */
    @DeleteMapping
    @Operation(
        summary = "Remove entry from blocklist",
        description = "Removes an entry from the blocklist"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Entry removed successfully"
        ),
        @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    public Mono<ResponseEntity<Void>> removeFromBlocklist(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam("fingerprint_id") String fingerprintId) {
        return blocklistService.removeFromBlocklist(merchantId, fingerprintId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Toggle blocklist guard
     * POST /api/blocklist/toggle
     */
    @PostMapping("/toggle")
    @Operation(
        summary = "Toggle blocklist guard",
        description = "Enables or disables the blocklist guard for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Blocklist guard toggled successfully"
        )
    })
    public Mono<ResponseEntity<Void>> toggleBlocklistGuard(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody BlocklistToggleRequest request) {
        return blocklistService.toggleBlocklistGuard(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

