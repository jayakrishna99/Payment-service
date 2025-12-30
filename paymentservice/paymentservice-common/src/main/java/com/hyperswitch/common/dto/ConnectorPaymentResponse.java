package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Response for connector payment execution
 */
@Schema(description = "Response for connector payment execution")
public class ConnectorPaymentResponse {
    
    @JsonProperty("payment_id")
    @Schema(description = "Payment ID")
    private String paymentId;
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector")
    private String connectorName;
    
    @JsonProperty("status")
    @Schema(description = "Payment status")
    private String status;
    
    @JsonProperty("connector_transaction_id")
    @Schema(description = "Transaction ID from connector")
    private String connectorTransactionId;
    
    @JsonProperty("metadata")
    @Schema(description = "Additional metadata")
    private Map<String, Object> metadata;
    
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
    
    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }
    
    public void setConnectorTransactionId(String connectorTransactionId) {
        this.connectorTransactionId = connectorTransactionId;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

