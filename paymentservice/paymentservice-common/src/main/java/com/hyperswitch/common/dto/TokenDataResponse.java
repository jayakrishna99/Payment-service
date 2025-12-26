package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response DTO for token data (v2 API)
 */
@Schema(description = "Response for token data")
public class TokenDataResponse {
    
    @Schema(description = "The unique identifier of the payment method", example = "12345_pm_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @Schema(description = "Token type of the payment method", example = "network_token")
    @JsonProperty("token_type")
    private String tokenType;
    
    @Schema(description = "Token details of the payment method")
    @JsonProperty("token_details")
    private Map<String, Object> tokenDetails;

    // Getters and Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Map<String, Object> getTokenDetails() {
        return tokenDetails;
    }

    public void setTokenDetails(Map<String, Object> tokenDetails) {
        this.tokenDetails = tokenDetails;
    }
}

