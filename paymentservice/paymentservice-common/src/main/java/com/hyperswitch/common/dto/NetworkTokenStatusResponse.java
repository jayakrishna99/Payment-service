package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Response for network token status check
 */
public class NetworkTokenStatusResponse {
    private String paymentMethodId;
    private String networkTransactionId;
    private String status; // ACTIVE, INACTIVE, EXPIRED, PENDING
    private Instant lastUpdated;
    private Boolean hasNetworkToken;
    
    public NetworkTokenStatusResponse() {
    }
    
    public NetworkTokenStatusResponse(String paymentMethodId, String networkTransactionId, 
                                     String status, Instant lastUpdated, Boolean hasNetworkToken) {
        this.paymentMethodId = paymentMethodId;
        this.networkTransactionId = networkTransactionId;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.hasNetworkToken = hasNetworkToken;
    }
    
    public String getPaymentMethodId() {
        return paymentMethodId;
    }
    
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
    
    public String getNetworkTransactionId() {
        return networkTransactionId;
    }
    
    public void setNetworkTransactionId(String networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Instant getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public Boolean getHasNetworkToken() {
        return hasNetworkToken;
    }
    
    public void setHasNetworkToken(Boolean hasNetworkToken) {
        this.hasNetworkToken = hasNetworkToken;
    }
}

