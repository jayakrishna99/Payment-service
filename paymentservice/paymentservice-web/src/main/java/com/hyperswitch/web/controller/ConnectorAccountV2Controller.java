package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.connectoraccount.ConnectorAccountService;
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
 * REST controller for connector account operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/connector-accounts")
@Tag(name = "Connector Account V2", description = "Connector account management operations (v2)")
public class ConnectorAccountV2Controller {
    
    private final ConnectorAccountService connectorAccountService;
    
    @Autowired
    public ConnectorAccountV2Controller(ConnectorAccountService connectorAccountService) {
        this.connectorAccountService = connectorAccountService;
    }
    
    /**
     * Create connector account
     * POST /api/v2/connector-accounts
     */
    @PostMapping
    @Operation(
        summary = "Create connector account",
        description = "Creates a new connector account for a merchant profile"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector account created successfully",
            content = @Content(schema = @Schema(implementation = ConnectorAccountResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorAccountResponse>> createConnectorAccount(
            @RequestBody ConnectorAccountCreateRequest request) {
        return connectorAccountService.createConnectorAccount(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get connector account
     * GET /api/v2/connector-accounts/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Get connector account",
        description = "Retrieves a connector account by ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector account retrieved successfully",
            content = @Content(schema = @Schema(implementation = ConnectorAccountResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Connector account not found")
    })
    public Mono<ResponseEntity<ConnectorAccountResponse>> getConnectorAccount(
            @PathVariable("id") String id) {
        return connectorAccountService.getConnectorAccount(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update connector account
     * PUT /api/v2/connector-accounts/{id}
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Update connector account",
        description = "Updates an existing connector account"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector account updated successfully",
            content = @Content(schema = @Schema(implementation = ConnectorAccountResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Connector account not found")
    })
    public Mono<ResponseEntity<ConnectorAccountResponse>> updateConnectorAccount(
            @PathVariable("id") String id,
            @RequestBody ConnectorAccountUpdateRequest request) {
        return connectorAccountService.updateConnectorAccount(id, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Delete connector account
     * DELETE /api/v2/connector-accounts/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete connector account",
        description = "Deletes a connector account"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector account deleted successfully",
            content = @Content(schema = @Schema(implementation = ConnectorAccountDeleteResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Connector account not found")
    })
    public Mono<ResponseEntity<ConnectorAccountDeleteResponse>> deleteConnectorAccount(
            @PathVariable("id") String id) {
        return connectorAccountService.deleteConnectorAccount(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

