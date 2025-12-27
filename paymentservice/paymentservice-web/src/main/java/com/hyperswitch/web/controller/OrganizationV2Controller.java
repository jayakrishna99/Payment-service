package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.MerchantAccountResponse;
import com.hyperswitch.common.dto.OrganizationRequest;
import com.hyperswitch.common.dto.OrganizationResponse;
import com.hyperswitch.core.organization.OrganizationService;
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
 * REST controller for organization operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/organizations")
@Tag(name = "Organization V2", description = "Organization management operations (v2)")
public class OrganizationV2Controller {
    
    private final OrganizationService organizationService;
    
    @Autowired
    public OrganizationV2Controller(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    
    /**
     * Create organization
     * POST /api/v2/organizations
     */
    @PostMapping
    @Operation(
        summary = "Create organization",
        description = "Creates a new organization"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Organization created successfully",
            content = @Content(schema = @Schema(implementation = OrganizationResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<OrganizationResponse>> createOrganization(
            @RequestBody OrganizationRequest request) {
        return organizationService.createOrganization(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get organization
     * GET /api/v2/organizations/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Get organization",
        description = "Retrieves an organization by ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Organization retrieved successfully",
            content = @Content(schema = @Schema(implementation = OrganizationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    public Mono<ResponseEntity<OrganizationResponse>> getOrganization(
            @PathVariable("id") String id) {
        return organizationService.getOrganization(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Update organization
     * PUT /api/v2/organizations/{id}
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Update organization",
        description = "Updates an existing organization"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Organization updated successfully",
            content = @Content(schema = @Schema(implementation = OrganizationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    public Mono<ResponseEntity<OrganizationResponse>> updateOrganization(
            @PathVariable("id") String id,
            @RequestBody OrganizationRequest request) {
        return organizationService.updateOrganization(id, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * List merchant accounts for organization
     * GET /api/v2/organizations/{id}/merchant-accounts
     */
    @GetMapping("/{id}/merchant-accounts")
    @Operation(
        summary = "List merchant accounts",
        description = "Lists all merchant accounts for an organization"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant accounts retrieved successfully"
        )
    })
    public Mono<ResponseEntity<Flux<MerchantAccountResponse>>> listMerchantAccounts(
            @PathVariable("id") String id) {
        return organizationService.listMerchantAccounts(id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

