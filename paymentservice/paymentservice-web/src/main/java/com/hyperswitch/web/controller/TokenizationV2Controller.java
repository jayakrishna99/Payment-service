package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.DeleteTokenDataRequest;
import com.hyperswitch.common.dto.DeleteTokenDataResponse;
import com.hyperswitch.common.dto.TokenizationRequest;
import com.hyperswitch.common.dto.TokenizationResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.tokenization.TokenizationService;
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
 * Controller for Tokenization v2 API endpoints
 */
@RestController
@RequestMapping("/api/v2/tokenize")
@Tag(name = "Tokenization", description = "Tokenization v2 API endpoints")
public class TokenizationV2Controller {
    
    private final TokenizationService tokenizationService;
    
    @Autowired
    public TokenizationV2Controller(TokenizationService tokenizationService) {
        this.tokenizationService = tokenizationService;
    }
    
    @PostMapping
    @Operation(
        summary = "Create token vault",
        description = "Create tokenized data in vault"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token created successfully",
            content = @Content(schema = @Schema(implementation = TokenizationResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<TokenizationResponse>> createTokenVault(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody TokenizationRequest request) {
        return tokenizationService.createTokenVault(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete tokenized data",
        description = "Delete tokenized data from vault"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token deleted successfully",
            content = @Content(schema = @Schema(implementation = DeleteTokenDataResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "404", description = "Token not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<DeleteTokenDataResponse>> deleteTokenizedData(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable("id") String tokenId,
            @RequestBody DeleteTokenDataRequest request) {
        return tokenizationService.deleteTokenizedData(merchantId, tokenId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

