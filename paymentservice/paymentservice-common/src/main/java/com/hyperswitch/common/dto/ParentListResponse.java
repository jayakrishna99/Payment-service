package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for parent list
 */
public class ParentListResponse {
    
    @JsonProperty("parents")
    private List<Map<String, Object>> parents;
    
    // Getters and Setters
    public List<Map<String, Object>> getParents() {
        return parents;
    }
    
    public void setParents(List<Map<String, Object>> parents) {
        this.parents = parents;
    }
}

