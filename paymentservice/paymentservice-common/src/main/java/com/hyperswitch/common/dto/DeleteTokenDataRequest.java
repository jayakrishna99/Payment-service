package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for deleting tokenized data
 */
@Schema(description = "Request for deleting tokenized data")
public class DeleteTokenDataRequest {
    
    @Schema(description = "Customer ID for which the tokenization is requested", 
            example = "12345_cus_01926c58bc6e77c09e809964e72af8c8", required = true)
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "Session ID associated with the tokenization request", 
            example = "12345_pms_01926c58bc6e77c09e809964e72af8c8", required = true)
    @JsonProperty("session_id")
    private String sessionId;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

