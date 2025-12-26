package com.hyperswitch.web.config;

import org.springframework.lang.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * API versioning configuration
 * Supports both v1 and v2 API versions
 */
@Configuration
public class ApiVersioningConfig implements WebFluxConfigurer {

    @Override
    public void configurePathMatching(@NonNull PathMatchConfigurer configurer) {
        // API versioning is handled via path prefixes:
        // - /api/payments -> v1 (default)
        // - /api/v2/payment-sessions -> v2
        // This allows backward compatibility while supporting new API versions
    }
}

