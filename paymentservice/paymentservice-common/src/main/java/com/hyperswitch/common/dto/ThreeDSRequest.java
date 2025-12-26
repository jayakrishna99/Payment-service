package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request DTO for 3DS authentication
 */
public final class ThreeDSRequest {
    private final String paymentId;
    private final String authenticationId;
    private final Map<String, Object> authenticationData;
    private final String returnUrl;

    private ThreeDSRequest(Builder builder) {
        this.paymentId = builder.paymentId;
        this.authenticationId = builder.authenticationId;
        this.authenticationData = builder.authenticationData;
        this.returnUrl = builder.returnUrl;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public Map<String, Object> getAuthenticationData() {
        return authenticationData;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String paymentId;
        private String authenticationId;
        private Map<String, Object> authenticationData;
        private String returnUrl;

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder authenticationId(String authenticationId) {
            this.authenticationId = authenticationId;
            return this;
        }

        public Builder authenticationData(Map<String, Object> authenticationData) {
            this.authenticationData = authenticationData;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public ThreeDSRequest build() {
            return new ThreeDSRequest(this);
        }
    }
}

