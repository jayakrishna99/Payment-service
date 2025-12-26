package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for authentication eligibility check
 */
@Schema(description = "Request for authentication eligibility check")
public class AuthenticationEligibilityRequest {
    
    @Schema(description = "Payment method ID", example = "pm_123456")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @Schema(description = "Payment ID", example = "pay_123456")
    @JsonProperty("payment_id")
    private String paymentId;
    
    @Schema(description = "Additional parameters")
    @JsonProperty("parameters")
    private Map<String, Object> parameters;

    // Getters and Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}

