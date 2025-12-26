package com.hyperswitch.common.dto;

import java.util.List;

/**
 * Response for batch card tokenization
 */
public class BatchTokenizeCardResponse {
    private List<TokenizeCardResult> results;
    private Integer totalCount;
    private Integer successCount;
    private Integer failureCount;
    
    public BatchTokenizeCardResponse() {
    }
    
    public List<TokenizeCardResult> getResults() {
        return results;
    }
    
    public void setResults(List<TokenizeCardResult> results) {
        this.results = results;
    }
    
    public Integer getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public Integer getSuccessCount() {
        return successCount;
    }
    
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }
    
    public Integer getFailureCount() {
        return failureCount;
    }
    
    public void setFailureCount(Integer failureCount) {
        this.failureCount = failureCount;
    }
    
    public static class TokenizeCardResult {
        private Integer index;
        private Boolean success;
        private String errorMessage;
        private TokenizeCardResponse tokenizeCardResponse;
        
        public TokenizeCardResult() {
        }
        
        public Integer getIndex() {
            return index;
        }
        
        public void setIndex(Integer index) {
            this.index = index;
        }
        
        public Boolean getSuccess() {
            return success;
        }
        
        public void setSuccess(Boolean success) {
            this.success = success;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
        
        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
        
        public TokenizeCardResponse getTokenizeCardResponse() {
            return tokenizeCardResponse;
        }
        
        public void setTokenizeCardResponse(TokenizeCardResponse tokenizeCardResponse) {
            this.tokenizeCardResponse = tokenizeCardResponse;
        }
    }
}

