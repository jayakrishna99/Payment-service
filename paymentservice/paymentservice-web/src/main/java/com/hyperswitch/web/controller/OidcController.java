package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.JwksResponse;
import com.hyperswitch.common.dto.OidcDiscoveryResponse;
import com.hyperswitch.core.oidc.OidcService;
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
 * REST controller for OIDC operations
 */
@RestController
@Tag(name = "OIDC", description = "OpenID Connect discovery and JWKS operations")
public class OidcController {
    
    private final OidcService oidcService;
    
    @Autowired
    public OidcController(OidcService oidcService) {
        this.oidcService = oidcService;
    }
    
    /**
     * OIDC Discovery
     * GET /.well-known/openid-configuration
     */
    @GetMapping("/.well-known/openid-configuration")
    @Operation(
        summary = "OIDC Discovery",
        description = "Returns OpenID Connect discovery configuration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OIDC discovery configuration retrieved successfully",
            content = @Content(schema = @Schema(implementation = OidcDiscoveryResponse.class))
        )
    })
    public Mono<ResponseEntity<OidcDiscoveryResponse>> getDiscovery() {
        return oidcService.getDiscovery()
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * JWKS Endpoint
     * GET /oauth2/jwks
     */
    @GetMapping("/oauth2/jwks")
    @Operation(
        summary = "JWKS Endpoint",
        description = "Returns JSON Web Key Set for token verification"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "JWKS retrieved successfully",
            content = @Content(schema = @Schema(implementation = JwksResponse.class))
        )
    })
    public Mono<ResponseEntity<JwksResponse>> getJwks() {
        return oidcService.getJwks()
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

