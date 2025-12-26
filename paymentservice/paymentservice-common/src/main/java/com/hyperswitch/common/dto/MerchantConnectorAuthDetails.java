package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Merchant connector authentication details
 */
public class MerchantConnectorAuthDetails {
    private String connectorName;
    private Map<String, String> authData;
    
    public MerchantConnectorAuthDetails() {
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public Map<String, String> getAuthData() {
        return authData;
    }
    
    public void setAuthData(Map<String, String> authData) {
        this.authData = authData;
    }
}

