package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import java.util.List;
import java.util.Map;

/**
 * Request DTO for creating a payment intent (v2 API)
 */
public class PaymentsCreateIntentRequest {
    private AmountDetails amountDetails;
    private String merchantReferenceId;
    private String routingAlgorithmId;
    private String captureMethod; // "automatic" or "manual"
    private String authenticationType; // "no_three_ds", "three_ds", etc.
    private Address billing;
    private Address shipping;
    private String customerId;
    private String customerPresent; // "present" or "absent"
    private String description;
    private String returnUrl;
    private String setupFutureUsage; // "off_session" or "on_session"
    private String statementDescriptor;
    private List<OrderDetailsWithAmount> orderDetails;
    private List<String> allowedPaymentMethodTypes;
    private Map<String, Object> metadata;
    private Map<String, Object> connectorMetadata;
    private Map<String, Object> featureMetadata;
    private Boolean paymentLinkEnabled;
    private Map<String, Object> paymentLinkConfig;
    private Boolean requestIncrementalAuthorization;
    private Integer sessionExpiry;
    private Map<String, Object> frmMetadata;
    private Boolean requestExternalThreeDsAuthentication;
    private Boolean force3dsChallenge;
    private MerchantConnectorAuthDetails merchantConnectorDetails;
    private Boolean enablePartialAuthorization;
    
    public PaymentsCreateIntentRequest() {
    }
    
    // Getters and Setters
    public AmountDetails getAmountDetails() {
        return amountDetails;
    }
    
    public void setAmountDetails(AmountDetails amountDetails) {
        this.amountDetails = amountDetails;
    }
    
    public String getMerchantReferenceId() {
        return merchantReferenceId;
    }
    
    public void setMerchantReferenceId(String merchantReferenceId) {
        this.merchantReferenceId = merchantReferenceId;
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
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
    
    public Boolean getPaymentLinkEnabled() {
        return paymentLinkEnabled;
    }
    
    public void setPaymentLinkEnabled(Boolean paymentLinkEnabled) {
        this.paymentLinkEnabled = paymentLinkEnabled;
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
    
    public Boolean getForce3dsChallenge() {
        return force3dsChallenge;
    }
    
    public void setForce3dsChallenge(Boolean force3dsChallenge) {
        this.force3dsChallenge = force3dsChallenge;
    }
    
    public MerchantConnectorAuthDetails getMerchantConnectorDetails() {
        return merchantConnectorDetails;
    }
    
    public void setMerchantConnectorDetails(MerchantConnectorAuthDetails merchantConnectorDetails) {
        this.merchantConnectorDetails = merchantConnectorDetails;
    }
    
    public Boolean getEnablePartialAuthorization() {
        return enablePartialAuthorization;
    }
    
    public void setEnablePartialAuthorization(Boolean enablePartialAuthorization) {
        this.enablePartialAuthorization = enablePartialAuthorization;
    }
}

