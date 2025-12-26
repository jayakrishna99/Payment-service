package com.hyperswitch.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Config entity matching Hyperswitch schema
 */
@Table("configs")
public class ConfigEntity {
    @Id
    @Column("key")
    private String key;
    
    @Column("config")
    private String config;

    // Getters and Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}

