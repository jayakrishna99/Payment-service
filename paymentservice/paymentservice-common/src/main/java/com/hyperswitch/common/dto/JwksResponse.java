package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response DTO for JWKS (JSON Web Key Set)
 */
public class JwksResponse {
    
    @JsonProperty("keys")
    private List<JwkKey> keys;
    
    // Getters and Setters
    public List<JwkKey> getKeys() {
        return keys;
    }
    
    public void setKeys(List<JwkKey> keys) {
        this.keys = keys;
    }
    
    /**
     * JWK Key structure
     */
    public static class JwkKey {
        @JsonProperty("kty")
        private String kty;
        
        @JsonProperty("kid")
        private String kid;
        
        @JsonProperty("use")
        private String use;
        
        @JsonProperty("n")
        private String n;
        
        @JsonProperty("e")
        private String e;
        
        @JsonProperty("alg")
        private String alg;
        
        // Getters and Setters
        public String getKty() {
            return kty;
        }
        
        public void setKty(String kty) {
            this.kty = kty;
        }
        
        public String getKid() {
            return kid;
        }
        
        public void setKid(String kid) {
            this.kid = kid;
        }
        
        public String getUse() {
            return use;
        }
        
        public void setUse(String use) {
            this.use = use;
        }
        
        public String getN() {
            return n;
        }
        
        public void setN(String n) {
            this.n = n;
        }
        
        public String getE() {
            return e;
        }
        
        public void setE(String e) {
            this.e = e;
        }
        
        public String getAlg() {
            return alg;
        }
        
        public void setAlg(String alg) {
            this.alg = alg;
        }
    }
}

