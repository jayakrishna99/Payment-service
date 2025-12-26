package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for blocklist operations
 */
@Schema(description = "Request for blocklist operations")
public class BlocklistRequest {
    
    @Schema(description = "Fingerprint ID", example = "fp_123456", required = true)
    @JsonProperty("fingerprint_id")
    private String fingerprintId;
    
    @Schema(description = "Data kind", example = "payment_method", required = true)
    @JsonProperty("data_kind")
    private String dataKind;
    
    @Schema(description = "Additional metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
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
}

