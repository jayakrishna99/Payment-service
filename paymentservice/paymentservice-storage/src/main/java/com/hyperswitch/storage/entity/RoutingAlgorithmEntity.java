package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

/**
 * Routing algorithm entity
 */
@Table("routing_algorithm")
public class RoutingAlgorithmEntity {
    @Id
    @Column("algorithm_id")
    private String algorithmId;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("name")
    private String name;
    
    @Column("description")
    private String description;
    
    @Column("kind")
    private String kind; // 'single', 'priority', 'volume_split', 'advanced'
    
    @Column("algorithm_data")
    private Map<String, Object> algorithmData; // Stored as JSONB
    
    @Column("algorithm_for")
    private String algorithmFor; // 'payment' or 'payout'
    
    @Column("decision_engine_routing_id")
    private String decisionEngineRoutingId;
    
    @Column("is_active")
    private Boolean isActive;
    
    @Column("is_default")
    private Boolean isDefault;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("modified_at")
    private Instant modifiedAt;

    // Getters and Setters
    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Map<String, Object> getAlgorithmData() {
        return algorithmData;
    }

    public void setAlgorithmData(Map<String, Object> algorithmData) {
        this.algorithmData = algorithmData;
    }

    public String getAlgorithmFor() {
        return algorithmFor;
    }

    public void setAlgorithmFor(String algorithmFor) {
        this.algorithmFor = algorithmFor;
    }

    public String getDecisionEngineRoutingId() {
        return decisionEngineRoutingId;
    }

    public void setDecisionEngineRoutingId(String decisionEngineRoutingId) {
        this.decisionEngineRoutingId = decisionEngineRoutingId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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
}

