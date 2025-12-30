package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request for processing refund through connector
 */
@Schema(description = "Request for processing refund through connector")
public class ConnectorRefundRequest {
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector", required = true)
    private String connectorName;
    
    @JsonProperty("amount")
    @Schema(description = "Refund amount (null for full refund)")
    private Long amount;
    
    @JsonProperty("reason")
    @Schema(description = "Refund reason")
    private String reason;
    
    @JsonProperty("metadata")
    @Schema(description = "Additional metadata")
    private Map<String, Object> metadata;
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public Long getAmount() {
        return amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

