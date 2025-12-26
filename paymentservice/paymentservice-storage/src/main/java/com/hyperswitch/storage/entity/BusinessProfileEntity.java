package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Business profile entity
 */
@Table("business_profile")
public class BusinessProfileEntity {
    @Id
    @Column("profile_id")
    private String profileId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("profile_name")
    private String profileName;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;
    
    @Column("return_url")
    private String returnUrl;
    
    @Column("enable_payment_response_hash")
    private Boolean enablePaymentResponseHash;
    
    @Column("payment_response_hash_key")
    private String paymentResponseHashKey;
    
    @Column("redirect_to_merchant_with_http_post")
    private Boolean redirectToMerchantWithHttpPost;
    
    @Column("webhook_details")
    private Map<String, Object> webhookDetails;
    
    @Column("metadata")
    private Map<String, Object> metadata;
    
    @Column("routing_algorithm")
    private Map<String, Object> routingAlgorithm;
    
    @Column("intent_fulfillment_time")
    private Long intentFulfillmentTime;
    
    @Column("frm_routing_algorithm")
    private Map<String, Object> frmRoutingAlgorithm;
    
    @Column("payout_routing_algorithm")
    private Map<String, Object> payoutRoutingAlgorithm;
    
    @Column("is_recon_enabled")
    private Boolean isReconEnabled;
    
    @Column("applepay_verified_domains")
    private List<String> applepayVerifiedDomains;
    
    @Column("payment_link_config")
    private Map<String, Object> paymentLinkConfig;
    
    @Column("session_expiry")
    private Long sessionExpiry;
    
    @Column("authentication_connector_details")
    private Map<String, Object> authenticationConnectorDetails;
    
    @Column("payout_link_config")
    private Map<String, Object> payoutLinkConfig;
    
    @Column("is_extended_card_info_enabled")
    private Boolean isExtendedCardInfoEnabled;
    
    @Column("extended_card_info_config")
    private Map<String, Object> extendedCardInfoConfig;
    
    @Column("is_connector_agnostic_mit_enabled")
    private Boolean isConnectorAgnosticMitEnabled;
    
    @Column("use_billing_as_payment_method_billing")
    private Boolean useBillingAsPaymentMethodBilling;
    
    @Column("collect_shipping_details_from_wallet_connector")
    private Boolean collectShippingDetailsFromWalletConnector;
    
    @Column("collect_billing_details_from_wallet_connector")
    private Boolean collectBillingDetailsFromWalletConnector;
    
    @Column("outgoing_webhook_custom_http_headers")
    private byte[] outgoingWebhookCustomHttpHeaders;
    
    @Column("always_collect_billing_details_from_wallet_connector")
    private Boolean alwaysCollectBillingDetailsFromWalletConnector;
    
    @Column("always_collect_shipping_details_from_wallet_connector")
    private Boolean alwaysCollectShippingDetailsFromWalletConnector;
    
    @Column("tax_connector_id")
    private String taxConnectorId;
    
    @Column("is_tax_connector_enabled")
    private Boolean isTaxConnectorEnabled;
    
    @Column("version")
    private String version;
    
    @Column("dynamic_routing_algorithm")
    private Map<String, Object> dynamicRoutingAlgorithm;
    
    @Column("routing_algorithm_id")
    private String routingAlgorithmId;
    
    @Column("order_fulfillment_time")
    private Long orderFulfillmentTime;
    
    @Column("order_fulfillment_time_origin")
    private String orderFulfillmentTimeOrigin;
    
    @Column("frm_routing_algorithm_id")
    private String frmRoutingAlgorithmId;
    
    @Column("payout_routing_algorithm_id")
    private String payoutRoutingAlgorithmId;
    
    @Column("default_fallback_routing")
    private Map<String, Object> defaultFallbackRouting;
    
    @Column("should_collect_cvv_during_payment")
    private Boolean shouldCollectCvvDuringPayment;

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

    public Map<String, Object> getRoutingAlgorithm() {
        return routingAlgorithm;
    }

    public void setRoutingAlgorithm(Map<String, Object> routingAlgorithm) {
        this.routingAlgorithm = routingAlgorithm;
    }

