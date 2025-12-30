package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * User entity for user management
 */
@Table("users")
public class UserEntity {
    
    @Id
    @Column("user_id")
    private String userId;
    
    @Column("email")
    private String email;
    
    @Column("name")
    private String name;
    
    @Column("password")
    private String password;
    
    @Column("is_verified")
    private Boolean isVerified;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("last_modified_at")
    private Instant lastModifiedAt;
    
    @Column("totp_status")
    private String totpStatus;
    
    @Column("totp_secret")
    private byte[] totpSecret;
    
    @Column("totp_recovery_codes")
    private List<String> totpRecoveryCodes;
    
    @Column("last_password_modified_at")
    private Instant lastPasswordModifiedAt;
    
    @Column("lineage_context")
    private Map<String, Object> lineageContext;
    
    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getIsVerified() {
        return isVerified;
    }
    
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    
    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }
    
    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
    
    public String getTotpStatus() {
        return totpStatus;
    }
    
    public void setTotpStatus(String totpStatus) {
        this.totpStatus = totpStatus;
    }
    
    public byte[] getTotpSecret() {
        return totpSecret;
    }
    
    public void setTotpSecret(byte[] totpSecret) {
        this.totpSecret = totpSecret;
    }
    
    public List<String> getTotpRecoveryCodes() {
        return totpRecoveryCodes;
    }
    
    public void setTotpRecoveryCodes(List<String> totpRecoveryCodes) {
        this.totpRecoveryCodes = totpRecoveryCodes;
    }
    
    public Instant getLastPasswordModifiedAt() {
        return lastPasswordModifiedAt;
    }
    
    public void setLastPasswordModifiedAt(Instant lastPasswordModifiedAt) {
        this.lastPasswordModifiedAt = lastPasswordModifiedAt;
    }
    
    public Map<String, Object> getLineageContext() {
        return lineageContext;
    }
    
    public void setLineageContext(Map<String, Object> lineageContext) {
        this.lineageContext = lineageContext;
    }
}

