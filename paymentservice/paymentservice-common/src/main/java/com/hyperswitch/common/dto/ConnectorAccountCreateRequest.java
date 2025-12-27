package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Request DTO for creating connector account (v2 API)
 */
public class ConnectorAccountCreateRequest {
    
    @JsonProperty("connector_type")
    private String connectorType;
    
    @JsonProperty("connector_name")
    private String connectorName;
    
    @JsonProperty("connector_label")
    private String connectorLabel;
    
    @JsonProperty("profile_id")
    private String profileId;
    
    @JsonProperty("connector_account_details")
    private Map<String, Object> connectorAccountDetails;
    
    @JsonProperty("payment_methods_enabled")
    private List<Map<String, Object>> paymentMethodsEnabled;
    
    @JsonProperty("connector_webhook_details")
    private Map<String, Object> connectorWebhookDetails;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    @JsonProperty("disabled")
    private Boolean disabled;
    
    @JsonProperty("status")
    private String status;
    
    // Getters and Setters
    public String getConnectorType() {
        return connectorType;
    }
    
    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public String getConnectorLabel() {
        return connectorLabel;
    }
    
    public void setConnectorLabel(String connectorLabel) {
        this.connectorLabel = connectorLabel;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public Map<String, Object> getConnectorAccountDetails() {
        return connectorAccountDetails;
    }
    
    public void setConnectorAccountDetails(Map<String, Object> connectorAccountDetails) {
        this.connectorAccountDetails = connectorAccountDetails;
    }
    
    public List<Map<String, Object>> getPaymentMethodsEnabled() {
        return paymentMethodsEnabled;
    }
    
    public void setPaymentMethodsEnabled(List<Map<String, Object>> paymentMethodsEnabled) {
        this.paymentMethodsEnabled = paymentMethodsEnabled;
    }
    
    public Map<String, Object> getConnectorWebhookDetails() {
        return connectorWebhookDetails;
    }
    
    public void setConnectorWebhookDetails(Map<String, Object> connectorWebhookDetails) {
        this.connectorWebhookDetails = connectorWebhookDetails;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public Boolean getDisabled() {
        return disabled;
    }
    
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}

