package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Response for card tokenization
 */
public class TokenizeCardResponse {
    private String paymentMethodId;
    private String customerId;
    private String merchantId;
    private String token;
    private String networkToken;
    private Boolean networkTokenizationEnabled;
    private Instant createdAt;
    
    public TokenizeCardResponse() {
    }
    
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
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getNetworkToken() {
        return networkToken;
    }
    
    public void setNetworkToken(String networkToken) {
        this.networkToken = networkToken;
    }
    
    public Boolean getNetworkTokenizationEnabled() {
        return networkTokenizationEnabled;
    }
    
    public void setNetworkTokenizationEnabled(Boolean networkTokenizationEnabled) {
        this.networkTokenizationEnabled = networkTokenizationEnabled;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

