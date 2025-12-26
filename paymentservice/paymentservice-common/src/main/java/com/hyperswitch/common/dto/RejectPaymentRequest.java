package com.hyperswitch.common.dto;

/**
 * Request DTO for rejecting a payment
 */
public class RejectPaymentRequest {
    private String reason; // Reason for rejection

    // Getters and Setters
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

