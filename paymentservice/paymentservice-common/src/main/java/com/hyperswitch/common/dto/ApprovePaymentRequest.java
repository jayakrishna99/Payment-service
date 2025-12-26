package com.hyperswitch.common.dto;

/**
 * Request DTO for approving a payment
 */
public class ApprovePaymentRequest {
    private String reason; // Optional reason for approval

    // Getters and Setters
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

