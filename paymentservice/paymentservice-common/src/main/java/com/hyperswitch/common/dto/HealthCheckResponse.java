package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Response DTO for health check
 */
public class HealthCheckResponse {
    
    @JsonProperty("database")
    private Boolean database;
    
    @JsonProperty("redis")
    private Boolean redis;
    
    @JsonProperty("vault")
    private Boolean vault;
    
    @JsonProperty("analytics")
    private Boolean analytics;
    
    @JsonProperty("opensearch")
    private Boolean opensearch;
    
    @JsonProperty("outgoing_request")
    private Boolean outgoingRequest;
    
    @JsonProperty("grpc_health_check")
    private Map<String, Boolean> grpcHealthCheck;
    
    @JsonProperty("decision_engine")
    private Boolean decisionEngine;
    
    @JsonProperty("unified_connector_service")
    private Boolean unifiedConnectorService;
    
    @JsonProperty("status")
    private String status;
    
    // Getters and Setters
    public Boolean getDatabase() {
        return database;
    }
    
    public void setDatabase(Boolean database) {
        this.database = database;
    }
    
    public Boolean getRedis() {
        return redis;
    }
    
    public void setRedis(Boolean redis) {
        this.redis = redis;
    }
    
    public Boolean getVault() {
        return vault;
    }
    
    public void setVault(Boolean vault) {
        this.vault = vault;
    }
    
    public Boolean getAnalytics() {
        return analytics;
    }
    
    public void setAnalytics(Boolean analytics) {
        this.analytics = analytics;
    }
    
    public Boolean getOpensearch() {
        return opensearch;
    }
    
    public void setOpensearch(Boolean opensearch) {
        this.opensearch = opensearch;
    }
    
    public Boolean getOutgoingRequest() {
        return outgoingRequest;
    }
    
    public void setOutgoingRequest(Boolean outgoingRequest) {
        this.outgoingRequest = outgoingRequest;
    }
    
    public Map<String, Boolean> getGrpcHealthCheck() {
        return grpcHealthCheck;
    }
    
    public void setGrpcHealthCheck(Map<String, Boolean> grpcHealthCheck) {
        this.grpcHealthCheck = grpcHealthCheck;
    }
    
    public Boolean getDecisionEngine() {
        return decisionEngine;
    }
    
    public void setDecisionEngine(Boolean decisionEngine) {
        this.decisionEngine = decisionEngine;
    }
    
    public Boolean getUnifiedConnectorService() {
        return unifiedConnectorService;
    }
    
    public void setUnifiedConnectorService(Boolean unifiedConnectorService) {
        this.unifiedConnectorService = unifiedConnectorService;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}

