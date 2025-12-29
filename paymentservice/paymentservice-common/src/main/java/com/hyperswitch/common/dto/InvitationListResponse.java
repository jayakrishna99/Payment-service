package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response DTO for invitation list
 */
public class InvitationListResponse {
    
    @JsonProperty("invitations")
    private List<InvitationResponse> invitations;
    
    // Getters and Setters
    public List<InvitationResponse> getInvitations() {
        return invitations;
    }
    
    public void setInvitations(List<InvitationResponse> invitations) {
        this.invitations = invitations;
    }
    
    /**
     * Inner class for invitation details
     */
    public static class InvitationResponse {
        @JsonProperty("email")
        private String email;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("role_id")
        private String roleId;
        
        @JsonProperty("created_at")
        private java.time.Instant createdAt;
        
        // Getters and Setters
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getRoleId() {
            return roleId;
        }
        
        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }
        
        public java.time.Instant getCreatedAt() {
            return createdAt;
        }
        
        public void setCreatedAt(java.time.Instant createdAt) {
            this.createdAt = createdAt;
        }
    }
}

