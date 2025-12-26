package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for confirming a payment method intent (v2 API)
 */
@Schema(description = "Request to confirm a payment method intent")
public class PaymentMethodIntentConfirmRequest {
    
    @Schema(description = "The unique identifier of the customer", example = "cus_y3oqhf46pyzuxjbcn2giaqnb44", maxLength = 64, minLength = 1)
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "Payment method data to be passed")
    @JsonProperty("payment_method_data")
    private Map<String, Object> paymentMethodData;
    
    @Schema(description = "The type of payment method used for the payment", example = "card")
    @JsonProperty("payment_method_type")
    private String paymentMethodType;
    
    @Schema(description = "This is a sub-category of payment method", example = "credit")
    @JsonProperty("payment_method_subtype")
    private String paymentMethodSubtype;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
        this.paymentMethodData = paymentMethodData;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getPaymentMethodSubtype() {
        return paymentMethodSubtype;
    }

    public void setPaymentMethodSubtype(String paymentMethodSubtype) {
        this.paymentMethodSubtype = paymentMethodSubtype;
    }
}

