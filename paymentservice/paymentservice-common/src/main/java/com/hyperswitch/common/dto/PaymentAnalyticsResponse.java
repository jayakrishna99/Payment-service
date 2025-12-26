package com.hyperswitch.common.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Response DTO for payment analytics
 */
public class PaymentAnalyticsResponse {
    private Long totalPayments;
    private Long successfulPayments;
    private Long failedPayments;
    private Long pendingPayments;
    private BigDecimal successRate;
    private Long totalVolume; // In minor units
    private Long successfulVolume; // In minor units
    private Long failedVolume; // In minor units
    private Instant startDate;
    private Instant endDate;
    private String currency;

    // Getters and Setters
    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Long getSuccessfulPayments() {
        return successfulPayments;
    }

    public void setSuccessfulPayments(Long successfulPayments) {
        this.successfulPayments = successfulPayments;
    }

    public Long getFailedPayments() {
        return failedPayments;
    }

    public void setFailedPayments(Long failedPayments) {
        this.failedPayments = failedPayments;
    }

    public Long getPendingPayments() {
        return pendingPayments;
    }

    public void setPendingPayments(Long pendingPayments) {
        this.pendingPayments = pendingPayments;
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

    public Long getFailedVolume() {
        return failedVolume;
    }

    public void setFailedVolume(Long failedVolume) {
        this.failedVolume = failedVolume;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

