package com.hyperswitch.connectors;

import java.util.Map;

/**
 * Parsed webhook payload from connector
 */
public final class WebhookPayload {
    private final String eventType;
    private final String paymentId;
    private final String attemptId;
    private final String connectorTransactionId;
    private final String status;
    private final Map<String, Object> data;
    private final String merchantId;
    private final String frmId;
    private final String frmName;
    private final Integer frmScore;
    private final String frmReason;

    private WebhookPayload(Builder builder) {
        this.eventType = builder.eventType;
        this.paymentId = builder.paymentId;
        this.attemptId = builder.attemptId;
        this.connectorTransactionId = builder.connectorTransactionId;
        this.status = builder.status;
        this.data = builder.data;
        this.merchantId = builder.merchantId;
        this.frmId = builder.frmId;
        this.frmName = builder.frmName;
        this.frmScore = builder.frmScore;
        this.frmReason = builder.frmReason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEventType() {
        return eventType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getFrmId() {
        return frmId;
    }

    public String getFrmName() {
        return frmName;
    }

    public Integer getFrmScore() {
        return frmScore;
    }

    public String getFrmReason() {
        return frmReason;
    }

    public static class Builder {
        private String eventType;
        private String paymentId;
        private String attemptId;
        private String connectorTransactionId;
        private String status;
        private Map<String, Object> data;
        private String merchantId;
        private String frmId;
        private String frmName;
        private Integer frmScore;
        private String frmReason;

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder connectorTransactionId(String connectorTransactionId) {
            this.connectorTransactionId = connectorTransactionId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder data(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public Builder attemptId(String attemptId) {
            this.attemptId = attemptId;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder frmId(String frmId) {
            this.frmId = frmId;
            return this;
        }

        public Builder frmName(String frmName) {
            this.frmName = frmName;
            return this;
        }

        public Builder frmScore(Integer frmScore) {
            this.frmScore = frmScore;
            return this;
        }

        public Builder frmReason(String frmReason) {
            this.frmReason = frmReason;
            return this;
        }

        public WebhookPayload build() {
            return new WebhookPayload(this);
        }
    }
}
