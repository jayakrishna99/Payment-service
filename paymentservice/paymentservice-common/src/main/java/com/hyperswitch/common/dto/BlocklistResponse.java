package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for blocklist operations
 */
@Schema(description = "Response for blocklist operations")
public class BlocklistResponse {
    
    @Schema(description = "List of blocklist entries")
    @JsonProperty("entries")
    private List<BlocklistEntry> entries;
    
    @Schema(description = "Total count")
    @JsonProperty("total")
    private Integer total;
    
    /**
     * Blocklist entry
     */
    public static class BlocklistEntry {
        @Schema(description = "Entry ID")
        @JsonProperty("id")
        private Long id;
        
        @Schema(description = "Fingerprint ID", example = "fp_123456")
        @JsonProperty("fingerprint_id")
        private String fingerprintId;
        
        @Schema(description = "Data kind", example = "payment_method")
        @JsonProperty("data_kind")
        private String dataKind;
        
        @Schema(description = "Additional metadata")
        @JsonProperty("metadata")
        private Map<String, Object> metadata;
        
        @Schema(description = "Created at timestamp")
        @JsonProperty("created_at")
        private Instant createdAt;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFingerprintId() {
            return fingerprintId;
        }

        public void setFingerprintId(String fingerprintId) {
            this.fingerprintId = fingerprintId;
        }

        public String getDataKind() {
            return dataKind;
        }

        public void setDataKind(String dataKind) {
            this.dataKind = dataKind;
        }

        public Map<String, Object> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }
    }

    // Getters and Setters
    public List<BlocklistEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlocklistEntry> entries) {
        this.entries = entries;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

