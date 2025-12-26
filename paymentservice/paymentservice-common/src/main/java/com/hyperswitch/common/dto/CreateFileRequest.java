package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

/**
 * Request DTO for creating a file
 */
@Schema(description = "Request for creating a file")
public class CreateFileRequest {
    
    @Schema(description = "File name", example = "evidence.pdf")
    @JsonProperty("file_name")
    private String fileName;
    
    @Schema(description = "File type", example = "application/pdf")
    @JsonProperty("file_type")
    private String fileType;
    
    @Schema(description = "File size in bytes", example = "1024")
    @JsonProperty("file_size")
    private Integer fileSize;
    
    @Schema(description = "File content (multipart)")
    private MultipartFile file;

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

