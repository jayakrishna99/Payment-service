package com.hyperswitch.common.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

/**
 * Response DTO for revenue analytics
 */
public class RevenueAnalyticsResponse {
    private Long totalRevenue; // In minor units
    private Long netRevenue; // In minor units (after refunds)
    private Long refundedAmount; // In minor units
    private Long capturedAmount; // In minor units
    private Long authorizedAmount; // In minor units
    private Map<String, Long> revenueByCurrency; // Currency -> Amount in minor units
    private Map<String, Long> revenueByPaymentMethod; // Payment method -> Amount in minor units
    private BigDecimal refundRate;
    private Instant startDate;
    private Instant endDate;

    // Getters and Setters
    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Long getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(Long netRevenue) {
        this.netRevenue = netRevenue;
    }

    public Long getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Long refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Long getCapturedAmount() {
        return capturedAmount;
    }

    public void setCapturedAmount(Long capturedAmount) {
        this.capturedAmount = capturedAmount;
    }

    public Long getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(Long authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public Map<String, Long> getRevenueByCurrency() {
        return revenueByCurrency;
    }

    public void setRevenueByCurrency(Map<String, Long> revenueByCurrency) {
        this.revenueByCurrency = revenueByCurrency;
    }

    public Map<String, Long> getRevenueByPaymentMethod() {
        return revenueByPaymentMethod;
    }

    public void setRevenueByPaymentMethod(Map<String, Long> revenueByPaymentMethod) {
        this.revenueByPaymentMethod = revenueByPaymentMethod;
    }

    public BigDecimal getRefundRate() {
        return refundRate;
    }

    public void setRefundRate(BigDecimal refundRate) {
        this.refundRate = refundRate;
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

