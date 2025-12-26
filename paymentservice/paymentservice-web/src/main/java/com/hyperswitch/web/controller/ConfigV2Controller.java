package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ConfigRequest;
import com.hyperswitch.common.dto.ConfigResponse;
import com.hyperswitch.core.configs.ConfigService;
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
 * REST controller for config operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/configs")
@Tag(name = "Configs V2", description = "Configuration key management operations (v2 API)")
public class ConfigV2Controller {
    
    private final ConfigService configService;
    
    @Autowired
    public ConfigV2Controller(ConfigService configService) {
        this.configService = configService;
    }
    
    /**
     * Create config key (v2 API)
     * POST /api/v2/configs/
     */
    @PostMapping
    @Operation(
        summary = "Create config key (v2)",
        description = "Creates a new configuration key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Config created successfully",
            content = @Content(schema = @Schema(implementation = ConfigResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "409", description = "Config already exists")
    })
    public Mono<ResponseEntity<ConfigResponse>> createConfig(@RequestBody ConfigRequest request) {
        return configService.createConfig(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Retrieve config key (v2 API)
     * GET /api/v2/configs/{key}
     */
    @GetMapping("/{key}")
    @Operation(
        summary = "Retrieve config key (v2)",
        description = "Retrieves a configuration key by its key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Config retrieved successfully",
            content = @Content(schema = @Schema(implementation = ConfigResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Config not found")
    })
    public Mono<ResponseEntity<ConfigResponse>> getConfig(@PathVariable("key") String key) {
        return configService.getConfig(key)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update config key (v2 API)
     * POST /api/v2/configs/{key}
     */
    @PostMapping("/{key}")
    @Operation(
        summary = "Update config key (v2)",
        description = "Updates an existing configuration key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Config updated successfully",
            content = @Content(schema = @Schema(implementation = ConfigResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Config not found")
    })
    public Mono<ResponseEntity<ConfigResponse>> updateConfig(
            @PathVariable("key") String key,
            @RequestBody ConfigRequest request) {
        return configService.updateConfig(key, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Delete config key (v2 API)
     * DELETE /api/v2/configs/{key}
     */
    @DeleteMapping("/{key}")
    @Operation(
        summary = "Delete config key (v2)",
        description = "Deletes a configuration key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Config deleted successfully",
            content = @Content(schema = @Schema(implementation = ConfigResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Config not found")
    })
    public Mono<ResponseEntity<ConfigResponse>> deleteConfig(@PathVariable("key") String key) {
        return configService.deleteConfig(key)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

