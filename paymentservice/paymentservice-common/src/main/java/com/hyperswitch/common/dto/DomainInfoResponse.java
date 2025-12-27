package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for domain info
 */
public class DomainInfoResponse {
    
    @JsonProperty("metrics")
    private List<MetricInfo> metrics;
    
    @JsonProperty("dimensions")
    private List<DimensionInfo> dimensions;
    
    @JsonProperty("download_dimensions")
    private List<DimensionInfo> downloadDimensions;
    
    // Getters and Setters
    public List<MetricInfo> getMetrics() {
        return metrics;
    }
    
    public void setMetrics(List<MetricInfo> metrics) {
        this.metrics = metrics;
    }
    
    public List<DimensionInfo> getDimensions() {
        return dimensions;
    }
    
    public void setDimensions(List<DimensionInfo> dimensions) {
        this.dimensions = dimensions;
    }
    
    public List<DimensionInfo> getDownloadDimensions() {
        return downloadDimensions;
    }
    
    public void setDownloadDimensions(List<DimensionInfo> downloadDimensions) {
        this.downloadDimensions = downloadDimensions;
    }
    
    /**
     * Inner class for metric information
     */
    public static class MetricInfo {
        @JsonProperty("name")
        private String name;
        
        @JsonProperty("display_name")
        private String displayName;
        
        @JsonProperty("description")
        private String description;
        
        @JsonProperty("type")
        private String type;
        
        // Getters and Setters
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
    }
    
    /**
     * Inner class for dimension information
     */
    public static class DimensionInfo {
        @JsonProperty("name")
        private String name;
        
        @JsonProperty("display_name")
        private String displayName;
        
        @JsonProperty("description")
        private String description;
        
        @JsonProperty("type")
        private String type;
        
        // Getters and Setters
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
    }
}

