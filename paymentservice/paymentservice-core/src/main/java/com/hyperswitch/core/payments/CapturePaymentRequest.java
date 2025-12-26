package com.hyperswitch.core.payments;

import com.hyperswitch.common.types.Amount;

/**
 * Request to capture a payment
 */
public final class CapturePaymentRequest {
    private final Amount amountToCapture; // null means capture full amount

    private CapturePaymentRequest(Builder builder) {
        this.amountToCapture = builder.amountToCapture;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Amount getAmountToCapture() {
        return amountToCapture;
    }

    public static class Builder {
        private Amount amountToCapture;

        public Builder amountToCapture(Amount amountToCapture) {
            this.amountToCapture = amountToCapture;
            return this;
        }

        public CapturePaymentRequest build() {
            return new CapturePaymentRequest(this);
        }
    }
}
