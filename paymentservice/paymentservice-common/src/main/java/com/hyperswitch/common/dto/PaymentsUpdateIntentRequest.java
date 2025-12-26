package com.hyperswitch.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Request DTO for updating a payment intent (v2 API)
 */
public class PaymentsUpdateIntentRequest {
    private AmountDetailsUpdate amountDetails;
    private String routingAlgorithmId;
    private String captureMethod;
    private String authenticationType;
    private Address billing;
    private Address shipping;
    private String customerPresent;
    private String description;
    private String returnUrl;
    private String setupFutureUsage;
    private String statementDescriptor;
    private List<OrderDetailsWithAmount> orderDetails;
    private List<String> allowedPaymentMethodTypes;
    private Map<String, Object> metadata;
    private Map<String, Object> connectorMetadata;
    private Map<String, Object> featureMetadata;
    private Map<String, Object> paymentLinkConfig;
    private Boolean requestIncrementalAuthorization;
    private Integer sessionExpiry;
    private Map<String, Object> frmMetadata;
    private Boolean requestExternalThreeDsAuthentication;
    private String setActiveAttemptId; // "set" or "unset"
    private Boolean enablePartialAuthorization;
    
    public PaymentsUpdateIntentRequest() {
    }
    
    // Getters and Setters
    public AmountDetailsUpdate getAmountDetails() {
        return amountDetails;
    }
    
    public void setAmountDetails(AmountDetailsUpdate amountDetails) {
        this.amountDetails = amountDetails;
    }
    
    public String getRoutingAlgorithmId() {
        return routingAlgorithmId;
    }
    
    public void setRoutingAlgorithmId(String routingAlgorithmId) {
        this.routingAlgorithmId = routingAlgorithmId;
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
    
    public String getCustomerPresent() {
        return customerPresent;
    }
    
    public void setCustomerPresent(String customerPresent) {
        this.customerPresent = customerPresent;
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
    
    public String getSetupFutureUsage() {
        return setupFutureUsage;
    }
    
    public void setSetupFutureUsage(String setupFutureUsage) {
        this.setupFutureUsage = setupFutureUsage;
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
    
    public Map<String, Object> getPaymentLinkConfig() {
        return paymentLinkConfig;
    }
    
    public void setPaymentLinkConfig(Map<String, Object> paymentLinkConfig) {
        this.paymentLinkConfig = paymentLinkConfig;
    }
    
    public Boolean getRequestIncrementalAuthorization() {
        return requestIncrementalAuthorization;
    }
    
    public void setRequestIncrementalAuthorization(Boolean requestIncrementalAuthorization) {
        this.requestIncrementalAuthorization = requestIncrementalAuthorization;
    }
    
    public Integer getSessionExpiry() {
        return sessionExpiry;
    }
    
    public void setSessionExpiry(Integer sessionExpiry) {
        this.sessionExpiry = sessionExpiry;
    }
    
    public Map<String, Object> getFrmMetadata() {
        return frmMetadata;
    }
    
    public void setFrmMetadata(Map<String, Object> frmMetadata) {
        this.frmMetadata = frmMetadata;
    }
    
    public Boolean getRequestExternalThreeDsAuthentication() {
        return requestExternalThreeDsAuthentication;
    }
    
    public void setRequestExternalThreeDsAuthentication(Boolean requestExternalThreeDsAuthentication) {
        this.requestExternalThreeDsAuthentication = requestExternalThreeDsAuthentication;
    }
    
    public String getSetActiveAttemptId() {
        return setActiveAttemptId;
    }
    
    public void setSetActiveAttemptId(String setActiveAttemptId) {
        this.setActiveAttemptId = setActiveAttemptId;
    }
    
    public Boolean getEnablePartialAuthorization() {
        return enablePartialAuthorization;
    }
    
    public void setEnablePartialAuthorization(Boolean enablePartialAuthorization) {
        this.enablePartialAuthorization = enablePartialAuthorization;
    }
}

