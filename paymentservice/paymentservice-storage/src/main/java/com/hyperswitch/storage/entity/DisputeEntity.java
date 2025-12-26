package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a dispute/chargeback
 */
@Table("dispute")
public class DisputeEntity {

    @Id
    @Column("dispute_id")
    private String disputeId;

    @Column("amount")
    private String amount;

    @Column("currency")
    private String currency;

    @Column("dispute_stage")
    private String disputeStage;

    @Column("dispute_status")
    private String disputeStatus;

    @Column("payment_id")
    private String paymentId;

    @Column("attempt_id")
    private String attemptId;

    @Column("merchant_id")
    private String merchantId;

    @Column("connector_status")
    private String connectorStatus;

    @Column("connector_dispute_id")
    private String connectorDisputeId;

    @Column("connector_reason")
    private String connectorReason;

    @Column("connector_reason_code")
    private String connectorReasonCode;

    @Column("challenge_required_by")
    private Instant challengeRequiredBy;

    @Column("connector_created_at")
    private Instant connectorCreatedAt;

    @Column("connector_updated_at")
    private Instant connectorUpdatedAt;

    @Column("connector")
    private String connector;

    @Column("evidence")
    private String evidence; // JSON string

    @Column("profile_id")
    private String profileId;

    @Column("merchant_connector_id")
    private String merchantConnectorId;

    @Column("created_at")
    private Instant createdAt;

    @Column("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public String getDisputeId() {
        return disputeId;
    }

    public void setDisputeId(String disputeId) {
        this.disputeId = disputeId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDisputeStage() {
        return disputeStage;
    }

    public void setDisputeStage(String disputeStage) {
        this.disputeStage = disputeStage;
    }

    public String getDisputeStatus() {
        return disputeStatus;
    }

    public void setDisputeStatus(String disputeStatus) {
        this.disputeStatus = disputeStatus;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getConnectorStatus() {
        return connectorStatus;
    }

    public void setConnectorStatus(String connectorStatus) {
        this.connectorStatus = connectorStatus;
    }

    public String getConnectorDisputeId() {
        return connectorDisputeId;
    }

    public void setConnectorDisputeId(String connectorDisputeId) {
        this.connectorDisputeId = connectorDisputeId;
    }

    public String getConnectorReason() {
        return connectorReason;
    }

    public void setConnectorReason(String connectorReason) {
        this.connectorReason = connectorReason;
    }

    public String getConnectorReasonCode() {
        return connectorReasonCode;
    }

    public void setConnectorReasonCode(String connectorReasonCode) {
        this.connectorReasonCode = connectorReasonCode;
    }

    public Instant getChallengeRequiredBy() {
        return challengeRequiredBy;
    }

    public void setChallengeRequiredBy(Instant challengeRequiredBy) {
        this.challengeRequiredBy = challengeRequiredBy;
    }

    public Instant getConnectorCreatedAt() {
        return connectorCreatedAt;
    }

    public void setConnectorCreatedAt(Instant connectorCreatedAt) {
        this.connectorCreatedAt = connectorCreatedAt;
    }

    public Instant getConnectorUpdatedAt() {
        return connectorUpdatedAt;
    }

    public void setConnectorUpdatedAt(Instant connectorUpdatedAt) {
        this.connectorUpdatedAt = connectorUpdatedAt;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }

    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
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

