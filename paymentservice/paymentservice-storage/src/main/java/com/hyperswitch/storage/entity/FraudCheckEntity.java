package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a fraud check
 */
@Table("fraud_check")
public class FraudCheckEntity {

    @Id
    @Column("frm_id")
    private String frmId;

    @Column("payment_id")
    private String paymentId;

    @Column("merchant_id")
    private String merchantId;

    @Column("attempt_id")
    private String attemptId;

    @Column("created_at")
    private Instant createdAt;

    @Column("frm_name")
    private String frmName;

    @Column("frm_transaction_id")
    private String frmTransactionId;

    @Column("frm_transaction_type")
    private String frmTransactionType;

    @Column("frm_status")
    private String frmStatus;

    @Column("frm_score")
    private Integer frmScore;

    @Column("frm_reason")
    private String frmReason; // JSON string

    @Column("frm_error")
    private String frmError;

    @Column("payment_details")
    private String paymentDetails; // JSON string

    @Column("metadata")
    private String metadata; // JSON string

    @Column("modified_at")
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

    public String getFrmTransactionType() {
        return frmTransactionType;
    }

    public void setFrmTransactionType(String frmTransactionType) {
        this.frmTransactionType = frmTransactionType;
    }

    public String getFrmStatus() {
        return frmStatus;
    }

    public void setFrmStatus(String frmStatus) {
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

