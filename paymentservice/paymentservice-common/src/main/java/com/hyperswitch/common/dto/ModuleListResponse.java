package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for module list
 */
public class ModuleListResponse {
    
    @JsonProperty("modules")
    private List<Map<String, Object>> modules;
    
    // Getters and Setters
    public List<Map<String, Object>> getModules() {
        return modules;
    }
    
    public void setModules(List<Map<String, Object>> modules) {
        this.modules = modules;
    }
}

