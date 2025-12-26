package com.hyperswitch.common.dto;

/**
 * Request DTO for incremental authorization
 */
public class IncrementalAuthorizationRequest {
    private Long amount; // Total amount to authorize (in minor units)
    private String reason; // Reason for incremental authorization

    // Getters and Setters
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