    public Long getIntentFulfillmentTime() {
        return intentFulfillmentTime;
    }

    public void setIntentFulfillmentTime(Long intentFulfillmentTime) {
        this.intentFulfillmentTime = intentFulfillmentTime;
    }

    public Map<String, Object> getFrmRoutingAlgorithm() {
        return frmRoutingAlgorithm;
    }

    public void setFrmRoutingAlgorithm(Map<String, Object> frmRoutingAlgorithm) {
        this.frmRoutingAlgorithm = frmRoutingAlgorithm;
    }

    public Map<String, Object> getPayoutRoutingAlgorithm() {
        return payoutRoutingAlgorithm;
    }

    public void setPayoutRoutingAlgorithm(Map<String, Object> payoutRoutingAlgorithm) {
        this.payoutRoutingAlgorithm = payoutRoutingAlgorithm;
    }

    public Boolean getIsReconEnabled() {
        return isReconEnabled;
    }

    public void setIsReconEnabled(Boolean isReconEnabled) {
        this.isReconEnabled = isReconEnabled;
    }

    public List<String> getApplepayVerifiedDomains() {
        return applepayVerifiedDomains;
    }

    public void setApplepayVerifiedDomains(List<String> applepayVerifiedDomains) {
        this.applepayVerifiedDomains = applepayVerifiedDomains;
    }

    public Map<String, Object> getPaymentLinkConfig() {
        return paymentLinkConfig;
    }

    public void setPaymentLinkConfig(Map<String, Object> paymentLinkConfig) {
        this.paymentLinkConfig = paymentLinkConfig;
    }

    public Long getSessionExpiry() {
        return sessionExpiry;
    }

    public void setSessionExpiry(Long sessionExpiry) {
        this.sessionExpiry = sessionExpiry;
    }

    public Map<String, Object> getAuthenticationConnectorDetails() {
        return authenticationConnectorDetails;
    }

    public void setAuthenticationConnectorDetails(Map<String, Object> authenticationConnectorDetails) {
        this.authenticationConnectorDetails = authenticationConnectorDetails;
    }

    public Map<String, Object> getPayoutLinkConfig() {
        return payoutLinkConfig;
    }

    public void setPayoutLinkConfig(Map<String, Object> payoutLinkConfig) {
        this.payoutLinkConfig = payoutLinkConfig;
    }

    public Boolean getIsExtendedCardInfoEnabled() {
        return isExtendedCardInfoEnabled;
    }

    public void setIsExtendedCardInfoEnabled(Boolean isExtendedCardInfoEnabled) {
        this.isExtendedCardInfoEnabled = isExtendedCardInfoEnabled;
    }

    public Map<String, Object> getExtendedCardInfoConfig() {
        return extendedCardInfoConfig;
    }

    public void setExtendedCardInfoConfig(Map<String, Object> extendedCardInfoConfig) {
        this.extendedCardInfoConfig = extendedCardInfoConfig;
    }

    public Boolean getIsConnectorAgnosticMitEnabled() {
        return isConnectorAgnosticMitEnabled;
    }

    public void setIsConnectorAgnosticMitEnabled(Boolean isConnectorAgnosticMitEnabled) {
        this.isConnectorAgnosticMitEnabled = isConnectorAgnosticMitEnabled;
    }

    public Boolean getUseBillingAsPaymentMethodBilling() {
        return useBillingAsPaymentMethodBilling;
    }

    public void setUseBillingAsPaymentMethodBilling(Boolean useBillingAsPaymentMethodBilling) {
        this.useBillingAsPaymentMethodBilling = useBillingAsPaymentMethodBilling;
    }

    public Boolean getCollectShippingDetailsFromWalletConnector() {
        return collectShippingDetailsFromWalletConnector;
    }

    public void setCollectShippingDetailsFromWalletConnector(Boolean collectShippingDetailsFromWalletConnector) {
        this.collectShippingDetailsFromWalletConnector = collectShippingDetailsFromWalletConnector;
    }

    public Boolean getCollectBillingDetailsFromWalletConnector() {
        return collectBillingDetailsFromWalletConnector;
    }

