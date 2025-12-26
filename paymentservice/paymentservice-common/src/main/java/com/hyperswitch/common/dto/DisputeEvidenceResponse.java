package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * Response DTO for dispute evidence
 */
@Schema(description = "Response for dispute evidence")
public class DisputeEvidenceResponse {
    
    @Schema(description = "Dispute ID", example = "dispute_123")
    @JsonProperty("dispute_id")
    private String disputeId;
    
    @Schema(description = "List of evidence blocks")
    @JsonProperty("evidence_blocks")
    private List<DisputeEvidenceBlock> evidenceBlocks;
    
    @Schema(description = "Evidence metadata")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getDisputeId() {
        return disputeId;
    }

    public void setDisputeId(String disputeId) {
        this.disputeId = disputeId;
    }

    public List<DisputeEvidenceBlock> getEvidenceBlocks() {
        return evidenceBlocks;
    }

    public void setEvidenceBlocks(List<DisputeEvidenceBlock> evidenceBlocks) {
        this.evidenceBlocks = evidenceBlocks;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
    
    /**
     * Evidence block structure
     */
    public static class DisputeEvidenceBlock {
        @Schema(description = "Evidence type", example = "receipt")
        @JsonProperty("evidence_type")
        private String evidenceType;
        
        @Schema(description = "File metadata")
        @JsonProperty("file_metadata")
        private Map<String, Object> fileMetadata;

        public String getEvidenceType() {
            return evidenceType;
        }

        public void setEvidenceType(String evidenceType) {
            this.evidenceType = evidenceType;
        }

        public Map<String, Object> getFileMetadata() {
            return fileMetadata;
        }

        public void setFileMetadata(Map<String, Object> fileMetadata) {
            this.fileMetadata = fileMetadata;
        }
    }
}

