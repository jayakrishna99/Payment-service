package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request for creating a connector session
 */
@Schema(description = "Request for creating a connector session")
public class ConnectorSessionRequest {
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector (e.g., stripe, paypal)", required = true)
    private String connectorName;
    
    @JsonProperty("payment_id")
    @Schema(description = "Payment ID")
    private String paymentId;
    
    @JsonProperty("metadata")
    @Schema(description = "Additional metadata")
    private Map<String, Object> metadata;
    
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
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