    public void setCollectBillingDetailsFromWalletConnector(Boolean collectBillingDetailsFromWalletConnector) {
        this.collectBillingDetailsFromWalletConnector = collectBillingDetailsFromWalletConnector;
    }

    public byte[] getOutgoingWebhookCustomHttpHeaders() {
        return outgoingWebhookCustomHttpHeaders;
    }

    public void setOutgoingWebhookCustomHttpHeaders(byte[] outgoingWebhookCustomHttpHeaders) {
        this.outgoingWebhookCustomHttpHeaders = outgoingWebhookCustomHttpHeaders;
    }

    public Boolean getAlwaysCollectBillingDetailsFromWalletConnector() {
        return alwaysCollectBillingDetailsFromWalletConnector;
    }

    public void setAlwaysCollectBillingDetailsFromWalletConnector(Boolean alwaysCollectBillingDetailsFromWalletConnector) {
        this.alwaysCollectBillingDetailsFromWalletConnector = alwaysCollectBillingDetailsFromWalletConnector;
    }

    public Boolean getAlwaysCollectShippingDetailsFromWalletConnector() {
        return alwaysCollectShippingDetailsFromWalletConnector;
    }

    public void setAlwaysCollectShippingDetailsFromWalletConnector(Boolean alwaysCollectShippingDetailsFromWalletConnector) {
        this.alwaysCollectShippingDetailsFromWalletConnector = alwaysCollectShippingDetailsFromWalletConnector;
    }

    public String getTaxConnectorId() {
        return taxConnectorId;
    }

    public void setTaxConnectorId(String taxConnectorId) {
        this.taxConnectorId = taxConnectorId;
    }

    public Boolean getIsTaxConnectorEnabled() {
        return isTaxConnectorEnabled;
    }

    public void setIsTaxConnectorEnabled(Boolean isTaxConnectorEnabled) {
        this.isTaxConnectorEnabled = isTaxConnectorEnabled;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, Object> getDynamicRoutingAlgorithm() {
        return dynamicRoutingAlgorithm;
    }

    public void setDynamicRoutingAlgorithm(Map<String, Object> dynamicRoutingAlgorithm) {
        this.dynamicRoutingAlgorithm = dynamicRoutingAlgorithm;
    }

    public String getRoutingAlgorithmId() {
        return routingAlgorithmId;
    }

    public void setRoutingAlgorithmId(String routingAlgorithmId) {
        this.routingAlgorithmId = routingAlgorithmId;
    }

    public Long getOrderFulfillmentTime() {
        return orderFulfillmentTime;
    }

    public void setOrderFulfillmentTime(Long orderFulfillmentTime) {
        this.orderFulfillmentTime = orderFulfillmentTime;
    }

    public String getOrderFulfillmentTimeOrigin() {
        return orderFulfillmentTimeOrigin;
    }

    public void setOrderFulfillmentTimeOrigin(String orderFulfillmentTimeOrigin) {
        this.orderFulfillmentTimeOrigin = orderFulfillmentTimeOrigin;
    }

    public String getFrmRoutingAlgorithmId() {
        return frmRoutingAlgorithmId;
    }

    public void setFrmRoutingAlgorithmId(String frmRoutingAlgorithmId) {
        this.frmRoutingAlgorithmId = frmRoutingAlgorithmId;
    }

    public String getPayoutRoutingAlgorithmId() {
        return payoutRoutingAlgorithmId;
    }

    public void setPayoutRoutingAlgorithmId(String payoutRoutingAlgorithmId) {
        this.payoutRoutingAlgorithmId = payoutRoutingAlgorithmId;
    }

    public Map<String, Object> getDefaultFallbackRouting() {
        return defaultFallbackRouting;
    }

    public void setDefaultFallbackRouting(Map<String, Object> defaultFallbackRouting) {
        this.defaultFallbackRouting = defaultFallbackRouting;
    }

    public Boolean getShouldCollectCvvDuringPayment() {
        return shouldCollectCvvDuringPayment;
    }

    public void setShouldCollectCvvDuringPayment(Boolean shouldCollectCvvDuringPayment) {
        this.shouldCollectCvvDuringPayment = shouldCollectCvvDuringPayment;
    }
}

