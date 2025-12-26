package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Customer entity matching Hyperswitch schema
 */
@Table("customer")
public class CustomerEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("customer_id")
    private String customerId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("name")
    private String name;
    
    @Column("email")
    private String email;
    
    @Column("phone")
    private String phone;
    
    @Column("phone_country_code")
    private String phoneCountryCode;
    
    @Column("description")
    private String description;
    
    @Column("metadata")
    private Map<String, Object> metadata;
    
    @Column("address_id")
    private String addressId;
    
    @Column("default_payment_method_id")
    private String defaultPaymentMethodId;
    
    @Column("connector_customer")
    private Map<String, Object> connectorCustomer;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getDefaultPaymentMethodId() {
        return defaultPaymentMethodId;
    }

    public void setDefaultPaymentMethodId(String defaultPaymentMethodId) {
        this.defaultPaymentMethodId = defaultPaymentMethodId;
    }

    public Map<String, Object> getConnectorCustomer() {
        return connectorCustomer;
    }

    public void setConnectorCustomer(Map<String, Object> connectorCustomer) {
        this.connectorCustomer = connectorCustomer;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CustomerEntity entity = new CustomerEntity();

        public Builder id(String id) {
            entity.id = id;
            return this;
        }

        public Builder customerId(String customerId) {
            entity.customerId = customerId;
            return this;
        }

        public Builder merchantId(String merchantId) {
            entity.merchantId = merchantId;
            return this;
        }

        public Builder name(String name) {
            entity.name = name;
            return this;
        }

        public Builder email(String email) {
            entity.email = email;
            return this;
        }

        public Builder phone(String phone) {
            entity.phone = phone;
            return this;
        }

        public Builder phoneCountryCode(String phoneCountryCode) {
            entity.phoneCountryCode = phoneCountryCode;
            return this;
        }

        public Builder description(String description) {
            entity.description = description;
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            entity.metadata = metadata;
            return this;
        }

        public Builder addressId(String addressId) {
            entity.addressId = addressId;
            return this;
        }

        public Builder defaultPaymentMethodId(String defaultPaymentMethodId) {
            entity.defaultPaymentMethodId = defaultPaymentMethodId;
            return this;
        }

        public Builder connectorCustomer(Map<String, Object> connectorCustomer) {
            entity.connectorCustomer = connectorCustomer;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            entity.createdAt = createdAt;
            return this;
        }

        public Builder modifiedAt(Instant modifiedAt) {
            entity.modifiedAt = modifiedAt;
            return this;
        }

        public CustomerEntity build() {
            return entity;
        }
    }
}

