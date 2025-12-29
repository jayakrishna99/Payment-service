package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for updating user role
 */
public class UpdateUserRoleRequest {
    
    @JsonProperty("role_id")
    private String roleId;
    
    // Getters and Setters
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

