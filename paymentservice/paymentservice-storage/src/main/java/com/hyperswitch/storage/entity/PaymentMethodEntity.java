package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Payment Method entity matching Hyperswitch schema
 */
@Table("payment_method")
public class PaymentMethodEntity {
    @Id
    @Column("id")
    private String id;
    
    @Column("payment_method_id")
    private String paymentMethodId;
    
    @Column("customer_id")
    private String customerId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("payment_method_type")
    private String paymentMethodType;
    
    @Column("payment_method_subtype")
    private String paymentMethodSubtype;
    
    @Column("payment_method_data")
    private Map<String, Object> paymentMethodData;
    
    @Column("locker_id")
    private String lockerId;
    
    @Column("last_used_at")
    private Instant lastUsedAt;
    
    @Column("status")
    private String status;
    
    @Column("connector_mandate_details")
    private Map<String, Object> connectorMandateDetails;
    
    @Column("network_transaction_id")
    private String networkTransactionId;
    
    @Column("client_secret")
    private String clientSecret;
    
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

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
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

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getPaymentMethodSubtype() {
        return paymentMethodSubtype;
    }

    public void setPaymentMethodSubtype(String paymentMethodSubtype) {
        this.paymentMethodSubtype = paymentMethodSubtype;
    }

    public Map<String, Object> getPaymentMethodData() {
        return paymentMethodData;
    }

    public void setPaymentMethodData(Map<String, Object> paymentMethodData) {
        this.paymentMethodData = paymentMethodData;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public Instant getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(Instant lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getConnectorMandateDetails() {
        return connectorMandateDetails;
    }

    public void setConnectorMandateDetails(Map<String, Object> connectorMandateDetails) {
        this.connectorMandateDetails = connectorMandateDetails;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public void setNetworkTransactionId(String networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
        private PaymentMethodEntity entity = new PaymentMethodEntity();

        public Builder id(String id) {
            entity.id = id;
            return this;
        }

        public Builder paymentMethodId(String paymentMethodId) {
            entity.paymentMethodId = paymentMethodId;
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

        public Builder paymentMethodType(String paymentMethodType) {
            entity.paymentMethodType = paymentMethodType;
            return this;
        }

        public Builder paymentMethodSubtype(String paymentMethodSubtype) {
            entity.paymentMethodSubtype = paymentMethodSubtype;
            return this;
        }

        public Builder paymentMethodData(Map<String, Object> paymentMethodData) {
            entity.paymentMethodData = paymentMethodData;
            return this;
        }

        public Builder lockerId(String lockerId) {
            entity.lockerId = lockerId;
            return this;
        }

        public Builder lastUsedAt(Instant lastUsedAt) {
            entity.lastUsedAt = lastUsedAt;
            return this;
        }

        public Builder status(String status) {
            entity.status = status;
            return this;
        }

        public Builder connectorMandateDetails(Map<String, Object> connectorMandateDetails) {
            entity.connectorMandateDetails = connectorMandateDetails;
            return this;
        }

        public Builder networkTransactionId(String networkTransactionId) {
            entity.networkTransactionId = networkTransactionId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            entity.clientSecret = clientSecret;
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

        public PaymentMethodEntity build() {
            return entity;
        }
    }
}

