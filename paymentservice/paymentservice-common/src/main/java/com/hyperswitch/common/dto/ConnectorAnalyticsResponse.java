package com.hyperswitch.common.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Response DTO for connector analytics
 */
public class ConnectorAnalyticsResponse {
    private String connectorId;
    private String connectorName;
    private Long totalAttempts;
    private Long successfulAttempts;
    private Long failedAttempts;
    private BigDecimal successRate;
    private Long totalVolume; // In minor units
    private Long successfulVolume; // In minor units
    private Double averageResponseTime; // In milliseconds
    private Instant startDate;
    private Instant endDate;

    // Getters and Setters
    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public Long getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(Long totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public Long getSuccessfulAttempts() {
        return successfulAttempts;
    }

    public void setSuccessfulAttempts(Long successfulAttempts) {
        this.successfulAttempts = successfulAttempts;
    }

    public Long getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Long failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public BigDecimal getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(BigDecimal successRate) {
        this.successRate = successRate;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Long getSuccessfulVolume() {
        return successfulVolume;
    }

    public void setSuccessfulVolume(Long successfulVolume) {
        this.successfulVolume = successfulVolume;
    }

    public Double getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(Double averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
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
}

