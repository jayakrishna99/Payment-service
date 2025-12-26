package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for payment method auth token exchange
 */
@Schema(description = "Response for payment method auth token exchange")
public class PaymentMethodAuthExchangeResponse {
    
    @Schema(description = "Access token", example = "access_token_1234567890")
    @JsonProperty("access_token")
    private String accessToken;

    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

