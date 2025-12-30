package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response for connector refund processing
 */
@Schema(description = "Response for connector refund processing")
public class ConnectorRefundResponse {
    
    @JsonProperty("refund_id")
    @Schema(description = "Refund ID")
    private String refundId;
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector")
    private String connectorName;
    
    @JsonProperty("status")
    @Schema(description = "Refund status")
    private String status;
    
    @JsonProperty("connector_refund_id")
    @Schema(description = "Refund ID from connector")
    private String connectorRefundId;
    
    public String getRefundId() {
        return refundId;
    }
    
    public void setRefundId(String refundId) {
        this.refundId = refundId;
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
    
    public String getConnectorRefundId() {
        return connectorRefundId;
    }
    
    public void setConnectorRefundId(String connectorRefundId) {
        this.connectorRefundId = connectorRefundId;
    }
}

