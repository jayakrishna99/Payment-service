package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Request DTO for creating a role
 */
public class CreateRoleRequest {
    
    @JsonProperty("role_name")
    private String roleName;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("groups")
    private List<String> groups;
    
    @JsonProperty("resources")
    private List<String> resources;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    // Getters and Setters
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
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
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

