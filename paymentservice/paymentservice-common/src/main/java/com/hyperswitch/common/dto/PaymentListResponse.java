package com.hyperswitch.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Response for payment listing
 */
public class PaymentListResponse {
    private List<Map<String, Object>> data;
    private Integer totalCount;
    private Integer limit;
    private Integer offset;
    
    /**
     * Default constructor required for JSON deserialization frameworks (e.g., Jackson)
     */
    public PaymentListResponse() {
        // Empty constructor required for JSON deserialization
    }
    
    public List<Map<String, Object>> getData() {
        return data;
    }
    
    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
    
    public Integer getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Integer totalCount) {
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
}

