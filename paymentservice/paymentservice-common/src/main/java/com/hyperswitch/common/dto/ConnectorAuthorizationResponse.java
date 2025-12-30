package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response for connector payment authorization
 */
@Schema(description = "Response for connector payment authorization")
public class ConnectorAuthorizationResponse {
    
    @JsonProperty("payment_id")
    @Schema(description = "Payment ID")
    private String paymentId;
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector")
    private String connectorName;
    
    @JsonProperty("status")
    @Schema(description = "Authorization status")
    private String status;
    
    @JsonProperty("authorization_id")
    @Schema(description = "Authorization ID from connector")
    private String authorizationId;
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAuthorizationId() {
        return authorizationId;
    }
    
    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }
}

