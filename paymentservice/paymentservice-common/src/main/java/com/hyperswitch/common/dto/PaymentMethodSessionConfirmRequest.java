package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for confirming a payment method session (v2 API)
 */
@Schema(description = "Request to confirm a payment method session")
public class PaymentMethodSessionConfirmRequest {
    
    @Schema(description = "The payment method type", example = "card")
    @JsonProperty("payment_method_type")
    private String paymentMethodType;
    
    @Schema(description = "The payment method subtype", example = "google_pay")
    @JsonProperty("payment_method_subtype")
    private String paymentMethodSubtype;
    
    @Schema(description = "The payment instrument data to be used for the payment")
    @JsonProperty("payment_method_data")
    private Map<String, Object> paymentMethodData;
    
    @Schema(description = "The return URL to which the customer should be redirected to after adding the payment method")
    @JsonProperty("return_url")
    private String returnUrl;
    
    @Schema(description = "The storage type for the payment method")
    @JsonProperty("storage_type")
    private String storageType;

    // Getters and Setters
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

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
        this.paymentMethodData = paymentMethodData;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}

