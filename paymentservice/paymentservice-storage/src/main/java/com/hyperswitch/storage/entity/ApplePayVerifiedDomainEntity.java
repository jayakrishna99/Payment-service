package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * Apple Pay verified domain entity
 */
@Table("apple_pay_verified_domains")
public class ApplePayVerifiedDomainEntity {
    @Id
    @Column("id")
    private Long id;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("merchant_connector_account_id")
    private String merchantConnectorAccountId;
    
    @Column("domain_name")
    private String domainName;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("updated_at")
    private Instant updatedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getMerchantConnectorAccountId() {
        return merchantConnectorAccountId;
    }

    public void setMerchantConnectorAccountId(String merchantConnectorAccountId) {
        this.merchantConnectorAccountId = merchantConnectorAccountId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}

