package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.MerchantId;

import java.time.Instant;
import java.util.Map;

/**
 * Domain model for Customer
 */
public final class Customer {
    private final CustomerId customerId;
    private final MerchantId merchantId;
    private final String name;
    private final String email;
    private final String phone;
    private final String phoneCountryCode;
    private final String description;
    private final Map<String, Object> metadata;
    private final String addressId;
    private final String defaultPaymentMethodId;
    private final Map<String, Object> connectorCustomer;
    private final Instant createdAt;
    private final Instant modifiedAt;

    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.merchantId = builder.merchantId;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.phoneCountryCode = builder.phoneCountryCode;
        this.description = builder.description;
        this.metadata = builder.metadata;
        this.addressId = builder.addressId;
        this.defaultPaymentMethodId = builder.defaultPaymentMethodId;
        this.connectorCustomer = builder.connectorCustomer;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
    }

    public CustomerId getCustomerId() {
        return customerId;
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

    public Map<String, Object> getConnectorCustomer() {
        return connectorCustomer;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CustomerId customerId;
        private MerchantId merchantId;
        private String name;
        private String email;
        private String phone;
        private String phoneCountryCode;
        private String description;
        private Map<String, Object> metadata;
        private String addressId;
        private String defaultPaymentMethodId;
        private Map<String, Object> connectorCustomer;
        private Instant createdAt;
        private Instant modifiedAt;

        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }

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

        public Builder connectorCustomer(Map<String, Object> connectorCustomer) {
            this.connectorCustomer = connectorCustomer;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder modifiedAt(Instant modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}

