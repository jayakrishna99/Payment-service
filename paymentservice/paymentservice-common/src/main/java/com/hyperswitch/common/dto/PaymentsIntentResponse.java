package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for payment intent (v2 API)
 */
public class PaymentsIntentResponse {
    private String id; // Global Payment ID
    private String status; // Intent status
    private AmountDetailsResponse amountDetails;
    private String clientSecret;
    private String merchantId;
    private String profileId;
    private String customerId;
    private String description;
    private String returnUrl;
    private String captureMethod;
    private String authenticationType;
    private Address billing;
    private Address shipping;
    private String statementDescriptor;
    private List<OrderDetailsWithAmount> orderDetails;
    private List<String> allowedPaymentMethodTypes;
    private Map<String, Object> metadata;
    private Map<String, Object> connectorMetadata;
    private Map<String, Object> featureMetadata;
    private Instant createdAt;
    private Instant modifiedAt;
    private List<PaymentAttemptResponse> paymentAttempts;
    
    public PaymentsIntentResponse() {
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public AmountDetailsResponse getAmountDetails() {
        return amountDetails;
    }
    
    public void setAmountDetails(AmountDetailsResponse amountDetails) {
        this.amountDetails = amountDetails;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getReturnUrl() {
        return returnUrl;
    }
    
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    
    public String getCaptureMethod() {
        return captureMethod;
    }
    
    public void setCaptureMethod(String captureMethod) {
        this.captureMethod = captureMethod;
    }
    
    public String getAuthenticationType() {
        return authenticationType;
    }
    
    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }
    
    public Address getBilling() {
        return billing;
    }
    
    public void setBilling(Address billing) {
        this.billing = billing;
    }
    
    public Address getShipping() {
        return shipping;
    }
    
    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }
    
    public String getStatementDescriptor() {
        return statementDescriptor;
    }
    
    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }
    
    public List<OrderDetailsWithAmount> getOrderDetails() {
        return orderDetails;
    }
    
    public void setOrderDetails(List<OrderDetailsWithAmount> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public List<String> getAllowedPaymentMethodTypes() {
        return allowedPaymentMethodTypes;
    }
    
    public void setAllowedPaymentMethodTypes(List<String> allowedPaymentMethodTypes) {
        this.allowedPaymentMethodTypes = allowedPaymentMethodTypes;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    public Map<String, Object> getConnectorMetadata() {
        return connectorMetadata;
    }
    
    public void setConnectorMetadata(Map<String, Object> connectorMetadata) {
        this.connectorMetadata = connectorMetadata;
    }
    
    public Map<String, Object> getFeatureMetadata() {
        return featureMetadata;
    }
    
    public void setFeatureMetadata(Map<String, Object> featureMetadata) {
        this.featureMetadata = featureMetadata;
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
    
    public List<PaymentAttemptResponse> getPaymentAttempts() {
        return paymentAttempts;
    }
    
    public void setPaymentAttempts(List<PaymentAttemptResponse> paymentAttempts) {
        this.paymentAttempts = paymentAttempts;
    }
}

