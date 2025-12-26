package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.DisputeStage;
import com.hyperswitch.common.types.DisputeStatus;

import java.time.Instant;

/**
 * Response DTO for dispute information
 */
public class DisputeResponse {
    private String disputeId;
    private String paymentId;
    private String attemptId;
    private String amount;
    private String currency;
    private DisputeStage disputeStage;
    private DisputeStatus disputeStatus;
    private String connector;
    private String connectorStatus;
    private String connectorDisputeId;
    private String connectorReason;
    private String connectorReasonCode;
    private Instant challengeRequiredBy;
    private Instant connectorCreatedAt;
    private Instant connectorUpdatedAt;
    private Instant createdAt;
    private String profileId;
    private String merchantConnectorId;

    // Getters and Setters
    public String getDisputeId() {
        return disputeId;
    }

    public void setDisputeId(String disputeId) {
        this.disputeId = disputeId;
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

    public DisputeStage getDisputeStage() {
        return disputeStage;
    }

    public void setDisputeStage(DisputeStage disputeStage) {
        this.disputeStage = disputeStage;
    }

    public DisputeStatus getDisputeStatus() {
        return disputeStatus;
    }

    public void setDisputeStatus(DisputeStatus disputeStatus) {
        this.disputeStatus = disputeStatus;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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
}

