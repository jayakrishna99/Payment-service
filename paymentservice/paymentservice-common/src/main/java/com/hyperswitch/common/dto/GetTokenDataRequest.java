package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for getting token data (v2 API)
 */
@Schema(description = "Request to get token data for a payment method")
public class GetTokenDataRequest {
    
    @Schema(description = "Indicates the type of token to be fetched", example = "network_token", 
        allowableValues = {"single_use_token", "multi_use_token", "network_token"})
    @JsonProperty("token_type")
    private String tokenType;

    // Getters and Setters
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

