package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Response for payment method token data
 */
public class PaymentMethodTokenResponse {
    private String paymentMethodId;
    private String token;
    private String networkToken;
    private Map<String, Object> tokenData;
    private Boolean isActive;
    
    public PaymentMethodTokenResponse() {
    }
    
    public String getPaymentMethodId() {
        return paymentMethodId;
    }
    
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
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
    
    public Map<String, Object> getTokenData() {
        return tokenData;
    }
    
    public void setTokenData(Map<String, Object> tokenData) {
        this.tokenData = tokenData;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}

