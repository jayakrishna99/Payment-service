package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for search operations
 */
public class SearchResponse {
    
    @JsonProperty("results")
    private List<Map<String, Object>> results;
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    @JsonProperty("limit")
    private Integer limit;
    
    @JsonProperty("offset")
    private Integer offset;
    
    @JsonProperty("domain")
    private String domain;
    
    // Getters and Setters
    public List<Map<String, Object>> getResults() {
        return results;
    }
    
    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    public String getDomain() {
        return domain;
    }
    
    public void setDomain(String domain) {
        this.domain = domain;
    }
}

