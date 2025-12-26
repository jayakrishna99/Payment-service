package com.hyperswitch.core.oidc.impl;

import com.hyperswitch.common.dto.JwksResponse;
import com.hyperswitch.common.dto.OidcDiscoveryResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.oidc.OidcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of OidcService
 */
@Service
public class OidcServiceImpl implements OidcService {
    
    private static final Logger log = LoggerFactory.getLogger(OidcServiceImpl.class);
    
    @Value("${hyperswitch.oidc.issuer:https://hyperswitch.io}")
    private String issuer;
    
    @Value("${server.port:8080}")
    private Integer serverPort;
    
    @Value("${server.host:localhost}")
    private String serverHost;
    
    @Override
    public Mono<Result<OidcDiscoveryResponse, PaymentError>> getDiscovery() {
        log.info("Getting OIDC discovery configuration");
        
        return Mono.fromCallable(() -> {
            String baseUrl = "https://" + serverHost + ":" + serverPort;
            
            OidcDiscoveryResponse response = new OidcDiscoveryResponse();
            response.setIssuer(issuer);
            response.setAuthorizationEndpoint(baseUrl + "/oauth2/authorize");
            response.setTokenEndpoint(baseUrl + "/oauth2/token");
            response.setUserinfoEndpoint(baseUrl + "/oauth2/userinfo");
            response.setJwksUri(baseUrl + "/oauth2/jwks");
            response.setResponseTypesSupported(Arrays.asList("code", "token", "id_token"));
            response.setSubjectTypesSupported(Arrays.asList("public"));
            response.setIdTokenSigningAlgValuesSupported(Arrays.asList("RS256", "HS256"));
            
            return Result.<OidcDiscoveryResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting OIDC discovery: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("OIDC_DISCOVERY_FAILED",
                "Failed to get OIDC discovery: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<JwksResponse, PaymentError>> getJwks() {
        log.info("Getting JWKS");
        
        return Mono.fromCallable(() -> {
            JwksResponse response = new JwksResponse();
            
            // In production, this would:
            // 1. Generate or retrieve RSA keys
            // 2. Convert keys to JWK format
            // 3. Return public keys for token verification
            
            JwksResponse.JwkKey key = new JwksResponse.JwkKey();
            key.setKty("RSA");
            key.setKid("default-key-id");
            key.setUse("sig");
            key.setAlg("RS256");
            // In production, these would be actual key values
            key.setN("placeholder-n");
            key.setE("AQAB");
            
            response.setKeys(Collections.singletonList(key));
            
            return Result.<JwksResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting JWKS: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("JWKS_RETRIEVAL_FAILED",
                "Failed to get JWKS: " + error.getMessage())));
        });
    }
}

