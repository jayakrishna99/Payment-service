package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a payout attempt
 */
@Table("payout_attempt")
public class PayoutAttemptEntity {

    @Id
    @Column("payout_attempt_id")
    private String payoutAttemptId;

    @Column("payout_id")
    private String payoutId;

    @Column("customer_id")
    private String customerId;

    @Column("merchant_id")
    private String merchantId;

    @Column("address_id")
    private String addressId;

    @Column("connector")
    private String connector;

    @Column("connector_payout_id")
    private String connectorPayoutId;

    @Column("payout_token")
    private String payoutToken;

    @Column("status")
    private String status;

    @Column("is_eligible")
    private Boolean isEligible;

    @Column("error_message")
    private String errorMessage;

    @Column("error_code")
    private String errorCode;

    @Column("business_country")
    private String businessCountry;

    @Column("business_label")
    private String businessLabel;

    @Column("created_at")
    private Instant createdAt;

    @Column("last_modified_at")
    private Instant lastModifiedAt;

    // Getters and Setters
    public String getPayoutAttemptId() {
        return payoutAttemptId;
    }

    public void setPayoutAttemptId(String payoutAttemptId) {
        this.payoutAttemptId = payoutAttemptId;
    }

    public String getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(String payoutId) {
        this.payoutId = payoutId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getConnectorPayoutId() {
        return connectorPayoutId;
    }

    public void setConnectorPayoutId(String connectorPayoutId) {
        this.connectorPayoutId = connectorPayoutId;
    }

    public String getPayoutToken() {
        return payoutToken;
    }

    public void setPayoutToken(String payoutToken) {
        this.payoutToken = payoutToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
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

    public String getBusinessCountry() {
        return businessCountry;
    }

    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }

    public String getBusinessLabel() {
        return businessLabel;
    }

    public void setBusinessLabel(String businessLabel) {
        this.businessLabel = businessLabel;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}

