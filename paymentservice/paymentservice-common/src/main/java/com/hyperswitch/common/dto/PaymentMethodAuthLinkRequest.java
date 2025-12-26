package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for creating a payment method auth link token
 */
@Schema(description = "Request to create a payment method auth link token")
public class PaymentMethodAuthLinkRequest {
    
    @Schema(description = "Language for the auth link", example = "en")
    @JsonProperty("language")
    private String language;
    
    @Schema(description = "Client secret")
    @JsonProperty("client_secret")
    private String clientSecret;
    
    @Schema(description = "Payment ID", example = "pay_1234567890")
    @JsonProperty("payment_id")
    private String paymentId;
    
    @Schema(description = "Payment method", example = "card")
    @JsonProperty("payment_method")
    private String paymentMethod;
    
    @Schema(description = "Payment method type", example = "credit")
    @JsonProperty("payment_method_type")
    private String paymentMethodType;

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }
}

