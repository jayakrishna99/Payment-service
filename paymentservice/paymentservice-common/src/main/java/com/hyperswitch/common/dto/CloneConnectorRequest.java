package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for cloning a connector
 */
public class CloneConnectorRequest {
    
    @JsonProperty("source_connector_id")
    private String sourceConnectorId;
    
    @JsonProperty("target_merchant_id")
    private String targetMerchantId;
    
    @JsonProperty("target_profile_id")
    private String targetProfileId;
    
    // Getters and Setters
    public String getSourceConnectorId() {
        return sourceConnectorId;
    }
    
    public void setSourceConnectorId(String sourceConnectorId) {
        this.sourceConnectorId = sourceConnectorId;
    }
    
    public String getTargetMerchantId() {
        return targetMerchantId;
    }
    
    public void setTargetMerchantId(String targetMerchantId) {
        this.targetMerchantId = targetMerchantId;
    }
    
    public String getTargetProfileId() {
        return targetProfileId;
    }
    
    public void setTargetProfileId(String targetProfileId) {
        this.targetProfileId = targetProfileId;
    }
}

