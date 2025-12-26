package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for authentication session token
 */
@Schema(description = "Request for authentication session token")
public class AuthenticationSessionTokenRequest {
    
    @Schema(description = "Payment method ID", example = "pm_123456")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @Schema(description = "Additional parameters")
    @JsonProperty("parameters")
    private java.util.Map<String, Object> parameters;

    // Getters and Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public java.util.Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(java.util.Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}

