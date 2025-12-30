package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request for executing payment through connector
 */
@Schema(description = "Request for executing payment through connector")
public class ConnectorPaymentRequest {
    
    @JsonProperty("connector_name")
    @Schema(description = "Name of the connector", required = true)
    private String connectorName;
    
    @JsonProperty("amount")
    @Schema(description = "Payment amount")
    private Long amount;
    
    @JsonProperty("currency")
    @Schema(description = "Currency code")
    private String currency;
    
    @JsonProperty("payment_method")
    @Schema(description = "Payment method details")
    private Map<String, Object> paymentMethod;
    
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
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Map<String, Object> getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(Map<String, Object> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

