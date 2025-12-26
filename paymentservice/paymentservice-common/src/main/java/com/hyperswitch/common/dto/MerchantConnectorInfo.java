package com.hyperswitch.common.dto;

/**
 * Merchant connector information for filter responses
 */
public class MerchantConnectorInfo {
    private String merchantConnectorId;
    private String connectorName;
    private String label;
    
    public MerchantConnectorInfo() {
    }
    
    public MerchantConnectorInfo(String merchantConnectorId, String connectorName, String label) {
        this.merchantConnectorId = merchantConnectorId;
        this.connectorName = connectorName;
        this.label = label;
    }
    
    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }
    
    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
}

