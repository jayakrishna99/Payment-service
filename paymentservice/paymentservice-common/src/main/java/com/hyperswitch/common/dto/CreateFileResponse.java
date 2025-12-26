package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response DTO for creating a file
 */
@Schema(description = "Response for creating a file")
public class CreateFileResponse {
    
    @Schema(description = "File ID", example = "file_123456")
    @JsonProperty("file_id")
    private String fileId;
    
    @Schema(description = "File name", example = "evidence.pdf")
    @JsonProperty("file_name")
    private String fileName;
    
    @Schema(description = "File size in bytes", example = "1024")
    @JsonProperty("file_size")
    private Integer fileSize;
    
    @Schema(description = "File type", example = "application/pdf")
    @JsonProperty("file_type")
    private String fileType;
    
    @Schema(description = "File URL", example = "https://example.com/files/file_123456")
    @JsonProperty("file_url")
    private String fileUrl;
    
    @Schema(description = "Created at timestamp")
    @JsonProperty("created_at")
    private Instant createdAt;

    // Getters and Setters
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

