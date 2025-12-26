package com.hyperswitch.core.configs.impl;

import com.hyperswitch.common.dto.ConfigRequest;
import com.hyperswitch.common.dto.ConfigResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.configs.ConfigService;
import com.hyperswitch.storage.entity.ConfigEntity;
import com.hyperswitch.storage.repository.ConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementation of ConfigService
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    
    private static final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);
    
    private final ConfigRepository configRepository;
    
    @Autowired
    public ConfigServiceImpl(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }
    
    @Override
    public Mono<Result<ConfigResponse, PaymentError>> createConfig(ConfigRequest request) {
        log.info("Creating config with key: {}", request.getKey());
        
        return configRepository.findByKey(request.getKey())
            .flatMap(existing -> {
                log.warn("Config with key {} already exists", request.getKey());
                return Mono.just(Result.<ConfigResponse, PaymentError>err(
                    PaymentError.of("CONFIG_ALREADY_EXISTS",
                        "Config with key " + request.getKey() + " already exists")
                ));
            })
            .switchIfEmpty(Mono.fromCallable(() -> {
                ConfigEntity entity = new ConfigEntity();
                entity.setKey(request.getKey());
                entity.setConfig(request.getValue());
                return entity;
            })
            .flatMap(configRepository::save)
            .map(this::toConfigResponse)
            .map(Result::<ConfigResponse, PaymentError>ok))
            .onErrorResume(error -> {
                log.error("Error creating config", error);
                return Mono.just(Result.<ConfigResponse, PaymentError>err(
                    PaymentError.of("CONFIG_CREATE_FAILED",
                        "Failed to create config: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<ConfigResponse, PaymentError>> getConfig(String key) {
        log.info("Getting config with key: {}", key);
        
        return configRepository.findByKey(key)
            .map(this::toConfigResponse)
            .map(Result::<ConfigResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<ConfigResponse, PaymentError>err(
                PaymentError.of("CONFIG_NOT_FOUND",
                    "Config with key " + key + " not found")
            )))
            .onErrorResume(error -> {
                log.error("Error getting config", error);
                return Mono.just(Result.<ConfigResponse, PaymentError>err(
                    PaymentError.of("CONFIG_RETRIEVAL_FAILED",
                        "Failed to get config: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<ConfigResponse, PaymentError>> updateConfig(String key, ConfigRequest request) {
        log.info("Updating config with key: {}", key);
        
        return configRepository.findByKey(key)
            .switchIfEmpty(Mono.error(new RuntimeException("Config not found")))
            .flatMap(entity -> {
                if (request.getValue() != null) {
                    entity.setConfig(request.getValue());
                }
                return configRepository.save(entity)
                    .map(this::toConfigResponse)
                    .map(Result::<ConfigResponse, PaymentError>ok);
            })
            .onErrorResume(error -> {
                log.error("Error updating config", error);
                if (error.getMessage() != null && error.getMessage().contains("not found")) {
                    return Mono.just(Result.<ConfigResponse, PaymentError>err(
                        PaymentError.of("CONFIG_NOT_FOUND",
                            "Config with key " + key + " not found")
                    ));
                }
                return Mono.just(Result.<ConfigResponse, PaymentError>err(
                    PaymentError.of("CONFIG_UPDATE_FAILED",
                        "Failed to update config: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<ConfigResponse, PaymentError>> deleteConfig(String key) {
        log.info("Deleting config with key: {}", key);
        
        return configRepository.findByKey(key)
            .switchIfEmpty(Mono.error(new RuntimeException("Config not found")))
            .flatMap(entity -> {
                ConfigResponse response = toConfigResponse(entity);
                return configRepository.delete(entity)
                    .thenReturn(Result.<ConfigResponse, PaymentError>ok(response));
            })
            .onErrorResume(error -> {
                log.error("Error deleting config", error);
                if (error.getMessage() != null && error.getMessage().contains("not found")) {
                    return Mono.just(Result.<ConfigResponse, PaymentError>err(
                        PaymentError.of("CONFIG_NOT_FOUND",
                            "Config with key " + key + " not found")
                    ));
                }
                return Mono.just(Result.<ConfigResponse, PaymentError>err(
                    PaymentError.of("CONFIG_DELETE_FAILED",
                        "Failed to delete config: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Convert ConfigEntity to ConfigResponse
     */
    private ConfigResponse toConfigResponse(ConfigEntity entity) {
        ConfigResponse response = new ConfigResponse();
        response.setKey(entity.getKey());
        response.setValue(entity.getConfig());
        return response;
    }
}

