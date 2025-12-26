package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * File metadata entity matching Hyperswitch schema
 */
@Table("file_metadata")
public class FileMetadataEntity {
    @Id
    @Column("file_id")
    private String fileId;
    
    @Column("merchant_id")
    private String merchantId;
    
    @Column("file_name")
    private String fileName;
    
    @Column("file_size")
    private Integer fileSize;
    
    @Column("file_type")
    private String fileType;
    
    @Column("provider_file_id")
    private String providerFileId;
    
    @Column("file_upload_provider")
    private String fileUploadProvider;
    
    @Column("available")
    private Boolean available;
    
    @Column("created_at")
    private Instant createdAt;
    
    @Column("connector_label")
    private String connectorLabel;
    
    @Column("profile_id")
    private String profileId;
    
    @Column("merchant_connector_id")
    private String merchantConnectorId;

    // Getters and Setters
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getProviderFileId() {
        return providerFileId;
    }

    public void setProviderFileId(String providerFileId) {
        this.providerFileId = providerFileId;
    }

    public String getFileUploadProvider() {
        return fileUploadProvider;
    }

    public void setFileUploadProvider(String fileUploadProvider) {
        this.fileUploadProvider = fileUploadProvider;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getConnectorLabel() {
        return connectorLabel;
    }

    public void setConnectorLabel(String connectorLabel) {
        this.connectorLabel = connectorLabel;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getMerchantConnectorId() {
        return merchantConnectorId;
    }

    public void setMerchantConnectorId(String merchantConnectorId) {
        this.merchantConnectorId = merchantConnectorId;
    }
}

