package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for user role details
 */
public class UserRoleDetailsResponse {
    
    @JsonProperty("role_id")
    private String roleId;
    
    @JsonProperty("role_name")
    private String roleName;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("entity_type")
    private String entityType;
    
    @JsonProperty("org_id")
    private String orgId;
    
    @JsonProperty("org_name")
    private String orgName;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("merchant_name")
    private String merchantName;
    
    @JsonProperty("profile_id")
    private String profileId;
    
    @JsonProperty("profile_name")
    private String profileName;
    
    // Getters and Setters
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    public String getOrgId() {
        return orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getMerchantName() {
        return merchantName;
    }
    
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
    
    public String getProfileName() {
        return profileName;
    }
    
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}

