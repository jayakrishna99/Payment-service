package com.hyperswitch.web.controller;

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
import reactor.core.publisher.Mono;

/**
 * REST controller for organization operations (v1 API)
 */
@RestController
@RequestMapping("/api/organization")
@Tag(name = "Organization", description = "Organization management operations (v1)")
public class OrganizationController {
    
    private final OrganizationService organizationService;
    
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    
    /**
     * Create organization
     * POST /api/organization
     */
    @PostMapping
    @Operation(
        summary = "Create organization",
        description = "Creates a new organization (v1 API)"
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
     * GET /api/organization/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Get organization",
        description = "Retrieves an organization by ID (v1 API)"
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
     * PUT /api/organization/{id}
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Update organization",
        description = "Updates an existing organization (v1 API)"
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
}

