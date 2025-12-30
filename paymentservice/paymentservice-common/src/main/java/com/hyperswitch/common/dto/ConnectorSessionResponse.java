package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response for connector session creation
 */
@Schema(description = "Response for connector session creation")
public class ConnectorSessionResponse {
    
    @JsonProperty("session_id")
    @Schema(description = "Session ID")
    private String sessionId;
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector")
    private String connectorName;
    
    @JsonProperty("payment_id")
    @Schema(description = "Payment ID")
    private String paymentId;
    
    @JsonProperty("session_token")
    @Schema(description = "Session token for the connector")
    private String sessionToken;
    
    @JsonProperty("expires_at")
    @Schema(description = "Session expiration timestamp")
    private Long expiresAt;
    
    @JsonProperty("metadata")
    @Schema(description = "Additional metadata")
    private Map<String, Object> metadata;
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getSessionToken() {
        return sessionToken;
    }
    
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    
    public Long getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

