package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ConnectorVerifyRequest;
import com.hyperswitch.common.dto.ConnectorVerifyResponse;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.dto.MerchantConnectorAccountRequest;
import com.hyperswitch.common.dto.MerchantConnectorAccountResponse;
import com.hyperswitch.core.connectors.MerchantConnectorAccountService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for merchant connector account management
 */
@RestController
@RequestMapping("/api/account")
@Tag(name = "Merchant Connector Accounts", description = "Manage merchant connector account configurations including create, list, update, delete, and verify operations")
public class MerchantConnectorAccountController {

    private final MerchantConnectorAccountService connectorAccountService;

    @Autowired
    public MerchantConnectorAccountController(MerchantConnectorAccountService connectorAccountService) {
        this.connectorAccountService = connectorAccountService;
    }

    /**
     * Create a new merchant connector account
     * POST /api/account/{merchantId}/connectors
     */
    @PostMapping("/{merchantId}/connectors")
    @Operation(
        summary = "Create merchant connector account",
        description = "Creates a new connector account configuration for a merchant. This allows the merchant to process payments through the specified connector."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Connector account created successfully",
            content = @Content(schema = @Schema(implementation = MerchantConnectorAccountResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content(schema = @Schema(implementation = com.hyperswitch.common.dto.ErrorResponse.class))
        )
    })
    public Mono<ResponseEntity<MerchantConnectorAccountResponse>> createConnectorAccount(
            @Parameter(description = "Merchant ID", required = true)
            @PathVariable String merchantId,
            @Parameter(description = "Connector account creation request", required = true)
            @RequestBody MerchantConnectorAccountRequest request) {
        return connectorAccountService.createConnectorAccount(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * List all connector accounts for a merchant
     * GET /api/account/{merchantId}/connectors
     */
    @GetMapping("/{merchantId}/connectors")
    @Operation(
        summary = "List merchant connector accounts",
        description = "Retrieves all connector account configurations for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector accounts retrieved successfully",
            content = @Content(schema = @Schema(implementation = MerchantConnectorAccountResponse.class))
        )
    })
    public Mono<ResponseEntity<Flux<MerchantConnectorAccountResponse>>> listConnectorAccounts(
            @Parameter(description = "Merchant ID", required = true)
            @PathVariable String merchantId) {
        return connectorAccountService.listConnectorAccounts(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Get a specific connector account
     * GET /api/account/{merchantId}/connectors/{merchantConnectorId}
     */
    @GetMapping("/{merchantId}/connectors/{merchantConnectorId}")
    @Operation(
        summary = "Get merchant connector account",
        description = "Retrieves a specific connector account configuration by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector account found",
            content = @Content(schema = @Schema(implementation = MerchantConnectorAccountResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Connector account not found"
        )
    })
    public Mono<ResponseEntity<MerchantConnectorAccountResponse>> getConnectorAccount(
            @Parameter(description = "Merchant ID", required = true)
            @PathVariable String merchantId,
            @Parameter(description = "Merchant Connector ID", required = true)
            @PathVariable String merchantConnectorId) {
        return connectorAccountService.getConnectorAccount(merchantId, merchantConnectorId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Update a merchant connector account
     * POST /api/account/{merchantId}/connectors/{merchantConnectorId}
     */
    @PostMapping("/{merchantId}/connectors/{merchantConnectorId}")
    @Operation(
        summary = "Update merchant connector account",
        description = "Updates an existing connector account configuration. Only provided fields will be updated."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector account updated successfully",
            content = @Content(schema = @Schema(implementation = MerchantConnectorAccountResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Connector account not found"
        )
    })
    public Mono<ResponseEntity<MerchantConnectorAccountResponse>> updateConnectorAccount(
            @Parameter(description = "Merchant ID", required = true)
            @PathVariable String merchantId,
            @Parameter(description = "Merchant Connector ID", required = true)
            @PathVariable String merchantConnectorId,
            @Parameter(description = "Connector account update request", required = true)
            @RequestBody MerchantConnectorAccountRequest request) {
        return connectorAccountService.updateConnectorAccount(merchantId, merchantConnectorId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Delete a merchant connector account
     * DELETE /api/account/{merchantId}/connectors/{merchantConnectorId}
     */
    @DeleteMapping("/{merchantId}/connectors/{merchantConnectorId}")
    @Operation(
        summary = "Delete merchant connector account",
        description = "Deletes a connector account configuration. This will prevent the merchant from processing payments through this connector."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Connector account deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Connector account not found")
    })
    public Mono<ResponseEntity<Void>> deleteConnectorAccount(
            @Parameter(description = "Merchant ID", required = true)
            @PathVariable String merchantId,
            @Parameter(description = "Merchant Connector ID", required = true)
            @PathVariable String merchantConnectorId) {
        return connectorAccountService.deleteConnectorAccount(merchantId, merchantConnectorId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.noContent().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Verify connector account credentials
     * POST /api/account/connectors/verify
     */
    @PostMapping("/connectors/verify")
    @Operation(
        summary = "Verify connector account",
        description = "Verifies the credentials and configuration of a connector account. This tests the connection to the payment processor."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector verification result",
            content = @Content(schema = @Schema(implementation = ConnectorVerifyResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid verification request"
        )
    })
    public Mono<ResponseEntity<ConnectorVerifyResponse>> verifyConnector(
            @Parameter(description = "Connector verification request", required = true)
            @RequestBody ConnectorVerifyRequest request) {
        return connectorAccountService.verifyConnector(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

