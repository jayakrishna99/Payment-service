package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.HypersenseTokenResponse;
import com.hyperswitch.common.dto.VerifyTokenRequest;
import com.hyperswitch.common.dto.VerifyTokenResponse;
import com.hyperswitch.core.hypersense.HypersenseService;
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
 * REST controller for Hypersense token operations
 */
@RestController
@RequestMapping("/api/hypersense")
@Tag(name = "Hypersense", description = "Hypersense token management operations")
public class HypersenseController {
    
    private final HypersenseService hypersenseService;
    
    @Autowired
    public HypersenseController(HypersenseService hypersenseService) {
        this.hypersenseService = hypersenseService;
    }
    
    /**
     * Get Hypersense token
     * GET /api/hypersense/token
     */
    @GetMapping("/token")
    @Operation(
        summary = "Get Hypersense token",
        description = "Retrieves a Hypersense authentication token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token retrieved successfully",
            content = @Content(schema = @Schema(implementation = HypersenseTokenResponse.class))
        ),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public Mono<ResponseEntity<HypersenseTokenResponse>> getToken(
            @RequestHeader("merchant_id") String merchantId) {
        return hypersenseService.getToken(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Verify Hypersense token
     * POST /api/hypersense/verify_token
     */
    @PostMapping("/verify_token")
    @Operation(
        summary = "Verify Hypersense token",
        description = "Verifies a Hypersense authentication token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token verified successfully",
            content = @Content(schema = @Schema(implementation = VerifyTokenResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<VerifyTokenResponse>> verifyToken(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody VerifyTokenRequest request) {
        return hypersenseService.verifyToken(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Sign out Hypersense token
     * POST /api/hypersense/signout
     */
    @PostMapping("/signout")
    @Operation(
        summary = "Sign out Hypersense token",
        description = "Signs out and invalidates a Hypersense authentication token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token signed out successfully"
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<Void>> signOut(
            @RequestHeader("merchant_id") String merchantId,
            @RequestHeader("Authorization") String authorization) {
        String token = authorization != null && authorization.startsWith("Bearer ") 
            ? authorization.substring(7) 
            : authorization;
        
        return hypersenseService.signOut(merchantId, token)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

