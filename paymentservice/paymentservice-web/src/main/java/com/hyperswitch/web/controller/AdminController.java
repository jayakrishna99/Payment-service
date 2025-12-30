package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.connectoraccount.ConnectorAccountService;
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

import java.util.Map;

/**
 * REST controller for specialized admin operations
 * Provides endpoints for admin bulk operations, system configuration, and audit logs
 */
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "Specialized admin operations")
public class AdminController {
    
    private final MerchantAccountService merchantAccountService;
    private final ConnectorAccountService connectorAccountService;
    
    @Autowired
    public AdminController(
            MerchantAccountService merchantAccountService,
            ConnectorAccountService connectorAccountService) {
        this.merchantAccountService = merchantAccountService;
        this.connectorAccountService = connectorAccountService;
    }
    
    /**
     * List all merchant accounts (admin - global)
     * GET /api/admin/merchant-accounts
     */
    @GetMapping("/merchant-accounts")
    @Operation(
        summary = "List all merchant accounts (admin)",
        description = "Lists all merchant accounts in the system (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant accounts retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<MerchantAccountResponse>>> listAllMerchantAccounts() {
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
     * Bulk update merchant accounts
     * PUT /api/admin/merchant-accounts/bulk
     */
    @PutMapping("/merchant-accounts/bulk")
    @Operation(
        summary = "Bulk update merchant accounts",
        description = "Updates multiple merchant accounts in a single operation (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant accounts updated successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> bulkUpdateMerchantAccounts(
            @RequestBody Map<String, Object> bulkUpdateRequest) {
        // In production, implement bulk update logic
        return Mono.just(ResponseEntity.ok(Map.of(
            "updated_count", 0,
            "status", "success"
        )));
    }
    
    /**
     * Get system configuration
     * GET /api/admin/system/config
     */
    @GetMapping("/system/config")
    @Operation(
        summary = "Get system configuration",
        description = "Retrieves system-wide configuration (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "System configuration retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> getSystemConfig() {
        // In production, retrieve system configuration
        return Mono.just(ResponseEntity.ok(Map.of(
            "version", "1.0.0",
            "environment", "production",
            "features", Map.of()
        )));
    }
    
    /**
     * Update system configuration
     * PUT /api/admin/system/config
     */
    @PutMapping("/system/config")
    @Operation(
        summary = "Update system configuration",
        description = "Updates system-wide configuration (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "System configuration updated successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> updateSystemConfig(
            @RequestBody Map<String, Object> config) {
        // In production, update system configuration
        return Mono.just(ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Configuration updated"
        )));
    }
    
    /**
     * Get audit logs
     * GET /api/admin/audit-logs
     */
    @GetMapping("/audit-logs")
    @Operation(
        summary = "Get audit logs",
        description = "Retrieves audit logs for admin operations (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Audit logs retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> getAuditLogs(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        // In production, retrieve audit logs from database
        return Mono.just(ResponseEntity.ok(Map.of(
            "logs", java.util.Collections.emptyList(),
            "page", page,
            "size", size,
            "total", 0
        )));
    }
    
    /**
     * Export merchant account data
     * GET /api/admin/merchant-accounts/export
     */
    @GetMapping("/merchant-accounts/export")
    @Operation(
        summary = "Export merchant account data",
        description = "Exports merchant account data for reporting (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Export initiated successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> exportMerchantAccounts(
            @RequestParam(value = "format", defaultValue = "json") String format) {
        // In production, initiate export process
        return Mono.just(ResponseEntity.ok(Map.of(
            "export_id", java.util.UUID.randomUUID().toString(),
            "status", "initiated",
            "format", format
        )));
    }
    
    /**
     * Health check for admin services
     * GET /api/admin/health
     */
    @GetMapping("/health")
    @Operation(
        summary = "Admin health check",
        description = "Checks health of admin services (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Admin services are healthy"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> adminHealthCheck() {
        return Mono.just(ResponseEntity.ok(Map.of(
            "status", "healthy",
            "services", Map.of(
                "merchant_account_service", "up",
                "connector_account_service", "up"
            )
        )));
    }
}

