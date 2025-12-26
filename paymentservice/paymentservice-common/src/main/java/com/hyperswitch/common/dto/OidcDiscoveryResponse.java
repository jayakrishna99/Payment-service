package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response DTO for OIDC discovery
 */
public class OidcDiscoveryResponse {
    
    @JsonProperty("issuer")
    private String issuer;
    
    @JsonProperty("authorization_endpoint")
    private String authorizationEndpoint;
    
    @JsonProperty("token_endpoint")
    private String tokenEndpoint;
    
    @JsonProperty("userinfo_endpoint")
    private String userinfoEndpoint;
    
    @JsonProperty("jwks_uri")
    private String jwksUri;
    
    @JsonProperty("response_types_supported")
    private List<String> responseTypesSupported;
    
    @JsonProperty("subject_types_supported")
    private List<String> subjectTypesSupported;
    
    @JsonProperty("id_token_signing_alg_values_supported")
    private List<String> idTokenSigningAlgValuesSupported;
    
    // Getters and Setters
    public String getIssuer() {
        return issuer;
    }
    
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    
    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }
    
    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }
    
    public String getTokenEndpoint() {
        return tokenEndpoint;
    }
    
    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }
    
    public String getUserinfoEndpoint() {
        return userinfoEndpoint;
    }
    
    public void setUserinfoEndpoint(String userinfoEndpoint) {
        this.userinfoEndpoint = userinfoEndpoint;
    }
    
    public String getJwksUri() {
        return jwksUri;
    }
    
    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }
    
    public List<String> getResponseTypesSupported() {
        return responseTypesSupported;
    }
    
    public void setResponseTypesSupported(List<String> responseTypesSupported) {
        this.responseTypesSupported = responseTypesSupported;
    }
    
    public List<String> getSubjectTypesSupported() {
        return subjectTypesSupported;
    }
    
    public void setSubjectTypesSupported(List<String> subjectTypesSupported) {
        this.subjectTypesSupported = subjectTypesSupported;
    }
    
    public List<String> getIdTokenSigningAlgValuesSupported() {
        return idTokenSigningAlgValuesSupported;
    }
    
    public void setIdTokenSigningAlgValuesSupported(List<String> idTokenSigningAlgValuesSupported) {
        this.idTokenSigningAlgValuesSupported = idTokenSigningAlgValuesSupported;
    }
}

