package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Request DTO for creating a reconciliation run
 */
public class ReconciliationRequest {
    private String reconciliationType; // "TWO_WAY" or "THREE_WAY"
    private Instant startDate;
    private Instant endDate;
    private String connectorId;

    // Getters and Setters
    public String getReconciliationType() {
        return reconciliationType;
    }

    public void setReconciliationType(String reconciliationType) {
        this.reconciliationType = reconciliationType;
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

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }
}

