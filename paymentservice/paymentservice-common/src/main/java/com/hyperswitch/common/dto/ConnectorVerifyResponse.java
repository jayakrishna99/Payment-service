package com.hyperswitch.common.dto;

/**
 * Response DTO for connector verification
 */
public class ConnectorVerifyResponse {
    private Boolean verified;
    private String message;
    private String connectorName;
    
    public ConnectorVerifyResponse() {
    }
    
    public ConnectorVerifyResponse(Boolean verified, String message, String connectorName) {
        this.verified = verified;
        this.message = message;
        this.connectorName = connectorName;
    }
    
    public Boolean getVerified() {
        return verified;
    }
    
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
}

