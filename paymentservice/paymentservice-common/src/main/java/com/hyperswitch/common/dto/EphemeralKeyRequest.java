package com.hyperswitch.common.dto;

/**
 * Request for creating an ephemeral key
 */
public class EphemeralKeyRequest {
    private String customerId;
    
    public EphemeralKeyRequest() {
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

