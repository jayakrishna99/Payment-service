package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for cloning a connector
 */
public class CloneConnectorResponse {
    
    @JsonProperty("connector_id")
    private String connectorId;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("profile_id")
    private String profileId;
    
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
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}

