package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a revenue recovery attempt
 */
@Table("revenue_recovery")
public class RevenueRecoveryEntity {

    @Id
    @Column("recovery_id")
    private String recoveryId;

    @Column("merchant_id")
    private String merchantId;

    @Column("payment_id")
    private String paymentId;

    @Column("attempt_id")
    private String attemptId;

    @Column("profile_id")
    private String profileId;

    @Column("billing_mca_id")
    private String billingMcaId;

    @Column("recovery_status")
    private String recoveryStatus;

    @Column("retry_algorithm")
    private String retryAlgorithm;

    @Column("retry_count")
    private Integer retryCount;

    @Column("max_retries")
    private Integer maxRetries;

    @Column("retry_budget")
    private Long retryBudget; // Budget in minor units

    @Column("retry_budget_used")
    private Long retryBudgetUsed; // Budget used in minor units

    @Column("next_retry_at")
    private Instant nextRetryAt;

    @Column("last_error_code")
    private String lastErrorCode;

    @Column("last_error_message")
    private String lastErrorMessage;

    @Column("recovery_metadata")
    private String recoveryMetadata; // JSON string

    @Column("created_at")
    private Instant createdAt;

    @Column("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public String getRecoveryId() {
        return recoveryId;
    }

    public void setRecoveryId(String recoveryId) {
        this.recoveryId = recoveryId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getBillingMcaId() {
        return billingMcaId;
    }

    public void setBillingMcaId(String billingMcaId) {
        this.billingMcaId = billingMcaId;
    }

    public String getRecoveryStatus() {
        return recoveryStatus;
    }

    public void setRecoveryStatus(String recoveryStatus) {
        this.recoveryStatus = recoveryStatus;
    }

    public String getRetryAlgorithm() {
        return retryAlgorithm;
    }

    public void setRetryAlgorithm(String retryAlgorithm) {
        this.retryAlgorithm = retryAlgorithm;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Long getRetryBudget() {
        return retryBudget;
    }

    public void setRetryBudget(Long retryBudget) {
        this.retryBudget = retryBudget;
    }

    public Long getRetryBudgetUsed() {
        return retryBudgetUsed;
    }

    public void setRetryBudgetUsed(Long retryBudgetUsed) {
        this.retryBudgetUsed = retryBudgetUsed;
    }

    public Instant getNextRetryAt() {
        return nextRetryAt;
    }

    public void setNextRetryAt(Instant nextRetryAt) {
        this.nextRetryAt = nextRetryAt;
    }

    public String getLastErrorCode() {
        return lastErrorCode;
    }

    public void setLastErrorCode(String lastErrorCode) {
        this.lastErrorCode = lastErrorCode;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public String getRecoveryMetadata() {
        return recoveryMetadata;
    }

    public void setRecoveryMetadata(String recoveryMetadata) {
        this.recoveryMetadata = recoveryMetadata;
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

