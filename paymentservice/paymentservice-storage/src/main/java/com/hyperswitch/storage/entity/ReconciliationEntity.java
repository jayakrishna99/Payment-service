package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Entity representing a reconciliation run
 */
@Table("reconciliation")
public class ReconciliationEntity {

    @Id
    @Column("reconciliation_id")
    private String reconciliationId;

    @Column("merchant_id")
    private String merchantId;

    @Column("reconciliation_type")
    private String reconciliationType; // "TWO_WAY" or "THREE_WAY"

    @Column("status")
    private String status; // "PENDING", "IN_PROGRESS", "COMPLETED", "FAILED"

    @Column("start_date")
    private Instant startDate;

    @Column("end_date")
    private Instant endDate;

    @Column("total_transactions")
    private Integer totalTransactions;

    @Column("matched_transactions")
    private Integer matchedTransactions;

    @Column("unmatched_transactions")
    private Integer unmatchedTransactions;

    @Column("discrepancies")
    private String discrepancies; // JSON string

    @Column("connector_id")
    private String connectorId;

    @Column("reconciliation_data")
    private String reconciliationData; // JSON string

    @Column("created_at")
    private Instant createdAt;

    @Column("modified_at")
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

