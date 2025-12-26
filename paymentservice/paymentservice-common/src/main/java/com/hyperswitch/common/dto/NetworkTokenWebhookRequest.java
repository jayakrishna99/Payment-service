package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for network token requestor webhook
 */
public class NetworkTokenWebhookRequest {
    
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @JsonProperty("customer_id")
    private String customerId;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("payload")
    private Map<String, Object> payload;
    
    // Getters and Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }
    
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Map<String, Object> getPayload() {
        return payload;
    }
    
    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}

