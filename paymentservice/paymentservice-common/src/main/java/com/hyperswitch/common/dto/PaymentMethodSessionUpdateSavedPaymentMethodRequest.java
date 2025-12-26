package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for updating a saved payment method in a session (v2 API)
 */
@Schema(description = "Request to update a saved payment method in a session")
public class PaymentMethodSessionUpdateSavedPaymentMethodRequest {
    
    @Schema(description = "The payment method ID of the payment method to be updated", example = "12345_pm_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @Schema(description = "The payment method data to update")
    @JsonProperty("payment_method_data")
    private Map<String, Object> paymentMethodData;
    
    @Schema(description = "The network transaction ID")
    @JsonProperty("network_transaction_id")
    private String networkTransactionId;
    
    @Schema(description = "The connector mandate details")
    @JsonProperty("connector_mandate_details")
    private Map<String, Object> connectorMandateDetails;
    
    @Schema(description = "Metadata for the payment method")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
        this.paymentMethodData = paymentMethodData;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public void setNetworkTransactionId(String networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }

    public Map<String, Object> getConnectorMandateDetails() {
        return connectorMandateDetails;
    }

    public void setConnectorMandateDetails(Map<String, Object> connectorMandateDetails) {
        this.connectorMandateDetails = connectorMandateDetails;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

