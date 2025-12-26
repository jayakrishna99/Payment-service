package com.hyperswitch.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

/**
 * Security configuration
 * Implements API key-based authentication for payment service endpoints
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${hyperswitch.security.enable-csrf:false}")
    private boolean enableCsrf;

    @Value("${hyperswitch.security.enable-auth:true}")
    private boolean enableAuth;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http,
            ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) {
        
        ServerHttpSecurity httpSecurity = http;
        
        // Configure CSRF based on environment
        if (!enableCsrf) {
            httpSecurity = httpSecurity.csrf(csrf -> csrf.disable());
        }
        
        // Configure authentication
        if (enableAuth) {
            AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(
                new ApiKeyAuthenticationManager()
            );
            authenticationWebFilter.setServerAuthenticationConverter(apiKeyAuthenticationFilter);
            
            httpSecurity = httpSecurity
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(exchanges -> exchanges
                    .pathMatchers("/health", "/actuator/**", "/api/webhooks/**").permitAll()
                    .anyExchange().authenticated()
                );
        } else {
            httpSecurity = httpSecurity
                .authorizeExchange(exchanges -> exchanges
                    .anyExchange().permitAll()
                );
        }
        
        return httpSecurity.build();
    }
}


