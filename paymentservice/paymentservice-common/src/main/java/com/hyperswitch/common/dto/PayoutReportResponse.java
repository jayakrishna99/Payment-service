package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Response DTO for payout reports
 */
public class PayoutReportResponse {
    
    @JsonProperty("report_id")
    private String reportId;
    
    @JsonProperty("status")
    private String status; // GENERATING, COMPLETED, FAILED
    
    @JsonProperty("download_url")
    private String downloadUrl;
    
    @JsonProperty("format")
    private String format;
    
    @JsonProperty("generated_at")
    private String generatedAt;
    
    @JsonProperty("expires_at")
    private String expiresAt;
    
    @JsonProperty("row_count")
    private Long rowCount;
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    // Getters and Setters
    public String getReportId() {
        return reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }
    
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public String getGeneratedAt() {
        return generatedAt;
    }
    
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
    
    public String getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public Long getRowCount() {
        return rowCount;
    }
    
    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

