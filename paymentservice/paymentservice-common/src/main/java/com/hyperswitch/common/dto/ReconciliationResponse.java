package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Response DTO for reconciliation information
 */
public class ReconciliationResponse {
    private String reconciliationId;
    private String merchantId;
    private String reconciliationType;
    private String status;
    private Instant startDate;
    private Instant endDate;
    private Integer totalTransactions;
    private Integer matchedTransactions;
    private Integer unmatchedTransactions;
    private String discrepancies;
    private String connectorId;
    private String reconciliationData;
    private Instant createdAt;
    private Instant modifiedAt;

    // Getters and Setters
    public String getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(String reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getReconciliationType() {
        return reconciliationType;
    }

    public void setReconciliationType(String reconciliationType) {
        this.reconciliationType = reconciliationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Integer getMatchedTransactions() {
        return matchedTransactions;
    }

    public void setMatchedTransactions(Integer matchedTransactions) {
        this.matchedTransactions = matchedTransactions;
    }

    public Integer getUnmatchedTransactions() {
        return unmatchedTransactions;
    }

    public void setUnmatchedTransactions(Integer unmatchedTransactions) {
        this.unmatchedTransactions = unmatchedTransactions;
    }

    public String getDiscrepancies() {
        return discrepancies;
    }

    public void setDiscrepancies(String discrepancies) {
        this.discrepancies = discrepancies;
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getReconciliationData() {
        return reconciliationData;
    }

    public void setReconciliationData(String reconciliationData) {
        this.reconciliationData = reconciliationData;
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

