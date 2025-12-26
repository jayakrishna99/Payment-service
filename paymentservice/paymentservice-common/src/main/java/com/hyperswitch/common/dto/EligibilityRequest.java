package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for payment eligibility
 */
@Schema(description = "Request for payment eligibility")
public class EligibilityRequest {
    
    @Schema(description = "Payment method data")
    @JsonProperty("payment_method_data")
    private Map<String, Object> paymentMethodData;
    
    @Schema(description = "Balance check required")
    @JsonProperty("check_balance")
    private Boolean checkBalance;
    
    @Schema(description = "Additional eligibility parameters")
    @JsonProperty("parameters")
    private Map<String, Object> parameters;

    // Getters and Setters
    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
        this.paymentMethodData = paymentMethodData;
    }

    public Boolean getCheckBalance() {
        return checkBalance;
    }

    public void setCheckBalance(Boolean checkBalance) {
        this.checkBalance = checkBalance;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}

