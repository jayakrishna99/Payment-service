package com.hyperswitch.common.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for merchant connector account
 */
public class MerchantConnectorAccountResponse {
    private String id;
    private String merchantId;
    private String merchantConnectorId;
    private String connectorName;
    private Boolean testMode;
    private Boolean disabled;
    private List<Map<String, Object>> paymentMethodsEnabled;
    private String connectorType;
    private Map<String, Object> metadata;
    private String connectorLabel;
    private String businessCountry;
    private String businessLabel;
    private String businessSubLabel;
    private Map<String, Object> frmConfigs;
    private Map<String, Object> connectorWebhookDetails;
    private String profileId;
    private List<String> applepayVerifiedDomains;
    private Map<String, Object> pmAuthConfig;
    private String status;
    private String version;
    private Instant createdAt;
    private Instant modifiedAt;
    
    public MerchantConnectorAccountResponse() {
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }
    
    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }
    
    public String getConnectorName() {
        return connectorName;
    }
    
    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
    
    public Boolean getTestMode() {
        return testMode;
    }
    
    public void setTestMode(Boolean testMode) {
        this.testMode = testMode;
    }
    
    public Boolean getDisabled() {
        return disabled;
    }
    
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    
    public List<Map<String, Object>> getPaymentMethodsEnabled() {
        return paymentMethodsEnabled;
    }
    
    public void setPaymentMethodsEnabled(List<Map<String, Object>> paymentMethodsEnabled) {
        this.paymentMethodsEnabled = paymentMethodsEnabled;
    }
    
    public String getConnectorType() {
        return connectorType;
    }
    
    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public String getConnectorLabel() {
        return connectorLabel;
    }
    
    public void setConnectorLabel(String connectorLabel) {
        this.connectorLabel = connectorLabel;
    }
    
    public String getBusinessCountry() {
        return businessCountry;
    }
    
    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }
    
    public String getBusinessLabel() {
        return businessLabel;
    }
    
    public void setBusinessLabel(String businessLabel) {
        this.businessLabel = businessLabel;
    }
    
    public String getBusinessSubLabel() {
        return businessSubLabel;
    }
    
    public void setBusinessSubLabel(String businessSubLabel) {
        this.businessSubLabel = businessSubLabel;
    }
    
    public Map<String, Object> getFrmConfigs() {
        return frmConfigs;
    }
    
    public void setFrmConfigs(Map<String, Object> frmConfigs) {
        this.frmConfigs = frmConfigs;
    }
    
    public Map<String, Object> getConnectorWebhookDetails() {
        return connectorWebhookDetails;
    }
    
    public void setConnectorWebhookDetails(Map<String, Object> connectorWebhookDetails) {
        this.connectorWebhookDetails = connectorWebhookDetails;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public List<String> getApplepayVerifiedDomains() {
        return applepayVerifiedDomains;
    }
    
    public void setApplepayVerifiedDomains(List<String> applepayVerifiedDomains) {
        this.applepayVerifiedDomains = applepayVerifiedDomains;
    }
    
    public Map<String, Object> getPmAuthConfig() {
        return pmAuthConfig;
    }
    
    public void setPmAuthConfig(Map<String, Object> pmAuthConfig) {
        this.pmAuthConfig = pmAuthConfig;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getModifiedAt() {
        return modifiedAt;
    }
    
    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

