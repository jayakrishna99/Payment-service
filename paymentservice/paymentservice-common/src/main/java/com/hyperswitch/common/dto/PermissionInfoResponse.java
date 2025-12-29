package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for permission info
 */
public class PermissionInfoResponse {
    
    @JsonProperty("groups")
    private List<String> groups;
    
    @JsonProperty("resources")
    private List<String> resources;
    
    @JsonProperty("permissions")
    private Map<String, Object> permissions;
    
    // Getters and Setters
    public List<String> getGroups() {
        return groups;
    }
    
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
    
    public List<String> getResources() {
        return resources;
    }
    
    public void setResources(List<String> resources) {
        this.resources = resources;
    }
    
    public Map<String, Object> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(Map<String, Object> permissions) {
        this.permissions = permissions;
    }
}

