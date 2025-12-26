package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Merchant Connector Account entity
 * Represents a merchant's connector account configuration
 */
@Table("merchant_connector_account")
public class MerchantConnectorAccountEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("merchant_connector_id")
    private String merchantConnectorId;
    
    @Column("connector_name")
    private String connectorName;
    
    @Column("connector_account_details")
    private byte[] connectorAccountDetails;
    
    @Column("test_mode")
    private Boolean testMode;
    
    @Column("disabled")
    private Boolean disabled;
    
    @Column("payment_methods_enabled")
    private List<Map<String, Object>> paymentMethodsEnabled;
    
    @Column("connector_type")
    private String connectorType;
    
    @Column("metadata")
    private Map<String, Object> metadata;
    
    @Column("connector_label")
    private String connectorLabel;
    
    @Column("business_country")
    private String businessCountry;
    
    @Column("business_label")
    private String businessLabel;
    
    @Column("business_sub_label")
    private String businessSubLabel;
    
    @Column("frm_configs")
    private Map<String, Object> frmConfigs;
    
    @Column("connector_webhook_details")
    private Map<String, Object> connectorWebhookDetails;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("applepay_verified_domains")
    private List<String> applepayVerifiedDomains;
    
    @Column("pm_auth_config")
    private Map<String, Object> pmAuthConfig;
    
    @Column("status")
    private String status;
    
    @Column("additional_merchant_data")
    private byte[] additionalMerchantData;
    
    @Column("connector_wallets_details")
    private byte[] connectorWalletsDetails;
    
    @Column("version")
    private String version;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;

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

    public byte[] getConnectorAccountDetails() {
        return connectorAccountDetails;
    }

    public void setConnectorAccountDetails(byte[] connectorAccountDetails) {
        this.connectorAccountDetails = connectorAccountDetails;
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

    public byte[] getAdditionalMerchantData() {
        return additionalMerchantData;
    }

    public void setAdditionalMerchantData(byte[] additionalMerchantData) {
        this.additionalMerchantData = additionalMerchantData;
    }

    public byte[] getConnectorWalletsDetails() {
        return connectorWalletsDetails;
    }

    public void setConnectorWalletsDetails(byte[] connectorWalletsDetails) {
        this.connectorWalletsDetails = connectorWalletsDetails;
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

