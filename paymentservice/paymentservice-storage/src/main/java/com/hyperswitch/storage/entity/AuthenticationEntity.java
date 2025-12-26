package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Authentication entity matching Hyperswitch schema
 */
@Table("authentication")
public class AuthenticationEntity {
    @Id
    @Column("authentication_id")
    private String authenticationId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("authentication_connector")
    private String authenticationConnector;
    
    @Column("connector_authentication_id")
    private String connectorAuthenticationId;
    
    @Column("authentication_data")
    private Map<String, Object> authenticationData;
    
    @Column("payment_method_id")
    private String paymentMethodId;
    
    @Column("authentication_type")
    private String authenticationType;
    
    @Column("authentication_status")
    private String authenticationStatus;
    
    @Column("authentication_lifecycle_status")
    private String authenticationLifecycleStatus;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;
    
    @Column("error_message")
    private String errorMessage;
    
    @Column("error_code")
    private String errorCode;
    
    @Column("connector_metadata")
    private Map<String, Object> connectorMetadata;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("threeds_server_transaction_id")
    private String threedsServerTransactionId;
    
    @Column("cavv")
    private String cavv;
    
    @Column("authentication_flow_type")
    private String authenticationFlowType;
    
    @Column("eci")
    private String eci;
    
    @Column("trans_status")
    private String transStatus;
    
    @Column("acquirer_bin")
    private String acquirerBin;
    
    @Column("acquirer_merchant_id")
    private String acquirerMerchantId;
    
    @Column("three_ds_method_data")
    private String threeDsMethodData;
    
    @Column("three_ds_method_url")
    private String threeDsMethodUrl;
    
    @Column("acs_url")
    private String acsUrl;
    
    @Column("challenge_request")
    private String challengeRequest;
    
    @Column("acs_reference_number")
    private String acsReferenceNumber;
    
    @Column("acs_trans_id")
    private String acsTransId;

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

    public Map<String, Object> getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(Map<String, Object> authenticationData) {
        this.authenticationData = authenticationData;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
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

    public Map<String, Object> getConnectorMetadata() {
        return connectorMetadata;
    }

    public void setConnectorMetadata(Map<String, Object> connectorMetadata) {
        this.connectorMetadata = connectorMetadata;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getThreedsServerTransactionId() {
        return threedsServerTransactionId;
    }

    public void setThreedsServerTransactionId(String threedsServerTransactionId) {
        this.threedsServerTransactionId = threedsServerTransactionId;
    }

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getAuthenticationFlowType() {
        return authenticationFlowType;
    }

    public void setAuthenticationFlowType(String authenticationFlowType) {
        this.authenticationFlowType = authenticationFlowType;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getAcquirerBin() {
        return acquirerBin;
    }

    public void setAcquirerBin(String acquirerBin) {
        this.acquirerBin = acquirerBin;
    }

    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }

    public String getThreeDsMethodData() {
        return threeDsMethodData;
    }

    public void setThreeDsMethodData(String threeDsMethodData) {
        this.threeDsMethodData = threeDsMethodData;
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

    public String getAcsReferenceNumber() {
        return acsReferenceNumber;
    }

    public void setAcsReferenceNumber(String acsReferenceNumber) {
        this.acsReferenceNumber = acsReferenceNumber;
    }

    public String getAcsTransId() {
        return acsTransId;
    }

    public void setAcsTransId(String acsTransId) {
        this.acsTransId = acsTransId;
    }
}

