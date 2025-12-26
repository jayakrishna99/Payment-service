package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Request DTO for scheduling a payment capture
 */
public class ScheduleCaptureRequest {
    private Instant scheduledAt; // When to capture the payment
    private Long amount; // Optional: amount to capture (if null, captures full amount)

    // Getters and Setters
    public Instant getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Instant scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}

