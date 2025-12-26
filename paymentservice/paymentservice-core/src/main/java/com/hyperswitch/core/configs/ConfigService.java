package com.hyperswitch.core.configs;

import com.hyperswitch.common.dto.ConfigRequest;
import com.hyperswitch.common.dto.ConfigResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for config operations
 */
public interface ConfigService {
    
    /**
     * Create config key
     */
    Mono<Result<ConfigResponse, PaymentError>> createConfig(ConfigRequest request);
    
    /**
     * Retrieve config key
     */
    Mono<Result<ConfigResponse, PaymentError>> getConfig(String key);
    
    /**
     * Update config key
     */
    Mono<Result<ConfigResponse, PaymentError>> updateConfig(String key, ConfigRequest request);
    
    /**
     * Delete config key
     */
    Mono<Result<ConfigResponse, PaymentError>> deleteConfig(String key);
}

