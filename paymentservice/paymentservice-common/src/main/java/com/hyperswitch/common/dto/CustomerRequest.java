package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.MerchantId;

import java.util.Map;

/**
 * Request DTO for creating or updating a customer
 */
public final class CustomerRequest {
    private final MerchantId merchantId;
    private final String name;
    private final String email;
    private final String phone;
    private final String phoneCountryCode;
    private final String description;
    private final Map<String, Object> metadata;
    private final String addressId;
    private final String defaultPaymentMethodId;

    private CustomerRequest(Builder builder) {
        this.merchantId = builder.merchantId;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.phoneCountryCode = builder.phoneCountryCode;
        this.description = builder.description;
        this.metadata = builder.metadata;
        this.addressId = builder.addressId;
        this.defaultPaymentMethodId = builder.defaultPaymentMethodId;
    }

    public MerchantId getMerchantId() {
        return merchantId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getDefaultPaymentMethodId() {
        return defaultPaymentMethodId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MerchantId merchantId;
        private String name;
        private String email;
        private String phone;
        private String phoneCountryCode;
        private String description;
        private Map<String, Object> metadata;
        private String addressId;
        private String defaultPaymentMethodId;

        public Builder merchantId(MerchantId merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder phoneCountryCode(String phoneCountryCode) {
            this.phoneCountryCode = phoneCountryCode;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder addressId(String addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder defaultPaymentMethodId(String defaultPaymentMethodId) {
            this.defaultPaymentMethodId = defaultPaymentMethodId;
            return this;
        }

        public CustomerRequest build() {
            return new CustomerRequest(this);
        }
    }
}

