package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Payment Method Session entity for v2 API
 */
@Table("payment_method_session")
public class PaymentMethodSessionEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("customer_id")
    private String customerId;
    
    @Column("billing")
    private Map<String, Object> billing;
    
    @Column("psp_tokenization")
    private String pspTokenization;
    
    @Column("network_tokenization")
    private Map<String, Object> networkTokenization;
    
    @Column("tokenization_data")
    private Map<String, Object> tokenizationData;
    
    @Column("return_url")
    private String returnUrl;
    
    @Column("expires_at")
    private Instant expiresAt;
    
    @Column("client_secret")
    private String clientSecret;
    
    @Column("next_action")
    private Map<String, Object> nextAction;
    
    @Column("authentication_details")
    private Map<String, Object> authenticationDetails;
    
    @Column("associated_payment_methods")
    private List<String> associatedPaymentMethods;
    
    @Column("associated_payment")
    private String associatedPayment;
    
    @Column("associated_token_id")
    private String associatedTokenId;
    
    @Column("storage_type")
    private String storageType;
    
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Object> getBilling() {
        return billing;
    }

    public void setBilling(Map<String, Object> billing) {
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

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
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

    public String getAssociatedPayment() {
        return associatedPayment;
    }

    public void setAssociatedPayment(String associatedPayment) {
        this.associatedPayment = associatedPayment;
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

