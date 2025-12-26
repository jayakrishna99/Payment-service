package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.RecoveryStatus;
import com.hyperswitch.common.types.RevenueRecoveryAlgorithmType;

import java.time.Instant;

/**
 * Response DTO for revenue recovery information
 */
public class RevenueRecoveryResponse {
    private String recoveryId;
    private String merchantId;
    private String paymentId;
    private String attemptId;
    private String profileId;
    private String billingMcaId;
    private RecoveryStatus recoveryStatus;
    private RevenueRecoveryAlgorithmType retryAlgorithm;
    private Integer retryCount;
    private Integer maxRetries;
    private Long retryBudget;
    private Long retryBudgetUsed;
    private Instant nextRetryAt;
    private String lastErrorCode;
    private String lastErrorMessage;
    private String recoveryMetadata;
    private Instant createdAt;
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

    public RecoveryStatus getRecoveryStatus() {
        return recoveryStatus;
    }

    public void setRecoveryStatus(RecoveryStatus recoveryStatus) {
        this.recoveryStatus = recoveryStatus;
    }

    public RevenueRecoveryAlgorithmType getRetryAlgorithm() {
        return retryAlgorithm;
    }

    public void setRetryAlgorithm(RevenueRecoveryAlgorithmType retryAlgorithm) {
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

