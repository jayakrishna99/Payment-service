package com.hyperswitch.common.dto;

/**
 * Analytics DTO for revenue recovery metrics
 */
public class RevenueRecoveryAnalytics {
    private Long totalRecoveryAttempts;
    private Long successfulRecoveries;
    private Long failedRecoveries;
    private Long totalRecoveredAmount; // In minor units
    private Long totalBudgetUsed; // In minor units
    private Double recoveryRate; // Percentage
    private Double averageRetryCount;
    private Long activeRecoveries;
    private Long terminatedRecoveries;

    // Getters and Setters
    public Long getTotalRecoveryAttempts() {
        return totalRecoveryAttempts;
    }

    public void setTotalRecoveryAttempts(Long totalRecoveryAttempts) {
        this.totalRecoveryAttempts = totalRecoveryAttempts;
    }

    public Long getSuccessfulRecoveries() {
        return successfulRecoveries;
    }

    public void setSuccessfulRecoveries(Long successfulRecoveries) {
        this.successfulRecoveries = successfulRecoveries;
    }

    public Long getFailedRecoveries() {
        return failedRecoveries;
    }

    public void setFailedRecoveries(Long failedRecoveries) {
        this.failedRecoveries = failedRecoveries;
    }

    public Long getTotalRecoveredAmount() {
        return totalRecoveredAmount;
    }

    public void setTotalRecoveredAmount(Long totalRecoveredAmount) {
        this.totalRecoveredAmount = totalRecoveredAmount;
    }

    public Long getTotalBudgetUsed() {
        return totalBudgetUsed;
    }

    public void setTotalBudgetUsed(Long totalBudgetUsed) {
        this.totalBudgetUsed = totalBudgetUsed;
    }

    public Double getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(Double recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public Double getAverageRetryCount() {
        return averageRetryCount;
    }

    public void setAverageRetryCount(Double averageRetryCount) {
        this.averageRetryCount = averageRetryCount;
    }

    public Long getActiveRecoveries() {
        return activeRecoveries;
    }

    public void setActiveRecoveries(Long activeRecoveries) {
        this.activeRecoveries = activeRecoveries;
    }

    public Long getTerminatedRecoveries() {
        return terminatedRecoveries;
    }

    public void setTerminatedRecoveries(Long terminatedRecoveries) {
        this.terminatedRecoveries = terminatedRecoveries;
    }
}

