package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for connector onboarding operations
 */
public class ConnectorOnboardingRequest {
    
    @JsonProperty("connector_id")
    private String connectorId;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("tracking_id")
    private String trackingId;
    
    // Getters and Setters
    public String getConnectorId() {
        return connectorId;
    }
    
    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }
}

