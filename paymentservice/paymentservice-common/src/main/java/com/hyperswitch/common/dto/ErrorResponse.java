package com.hyperswitch.common.dto;

/**
 * Standard error response format for API documentation
 */
public class ErrorResponse {
    
    private ErrorDetails error;
    
    public ErrorResponse() {
    }
    
    public ErrorResponse(String code, String message) {
        this.error = new ErrorDetails(code, message);
    }
    
    public ErrorDetails getError() {
        return error;
    }
    
    public void setError(ErrorDetails error) {
        this.error = error;
    }
    
    /**
     * Error details object
     */
    public static class ErrorDetails {
        /**
         * Error code (e.g., INSUFFICIENT_FUNDS, CARD_DECLINED)
         */
        private String code;
        
        /**
         * Human-readable error message
         */
        private String message;
        
        /**
         * Connector-specific error message (if applicable)
         */
        private String connectorError;
        
        public ErrorDetails() {
        }
        
        public ErrorDetails(String code, String message) {
            this.code = code;
            this.message = message;
        }
        
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        public String getConnectorError() {
            return connectorError;
        }
        
        public void setConnectorError(String connectorError) {
            this.connectorError = connectorError;
        }
    }
}

