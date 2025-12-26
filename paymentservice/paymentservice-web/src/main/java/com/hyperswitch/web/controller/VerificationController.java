package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ApplePayMerchantResponse;
import com.hyperswitch.common.dto.ApplePayMerchantVerificationRequest;
import com.hyperswitch.common.dto.ApplePayVerifiedDomainsResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.verification.VerificationService;
import com.hyperswitch.web.controller.PaymentException;
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
 * Controller for Verification endpoints
 */
@RestController
@RequestMapping("/api/verification/apple_pay")
@Tag(name = "Verification", description = "Verification API endpoints")
public class VerificationController {
    
    private final VerificationService verificationService;
    
    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }
    
    @PostMapping("/merchant_registration")
    @Operation(
        summary = "Register Apple Pay merchant",
        description = "Register Apple Pay merchant with domain names"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant registered successfully",
            content = @Content(schema = @Schema(implementation = ApplePayMerchantResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ApplePayMerchantResponse>> registerApplePayMerchant(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestHeader(value = "X-Profile-Id", required = false) String profileId,
            @RequestBody ApplePayMerchantVerificationRequest request) {
        return verificationService.registerApplePayMerchant(merchantId, profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    @GetMapping("/merchant_registration")
    @Operation(
        summary = "Get Apple Pay merchant registration",
        description = "Get Apple Pay merchant registration status"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Registration retrieved successfully",
            content = @Content(schema = @Schema(implementation = ApplePayMerchantResponse.class))
        ),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ApplePayMerchantResponse>> getApplePayMerchantRegistration(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return verificationService.getApplePayMerchantRegistration(merchantId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    @GetMapping("/verified_domains")
    @Operation(
        summary = "Retrieve Apple Pay verified domains",
        description = "Get verified domains for Apple Pay"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Verified domains retrieved successfully",
            content = @Content(schema = @Schema(implementation = ApplePayVerifiedDomainsResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ApplePayVerifiedDomainsResponse>> getApplePayVerifiedDomains(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam("merchant_connector_account_id") String merchantConnectorAccountId) {
        return verificationService.getApplePayVerifiedDomains(merchantId, merchantConnectorAccountId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

