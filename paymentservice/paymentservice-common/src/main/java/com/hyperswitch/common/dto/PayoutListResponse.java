package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for payout list
 */
public class PayoutListResponse {
    private List<PayoutResponse> data;
    private Long totalCount;
    private Integer limit;
    private Integer offset;
    
    public PayoutListResponse() {
    }
    
    public PayoutListResponse(List<PayoutResponse> data, Long totalCount, Integer limit, Integer offset) {
        this.data = data;
        this.totalCount = totalCount;
        this.limit = limit;
        this.offset = offset;
    }
    
    public List<PayoutResponse> getData() {
        return data;
    }
    
    public void setData(List<PayoutResponse> data) {
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

