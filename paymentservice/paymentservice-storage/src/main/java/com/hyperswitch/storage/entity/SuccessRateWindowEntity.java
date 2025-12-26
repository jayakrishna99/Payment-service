package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Entity representing a success rate window for time-window based metrics
 */
@Table("success_rate_window")
public class SuccessRateWindowEntity {

    @Id
    @Column("id")
    private String id;

    @Column("profile_id")
    private String profileId;

    @Column("connector")
    private String connector;

    @Column("payment_method")
    private String paymentMethod;

    @Column("currency")
    private String currency;

    @Column("window_start")
    private Instant windowStart;

    @Column("window_end")
    private Instant windowEnd;

    @Column("total_attempts")
    private Long totalAttempts;

    @Column("successful_attempts")
    private Long successfulAttempts;

    @Column("failed_attempts")
    private Long failedAttempts;

    @Column("success_rate")
    private BigDecimal successRate;

    @Column("created_at")
    private Instant createdAt;

    @Column("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Instant getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(Instant windowStart) {
        this.windowStart = windowStart;
    }

    public Instant getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(Instant windowEnd) {
        this.windowEnd = windowEnd;
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

