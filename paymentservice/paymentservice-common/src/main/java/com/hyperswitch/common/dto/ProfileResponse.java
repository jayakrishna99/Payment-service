package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for profile operations
 */
@Schema(description = "Response for profile operations")
public class ProfileResponse {
    
    @Schema(description = "Profile ID", example = "12345_prof_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("profile_id")
    private String profileId;
    
    @Schema(description = "Merchant ID", example = "12345_merchant_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @Schema(description = "Profile name", example = "Production Profile")
    @JsonProperty("profile_name")
    private String profileName;
    
    @Schema(description = "Created at", example = "2024-02-24T11:04:09.922Z")
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @Schema(description = "Modified at", example = "2024-02-24T11:04:09.922Z")
    @JsonProperty("modified_at")
    private Instant modifiedAt;
    
    @Schema(description = "Return URL", example = "https://example.com/return")
    @JsonProperty("return_url")
    private String returnUrl;
    
    @Schema(description = "Enable payment response hash", example = "true")
    @JsonProperty("enable_payment_response_hash")
    private Boolean enablePaymentResponseHash;
    
    @Schema(description = "Is reconciliation enabled", example = "true")
    @JsonProperty("is_recon_enabled")
    private Boolean isReconEnabled;
    
    @Schema(description = "Is extended card info enabled", example = "false")
    @JsonProperty("is_extended_card_info_enabled")
    private Boolean isExtendedCardInfoEnabled;
    
    @Schema(description = "Is connector agnostic MIT enabled", example = "false")
    @JsonProperty("is_connector_agnostic_mit_enabled")
    private Boolean isConnectorAgnosticMitEnabled;
    
    @Schema(description = "Metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    @Schema(description = "Apple Pay verified domains")
    @JsonProperty("applepay_verified_domains")
    private List<String> applepayVerifiedDomains;

    // Getters and Setters
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
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

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Boolean getEnablePaymentResponseHash() {
        return enablePaymentResponseHash;
    }

    public void setEnablePaymentResponseHash(Boolean enablePaymentResponseHash) {
        this.enablePaymentResponseHash = enablePaymentResponseHash;
    }

    public Boolean getIsReconEnabled() {
        return isReconEnabled;
    }

    public void setIsReconEnabled(Boolean isReconEnabled) {
        this.isReconEnabled = isReconEnabled;
    }

    public Boolean getIsExtendedCardInfoEnabled() {
        return isExtendedCardInfoEnabled;
    }

    public void setIsExtendedCardInfoEnabled(Boolean isExtendedCardInfoEnabled) {
        this.isExtendedCardInfoEnabled = isExtendedCardInfoEnabled;
    }

    public Boolean getIsConnectorAgnosticMitEnabled() {
        return isConnectorAgnosticMitEnabled;
    }

    public void setIsConnectorAgnosticMitEnabled(Boolean isConnectorAgnosticMitEnabled) {
        this.isConnectorAgnosticMitEnabled = isConnectorAgnosticMitEnabled;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public List<String> getApplepayVerifiedDomains() {
        return applepayVerifiedDomains;
    }

    public void setApplepayVerifiedDomains(List<String> applepayVerifiedDomains) {
        this.applepayVerifiedDomains = applepayVerifiedDomains;
    }
}

