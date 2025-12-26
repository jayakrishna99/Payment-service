package com.hyperswitch.core.health;

import com.hyperswitch.common.dto.HealthCheckResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for health check operations
 */
public interface HealthCheckService {
    
    /**
     * Perform deep health check
     */
    Mono<Result<HealthCheckResponse, PaymentError>> performDeepHealthCheck();
    
    /**
     * Perform basic health check
     */
    Mono<Result<HealthCheckResponse, PaymentError>> performHealthCheck();
}

