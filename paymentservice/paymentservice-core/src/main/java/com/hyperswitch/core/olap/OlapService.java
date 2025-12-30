package com.hyperswitch.core.olap;

import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Service interface for OLAP (Online Analytical Processing) operations
 * Provides ClickHouse integration for large-scale analytics
 */
public interface OlapService {
    
    /**
     * Execute a ClickHouse query
     */
    Mono<Result<java.util.List<Map<String, Object>>, PaymentError>> executeQuery(String query);
    
    /**
     * Health check for ClickHouse connection
     */
    Mono<Result<Boolean, PaymentError>> healthCheck();
    
    /**
     * Get ClickHouse database name
     */
    String getDatabaseName();
    
    /**
     * Check if OLAP is enabled
     */
    boolean isEnabled();
}

