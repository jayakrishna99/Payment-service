package com.hyperswitch.web.controller;

import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.olap.OlapService;
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

import java.util.List;
import java.util.Map;

/**
 * REST controller for OLAP (Online Analytical Processing) operations
 * Provides ClickHouse integration endpoints for large-scale analytics
 */
@RestController
@RequestMapping("/api/olap")
@Tag(name = "OLAP", description = "OLAP operations with ClickHouse integration")
public class OlapController {
    
    private final OlapService olapService;
    
    @Autowired
    public OlapController(OlapService olapService) {
        this.olapService = olapService;
    }
    
    /**
     * Execute ClickHouse query
     * POST /api/olap/query
     */
    @PostMapping("/query")
    @Operation(
        summary = "Execute ClickHouse query",
        description = "Executes a ClickHouse query for analytics (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Query executed successfully",
            content = @Content(schema = @Schema(implementation = Map.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid query")
    })
    public Mono<ResponseEntity<List<Map<String, Object>>>> executeQuery(
            @RequestBody Map<String, String> request) {
        String query = request.get("query");
        if (query == null || query.trim().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        
        return olapService.executeQuery(query)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Health check for ClickHouse
     * GET /api/olap/health
     */
    @GetMapping("/health")
    @Operation(
        summary = "ClickHouse health check",
        description = "Checks the health of ClickHouse connection"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Health check completed"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> healthCheck() {
        return olapService.healthCheck()
            .map(result -> {
                Map<String, Object> response = new java.util.HashMap<>();
                if (result.isOk()) {
                    response.put("status", result.unwrap() ? "healthy" : "unhealthy");
                    response.put("enabled", olapService.isEnabled());
                    response.put("database", olapService.getDatabaseName());
                    return ResponseEntity.ok(response);
                } else {
                    response.put("status", "error");
                    response.put("error", result.unwrapErr().getMessage());
                    return ResponseEntity.ok(response);
                }
            });
    }
    
    /**
     * Get OLAP configuration
     * GET /api/olap/config
     */
    @GetMapping("/config")
    @Operation(
        summary = "Get OLAP configuration",
        description = "Retrieves OLAP/ClickHouse configuration (admin only)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Configuration retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, Object>>> getConfig() {
        Map<String, Object> config = new java.util.HashMap<>();
        config.put("enabled", olapService.isEnabled());
        config.put("database", olapService.getDatabaseName());
        return Mono.just(ResponseEntity.ok(config));
    }
}

