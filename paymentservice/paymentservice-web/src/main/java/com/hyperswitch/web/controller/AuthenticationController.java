package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.authentication.AuthenticationService;
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
 * REST controller for authentication operations
 */
@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Authentication", description = "Authentication operations for payment methods")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    /**
     * Create authentication
     * POST /api/authentication
     */
    @PostMapping
    @Operation(
        summary = "Create authentication",
        description = "Creates a new authentication for a payment method"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication created successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<AuthenticationResponse>> createAuthentication(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody AuthenticationCreateRequest request) {
        return authenticationService.createAuthentication(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Check authentication eligibility
     * POST /api/authentication/{authentication_id}/eligibility
     */
    @PostMapping("/{authentication_id}/eligibility")
    @Operation(
        summary = "Check authentication eligibility",
        description = "Checks if an authentication is eligible for a specific merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Eligibility checked successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationEligibilityResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationEligibilityResponse>> checkEligibility(
            @PathVariable("authentication_id") String authenticationId,
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody AuthenticationEligibilityRequest request) {
        return authenticationService.checkEligibility(authenticationId, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Authenticate payment
     * POST /api/authentication/{authentication_id}/authenticate
     */
    @PostMapping("/{authentication_id}/authenticate")
    @Operation(
        summary = "Authenticate payment",
        description = "Authenticates a payment using the specified authentication"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication successful",
            content = @Content(schema = @Schema(implementation = AuthenticationAuthenticateResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationAuthenticateResponse>> authenticate(
            @PathVariable("authentication_id") String authenticationId,
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody AuthenticationAuthenticateRequest request) {
        return authenticationService.authenticate(authenticationId, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Eligibility check
     * POST /api/authentication/{authentication_id}/eligibility-check
     */
    @PostMapping("/{authentication_id}/eligibility-check")
    @Operation(
        summary = "Eligibility check",
        description = "Performs an eligibility check for authentication"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Eligibility check completed",
            content = @Content(schema = @Schema(implementation = AuthenticationEligibilityResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationEligibilityResponse>> eligibilityCheck(
            @PathVariable("authentication_id") String authenticationId,
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody AuthenticationEligibilityRequest request) {
        return authenticationService.eligibilityCheck(authenticationId, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Retrieve eligibility check
     * GET /api/authentication/eligibility-check/{eligibility_check_id}
     */
    @GetMapping("/eligibility-check/{eligibility_check_id}")
    @Operation(
        summary = "Retrieve eligibility check",
        description = "Retrieves an eligibility check by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Eligibility check retrieved successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationEligibilityResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Eligibility check not found")
    })
    public Mono<ResponseEntity<AuthenticationEligibilityResponse>> retrieveEligibilityCheck(
            @PathVariable("eligibility_check_id") String eligibilityCheckId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return authenticationService.retrieveEligibilityCheck(eligibilityCheckId, merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Sync authentication
     * POST /api/authentication/{merchant_id}/{authentication_id}/sync
     */
    @PostMapping("/{merchant_id}/{authentication_id}/sync")
    @Operation(
        summary = "Sync authentication",
        description = "Synchronizes authentication status with the connector"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication synced successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationResponse>> syncAuthentication(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("authentication_id") String authenticationId,
            @RequestBody(required = false) AuthenticationSyncRequest request) {
        if (request == null) {
            request = new AuthenticationSyncRequest();
        }
        return authenticationService.syncAuthentication(authenticationId, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Sync authentication post update
     * POST /api/authentication/{merchant_id}/{authentication_id}/redirect
     * GET /api/authentication/{merchant_id}/{authentication_id}/redirect
     */
    @PostMapping("/{merchant_id}/{authentication_id}/redirect")
    @GetMapping("/{merchant_id}/{authentication_id}/redirect")
    @Operation(
        summary = "Sync authentication post update",
        description = "Synchronizes authentication after redirect update"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication synced successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationResponse>> syncAuthenticationPostUpdate(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("authentication_id") String authenticationId,
            @RequestParam(required = false) java.util.Map<String, String> queryParams) {
        AuthenticationSyncRequest request = new AuthenticationSyncRequest();
        return authenticationService.syncAuthenticationPostUpdate(authenticationId, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get authentication session token
     * POST /api/authentication/{authentication_id}/enabled_authn_methods_token
     */
    @PostMapping("/{authentication_id}/enabled_authn_methods_token")
    @Operation(
        summary = "Get authentication session token",
        description = "Gets a session token for enabled authentication methods"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Session token retrieved successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationSessionTokenResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationSessionTokenResponse>> getSessionToken(
            @PathVariable("authentication_id") String authenticationId,
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody(required = false) AuthenticationSessionTokenRequest request) {
        if (request == null) {
            request = new AuthenticationSessionTokenRequest();
        }
        return authenticationService.getSessionToken(authenticationId, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Retrieve authentication
     * GET /api/authentication/{authentication_id}
     */
    @GetMapping("/{authentication_id}")
    @Operation(
        summary = "Retrieve authentication",
        description = "Retrieves an authentication by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication retrieved successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationResponse>> getAuthentication(
            @PathVariable("authentication_id") String authenticationId,
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return authenticationService.getAuthentication(authenticationId, merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Authentication redirect
     * POST /api/authentication/{authentication_id}/redirect
     */
    @PostMapping("/{authentication_id}/redirect")
    @Operation(
        summary = "Authentication redirect",
        description = "Handles authentication redirect"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Redirect handled successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    public Mono<ResponseEntity<AuthenticationResponse>> redirect(
            @PathVariable("authentication_id") String authenticationId,
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) java.util.Map<String, String> queryParams) {
        if (queryParams == null) {
            queryParams = new java.util.HashMap<>();
        }
        return authenticationService.redirect(authenticationId, merchantId, queryParams)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

