package com.hyperswitch.connectors;

import com.hyperswitch.common.types.Amount;

import java.util.Map;

/**
 * Request to be sent to a payment connector
 */
public final class ConnectorRequest {
    private final String paymentId;
    private final Amount amount;
    private final String currency;
    private final Map<String, Object> paymentMethodData;
    private final Map<String, String> connectorConfig;
    private final Map<String, Object> metadata;

    private ConnectorRequest(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentMethodData = builder.paymentMethodData;
        this.connectorConfig = builder.connectorConfig;
        this.metadata = builder.metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public Map<String, String> getConnectorConfig() {
        return connectorConfig;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public static class Builder {
        private String paymentId;
        private Amount amount;
        private String currency;
        private Map<String, Object> paymentMethodData;
        private Map<String, String> connectorConfig;
        private Map<String, Object> metadata;

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder amount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder paymentMethodData(Map<String, Object> paymentMethodData) {
            this.paymentMethodData = paymentMethodData;
            return this;
        }

        public Builder connectorConfig(Map<String, String> connectorConfig) {
            this.connectorConfig = connectorConfig;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public ConnectorRequest build() {
            return new ConnectorRequest(this);
        }
    }
}
