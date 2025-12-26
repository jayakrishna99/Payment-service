package com.hyperswitch.web.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * Authentication manager for API key validation
 * In production, this would validate API keys against a database
 */
public class ApiKeyAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        // In production, validate API key against database/cache
        // For now, accept any authentication with API key
        if (authentication != null && authentication.isAuthenticated()) {
            return Mono.just(authentication);
        }
        return Mono.empty();
    }
}

