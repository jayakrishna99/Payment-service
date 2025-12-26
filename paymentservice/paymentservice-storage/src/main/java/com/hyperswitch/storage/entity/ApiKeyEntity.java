package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * API Key entity
 */
@Table("api_keys")
public class ApiKeyEntity {
    @Id
    @Column("key_id")
    private String keyId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("name")
    private String name;
    
    @Column("description")
    private String description;
    
    @Column("hash_key")
    private String hashKey;
    
    @Column("hashed_api_key")
    private String hashedApiKey;
    
    @Column("prefix")
    private String prefix;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("expires_at")
    private Instant expiresAt;
    
    @Column("last_used")
    private Instant lastUsed;

    // Getters and Setters
    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getHashedApiKey() {
        return hashedApiKey;
    }

    public void setHashedApiKey(String hashedApiKey) {
        this.hashedApiKey = hashedApiKey;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Instant getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Instant lastUsed) {
        this.lastUsed = lastUsed;
    }
}

