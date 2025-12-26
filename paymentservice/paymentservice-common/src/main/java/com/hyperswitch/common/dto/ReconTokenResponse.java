package com.hyperswitch.common.dto;

/**
 * Response DTO for reconciliation token
 */
public class ReconTokenResponse {
    private String token;

    public ReconTokenResponse() {
    }

    public ReconTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

