package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for deleting a saved payment method from a session (v2 API)
 */
@Schema(description = "Request to delete a saved payment method from a session")
public class PaymentMethodSessionDeleteSavedPaymentMethodRequest {
    
    @Schema(description = "The payment method ID of the payment method to be deleted", example = "12345_pm_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;

    // Getters and Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}

