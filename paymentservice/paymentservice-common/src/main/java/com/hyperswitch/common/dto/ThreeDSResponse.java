package com.hyperswitch.common.dto;

/**
 * Response DTO for 3DS authentication
 */
public final class ThreeDSResponse {
    private final String redirectUrl;
    private final String authenticationId;
    private final String status;
    private final String message;

    private ThreeDSResponse(Builder builder) {
        this.redirectUrl = builder.redirectUrl;
        this.authenticationId = builder.authenticationId;
        this.status = builder.status;
        this.message = builder.message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String redirectUrl;
        private String authenticationId;
        private String status;
        private String message;

        public Builder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder authenticationId(String authenticationId) {
            this.authenticationId = authenticationId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ThreeDSResponse build() {
            return new ThreeDSResponse(this);
        }
    }
}

