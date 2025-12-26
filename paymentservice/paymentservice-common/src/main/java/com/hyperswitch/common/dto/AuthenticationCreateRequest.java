package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for creating authentication
 */
@Schema(description = "Request for creating authentication")
public class AuthenticationCreateRequest {
    
    @Schema(description = "Payment method ID", required = true, example = "pm_123456")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @Schema(description = "Payment ID", example = "pay_123456")
    @JsonProperty("payment_id")
    private String paymentId;
    
    @Schema(description = "Authentication connector", example = "stripe")
    @JsonProperty("authentication_connector")
    private String authenticationConnector;
    
    @Schema(description = "Authentication type", example = "OTP")
    @JsonProperty("authentication_type")
    private String authenticationType;
    
    @Schema(description = "Authentication data")
    @JsonProperty("authentication_data")
    private Map<String, Object> authenticationData;
    
    @Schema(description = "Additional metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

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

    public String getAuthenticationConnector() {
        return authenticationConnector;
    }

    public void setAuthenticationConnector(String authenticationConnector) {
        this.authenticationConnector = authenticationConnector;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Map<String, Object> getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(Map<String, Object> authenticationData) {
        this.authenticationData = authenticationData;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

