package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for customer list with count
 */
public class CustomerListWithCountResponse {
    private List<CustomerResponse> data;
    private Long totalCount;
    private Integer limit;
    private Integer offset;
    
    public CustomerListWithCountResponse() {
    }
    
    public List<CustomerResponse> getData() {
        return data;
    }
    
    public void setData(List<CustomerResponse> data) {
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

