package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.FraudCheckStatus;
import com.hyperswitch.common.types.FraudCheckType;

import java.time.Instant;

/**
 * Response DTO for fraud check information
 */
public class FraudCheckResponse {
    private String frmId;
    private String paymentId;
    private String merchantId;
    private String attemptId;
    private Instant createdAt;
    private String frmName;
    private String frmTransactionId;
    private FraudCheckType frmTransactionType;
    private FraudCheckStatus frmStatus;
    private Integer frmScore;
    private String frmReason;
    private String frmError;
    private String paymentDetails;
    private String metadata;
    private Instant modifiedAt;

    // Getters and Setters
    public String getFrmId() {
        return frmId;
    }

    public void setFrmId(String frmId) {
        this.frmId = frmId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getFrmName() {
        return frmName;
    }

    public void setFrmName(String frmName) {
        this.frmName = frmName;
    }

    public String getFrmTransactionId() {
        return frmTransactionId;
    }

    public void setFrmTransactionId(String frmTransactionId) {
        this.frmTransactionId = frmTransactionId;
    }

    public FraudCheckType getFrmTransactionType() {
        return frmTransactionType;
    }

    public void setFrmTransactionType(FraudCheckType frmTransactionType) {
        this.frmTransactionType = frmTransactionType;
    }

    public FraudCheckStatus getFrmStatus() {
        return frmStatus;
    }

    public void setFrmStatus(FraudCheckStatus frmStatus) {
        this.frmStatus = frmStatus;
    }

    public Integer getFrmScore() {
        return frmScore;
    }

    public void setFrmScore(Integer frmScore) {
        this.frmScore = frmScore;
    }

    public String getFrmReason() {
        return frmReason;
    }

    public void setFrmReason(String frmReason) {
        this.frmReason = frmReason;
    }

    public String getFrmError() {
        return frmError;
    }

    public void setFrmError(String frmError) {
        this.frmError = frmError;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

