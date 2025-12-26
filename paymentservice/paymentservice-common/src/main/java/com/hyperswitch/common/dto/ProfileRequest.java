package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for profile operations
 */
@Schema(description = "Request for profile operations")
public class ProfileRequest {
    
    @Schema(description = "Profile name", example = "Production Profile", required = true)
    @JsonProperty("profile_name")
    private String profileName;
    
    @Schema(description = "Return URL", example = "https://example.com/return")
    @JsonProperty("return_url")
    private String returnUrl;
    
    @Schema(description = "Enable payment response hash", example = "true")
    @JsonProperty("enable_payment_response_hash")
    private Boolean enablePaymentResponseHash;
    
    @Schema(description = "Payment response hash key")
    @JsonProperty("payment_response_hash_key")
    private String paymentResponseHashKey;
    
    @Schema(description = "Redirect to merchant with HTTP POST", example = "false")
    @JsonProperty("redirect_to_merchant_with_http_post")
    private Boolean redirectToMerchantWithHttpPost;
    
    @Schema(description = "Webhook details")
    @JsonProperty("webhook_details")
    private Map<String, Object> webhookDetails;
    
    @Schema(description = "Metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    @Schema(description = "Is reconciliation enabled", example = "true")
    @JsonProperty("is_recon_enabled")
    private Boolean isReconEnabled;
    
    @Schema(description = "Is extended card info enabled", example = "false")
    @JsonProperty("is_extended_card_info_enabled")
    private Boolean isExtendedCardInfoEnabled;
    
    @Schema(description = "Is connector agnostic MIT enabled", example = "false")
    @JsonProperty("is_connector_agnostic_mit_enabled")
    private Boolean isConnectorAgnosticMitEnabled;

    // Getters and Setters
    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
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

    public String getPaymentResponseHashKey() {
        return paymentResponseHashKey;
    }

    public void setPaymentResponseHashKey(String paymentResponseHashKey) {
        this.paymentResponseHashKey = paymentResponseHashKey;
    }

    public Boolean getRedirectToMerchantWithHttpPost() {
        return redirectToMerchantWithHttpPost;
    }

    public void setRedirectToMerchantWithHttpPost(Boolean redirectToMerchantWithHttpPost) {
        this.redirectToMerchantWithHttpPost = redirectToMerchantWithHttpPost;
    }

    public Map<String, Object> getWebhookDetails() {
        return webhookDetails;
    }

    public void setWebhookDetails(Map<String, Object> webhookDetails) {
        this.webhookDetails = webhookDetails;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
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
}

