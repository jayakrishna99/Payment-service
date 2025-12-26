package com.hyperswitch.connectors;

import java.util.Map;

/**
 * Response from a payment connector
 */
public final class ConnectorResponse {
    private final String status;
    private final String connectorTransactionId;
    private final String errorCode;
    private final String errorMessage;
    private final Map<String, Object> additionalData;
    private final boolean requires3DS;
    private final String redirectUrl;
    
    private ConnectorResponse(Builder builder) {
        this.status = builder.status;
        this.connectorTransactionId = builder.connectorTransactionId;
        this.errorCode = builder.errorCode;
        this.errorMessage = builder.errorMessage;
        this.additionalData = builder.additionalData;
        this.requires3DS = builder.requires3DS;
        this.redirectUrl = builder.redirectUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getStatus() {
        return status;
    }

    public String getConnectorTransactionId() {
        return connectorTransactionId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public boolean isRequires3DS() {
        return requires3DS;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
    
    // Helper method to check if response indicates success
    public boolean isSuccess() {
        return "succeeded".equalsIgnoreCase(status) || "success".equalsIgnoreCase(status);
    }

    public static class Builder {
        private String status;
        private String connectorTransactionId;
        private String errorCode;
        private String errorMessage;
        private Map<String, Object> additionalData;
        private boolean requires3DS;
        private String redirectUrl;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder connectorTransactionId(String connectorTransactionId) {
            this.connectorTransactionId = connectorTransactionId;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder additionalData(Map<String, Object> additionalData) {
            this.additionalData = additionalData;
            return this;
        }

        public Builder requires3DS(boolean requires3DS) {
            this.requires3DS = requires3DS;
            return this;
        }

        public Builder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public ConnectorResponse build() {
            return new ConnectorResponse(this);
        }
    }
}
