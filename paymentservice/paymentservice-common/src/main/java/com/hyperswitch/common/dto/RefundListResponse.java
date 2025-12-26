package com.hyperswitch.common.dto;

import java.util.List;
import java.util.Map;

/**
 * Response for refund list
 */
public class RefundListResponse {
    private List<Map<String, Object>> data;
    private Long totalCount;
    private Integer limit;
    private Integer offset;
    
    public RefundListResponse() {
    }
    
    public List<Map<String, Object>> getData() {
        return data;
    }
    
    public void setData(List<Map<String, Object>> data) {
        this.data = data;
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
}

