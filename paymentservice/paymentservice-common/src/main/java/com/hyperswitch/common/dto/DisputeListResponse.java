package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for dispute list
 */
public class DisputeListResponse {
    private List<DisputeResponse> data;
    private Long totalCount;
    private Integer limit;
    private Integer offset;
    
    public DisputeListResponse() {
    }
    
    public DisputeListResponse(List<DisputeResponse> data, Long totalCount, Integer limit, Integer offset) {
        this.data = data;
        this.totalCount = totalCount;
        this.limit = limit;
        this.offset = offset;
    }
    
    public List<DisputeResponse> getData() {
        return data;
    }
    
    public void setData(List<DisputeResponse> data) {
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

