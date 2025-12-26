package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for payment method session (v2 API)
 */
@Schema(description = "Payment method session response")
public class PaymentMethodSessionResponse {
    
    @Schema(description = "The unique identifier for the Payment Method Session", example = "12345_pms_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("id")
    private String id;
    
    @Schema(description = "The customer ID for which the payment methods session is to be created", example = "12345_cus_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "The billing address details of the customer")
    @JsonProperty("billing")
    private Address billing;
    
    @Schema(description = "The tokenization type to be applied")
    @JsonProperty("psp_tokenization")
    private String pspTokenization;
    
    @Schema(description = "The network tokenization configuration if applicable")
    @JsonProperty("network_tokenization")
    private Map<String, Object> networkTokenization;
    
    @Schema(description = "Contains data to be passed on to tokenization service (if present) to create token_id for given JSON data")
    @JsonProperty("tokenization_data")
    private Map<String, Object> tokenizationData;
    
    @Schema(description = "The ISO timestamp when the session will expire", example = "2023-01-18T11:04:09.922Z")
    @JsonProperty("expires_at")
    private Instant expiresAt;
    
    @Schema(description = "Client Secret")
    @JsonProperty("client_secret")
    private String clientSecret;
    
    @Schema(description = "The return URL to which the user should be redirected to")
    @JsonProperty("return_url")
    private String returnUrl;
    
    @Schema(description = "The next action details for the payment method session")
    @JsonProperty("next_action")
    private Map<String, Object> nextAction;
    
    @Schema(description = "The customer authentication details for the payment method")
    @JsonProperty("authentication_details")
    private Map<String, Object> authenticationDetails;
    
    @Schema(description = "The payment methods that were created using this payment method session")
    @JsonProperty("associated_payment_methods")
    private List<String> associatedPaymentMethods;
    
    @Schema(description = "The token-id created if there is tokenization_data present", example = "12345_tok_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("associated_token_id")
    private String associatedTokenId;
    
    @Schema(description = "The storage type for the payment method")
    @JsonProperty("storage_type")
    private String storageType;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

    public String getPspTokenization() {
        return pspTokenization;
    }

    public void setPspTokenization(String pspTokenization) {
        this.pspTokenization = pspTokenization;
    }

    public Map<String, Object> getNetworkTokenization() {
        return networkTokenization;
    }

    public void setNetworkTokenization(Map<String, Object> networkTokenization) {
        this.networkTokenization = networkTokenization;
    }

    public Map<String, Object> getTokenizationData() {
        return tokenizationData;
    }

    public void setTokenizationData(Map<String, Object> tokenizationData) {
        this.tokenizationData = tokenizationData;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Map<String, Object> getNextAction() {
        return nextAction;
    }

    public void setNextAction(Map<String, Object> nextAction) {
        this.nextAction = nextAction;
    }

    public Map<String, Object> getAuthenticationDetails() {
        return authenticationDetails;
    }

    public void setAuthenticationDetails(Map<String, Object> authenticationDetails) {
        this.authenticationDetails = authenticationDetails;
    }

    public List<String> getAssociatedPaymentMethods() {
        return associatedPaymentMethods;
    }

    public void setAssociatedPaymentMethods(List<String> associatedPaymentMethods) {
        this.associatedPaymentMethods = associatedPaymentMethods;
    }

    public String getAssociatedTokenId() {
        return associatedTokenId;
    }

    public void setAssociatedTokenId(String associatedTokenId) {
        this.associatedTokenId = associatedTokenId;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}

