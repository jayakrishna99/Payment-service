package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for creating tokenized data in vault
 */
@Schema(description = "Request for creating tokenized data in vault")
public class TokenizationRequest {
    
    @Schema(description = "Customer ID for which the tokenization is requested", 
            example = "12345_cus_01926c58bc6e77c09e809964e72af8c8", required = true)
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "Request for tokenization which contains the data to be tokenized", required = true)
    @JsonProperty("token_request")
    private Map<String, Object> tokenRequest;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Object> getTokenRequest() {
        return tokenRequest;
    }

    public void setTokenRequest(Map<String, Object> tokenRequest) {
        this.tokenRequest = tokenRequest;
    }
}

