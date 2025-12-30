package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response for connector payment status
 */
@Schema(description = "Response for connector payment status")
public class ConnectorPaymentStatusResponse {
    
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
}

