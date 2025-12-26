package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request DTO for connector verification
 */
public class ConnectorVerifyRequest {
    private String merchantId;
    private String connectorName;
    private Map<String, Object> connectorAccountDetails;
    private String profileId;
    
    public ConnectorVerifyRequest() {
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public Map<String, Object> getConnectorAccountDetails() {
        return connectorAccountDetails;
    }
    
    public void setConnectorAccountDetails(Map<String, Object> connectorAccountDetails) {
        this.connectorAccountDetails = connectorAccountDetails;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}

