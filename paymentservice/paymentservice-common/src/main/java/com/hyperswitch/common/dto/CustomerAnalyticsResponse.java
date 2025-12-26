package com.hyperswitch.common.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Response DTO for customer analytics
 */
public class CustomerAnalyticsResponse {
    private String customerId;
    private Long totalPayments;
    private Long successfulPayments;
    private Long failedPayments;
    private BigDecimal successRate;
    private Long totalSpent; // In minor units
    private Long averageTransactionValue; // In minor units
    private Long lifetimeValue; // In minor units
    private Instant firstPaymentDate;
    private Instant lastPaymentDate;
    private String preferredPaymentMethod;
    private String preferredCurrency;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    public BigDecimal getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(BigDecimal successRate) {
        this.successRate = successRate;
    }

    public Long getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Long totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Long getAverageTransactionValue() {
        return averageTransactionValue;
    }

    public void setAverageTransactionValue(Long averageTransactionValue) {
        this.averageTransactionValue = averageTransactionValue;
    }

    public Long getLifetimeValue() {
        return lifetimeValue;
    }

    public void setLifetimeValue(Long lifetimeValue) {
        this.lifetimeValue = lifetimeValue;
    }

    public Instant getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Instant firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Instant getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Instant lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }
}

