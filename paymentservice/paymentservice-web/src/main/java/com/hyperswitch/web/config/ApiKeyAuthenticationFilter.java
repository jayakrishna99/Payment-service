package com.hyperswitch.web.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * API Key authentication filter for securing endpoints
 */
@Component
public class ApiKeyAuthenticationFilter implements ServerAuthenticationConverter {

    private static final String API_KEY_HEADER = "X-API-Key";
    private static final String API_KEY_PREFIX = "Bearer ";
    private static final List<SimpleGrantedAuthority> AUTHORITIES = 
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_API_USER"));

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        
        // Check for API key in header
        String apiKey = request.getHeaders().getFirst(API_KEY_HEADER);
        if (apiKey == null || apiKey.isEmpty()) {
            // Try Authorization header with Bearer token
            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith(API_KEY_PREFIX)) {
                apiKey = authHeader.substring(API_KEY_PREFIX.length());
            }
        }
        
        if (apiKey != null && !apiKey.isEmpty()) {
            // In production, validate API key against database
            // For now, accept any non-empty API key
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                apiKey, 
                null, 
                AUTHORITIES
            );
            return Mono.just(authentication);
        }
        
        return Mono.empty();
    }
}

