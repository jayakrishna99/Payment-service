package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response for connector payment capture
 */
@Schema(description = "Response for connector payment capture")
public class ConnectorCaptureResponse {
    
    @JsonProperty("payment_id")
    @Schema(description = "Payment ID")
    private String paymentId;
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector")
    private String connectorName;
    
    @JsonProperty("status")
    @Schema(description = "Capture status")
    private String status;
    
    @JsonProperty("capture_id")
    @Schema(description = "Capture ID from connector")
    private String captureId;
    
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
    
    public String getCaptureId() {
        return captureId;
    }
    
    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }
}

