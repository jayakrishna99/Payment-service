package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for authentication
 */
@Schema(description = "Response for authentication")
public class AuthenticationResponse {
    
    @Schema(description = "Authentication ID", example = "auth_123456")
    @JsonProperty("authentication_id")
    private String authenticationId;
    
    @Schema(description = "Merchant ID", example = "merchant_123")
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @Schema(description = "Payment method ID", example = "pm_123456")
    @JsonProperty("payment_method_id")
    private String paymentMethodId;
    
    @Schema(description = "Payment ID", example = "pay_123456")
    @JsonProperty("payment_id")
    private String paymentId;
    
    @Schema(description = "Authentication connector", example = "stripe")
    @JsonProperty("authentication_connector")
    private String authenticationConnector;
    
    @Schema(description = "Connector authentication ID", example = "conn_auth_123")
    @JsonProperty("connector_authentication_id")
    private String connectorAuthenticationId;
    
    @Schema(description = "Authentication status", example = "PENDING")
    @JsonProperty("authentication_status")
    private String authenticationStatus;
    
    @Schema(description = "Authentication lifecycle status", example = "IN_PROGRESS")
    @JsonProperty("authentication_lifecycle_status")
    private String authenticationLifecycleStatus;
    
    @Schema(description = "Authentication type", example = "OTP")
    @JsonProperty("authentication_type")
    private String authenticationType;
    
    @Schema(description = "Authentication data")
    @JsonProperty("authentication_data")
    private Map<String, Object> authenticationData;
    
    @Schema(description = "Error message")
    @JsonProperty("error_message")
    private String errorMessage;
    
    @Schema(description = "Error code")
    @JsonProperty("error_code")
    private String errorCode;
    
    @Schema(description = "Created at timestamp")
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @Schema(description = "Modified at timestamp")
    @JsonProperty("modified_at")
    private Instant modifiedAt;
    
    @Schema(description = "3DS method URL")
    @JsonProperty("three_ds_method_url")
    private String threeDsMethodUrl;
    
    @Schema(description = "ACS URL")
    @JsonProperty("acs_url")
    private String acsUrl;
    
    @Schema(description = "Challenge request")
    @JsonProperty("challenge_request")
    private String challengeRequest;
    
    @Schema(description = "Additional metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthenticationConnector() {
        return authenticationConnector;
    }

    public void setAuthenticationConnector(String authenticationConnector) {
        this.authenticationConnector = authenticationConnector;
    }

    public String getConnectorAuthenticationId() {
        return connectorAuthenticationId;
    }

    public void setConnectorAuthenticationId(String connectorAuthenticationId) {
        this.connectorAuthenticationId = connectorAuthenticationId;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public String getAuthenticationLifecycleStatus() {
        return authenticationLifecycleStatus;
    }

    public void setAuthenticationLifecycleStatus(String authenticationLifecycleStatus) {
        this.authenticationLifecycleStatus = authenticationLifecycleStatus;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Map<String, Object> getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(Map<String, Object> authenticationData) {
        this.authenticationData = authenticationData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public String getThreeDsMethodUrl() {
        return threeDsMethodUrl;
    }

    public void setThreeDsMethodUrl(String threeDsMethodUrl) {
        this.threeDsMethodUrl = threeDsMethodUrl;
    }

    public String getAcsUrl() {
        return acsUrl;
    }

    public void setAcsUrl(String acsUrl) {
        this.acsUrl = acsUrl;
    }

    public String getChallengeRequest() {
        return challengeRequest;
    }

    public void setChallengeRequest(String challengeRequest) {
        this.challengeRequest = challengeRequest;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

