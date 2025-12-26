package com.hyperswitch.connectors.impl;

import com.hyperswitch.connectors.ConnectorMetadataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of ConnectorMetadataService using R2DBC
 * In production, this could use Redis or a dedicated metadata store
 */
@Service
public class ConnectorMetadataServiceImpl implements ConnectorMetadataService {

    private static final Logger log = LoggerFactory.getLogger(ConnectorMetadataServiceImpl.class);
    
    // In-memory store for now (in production, use Redis or database)
    private final Map<String, Map<String, Object>> metadataStore = new HashMap<>();
    
    @Override
    public Mono<Boolean> storeMetadata(String connectorTransactionId, Map<String, Object> metadata) {
        log.debug("Storing metadata for connector transaction: {}", connectorTransactionId);
        
        return Mono.fromCallable(() -> {
            metadataStore.put(connectorTransactionId, new HashMap<>(metadata));
            return true;
        })
        .doOnSuccess(success -> {
            if (Boolean.TRUE.equals(success)) {
                log.debug("Metadata stored successfully for transaction: {}", connectorTransactionId);
            }
        })
        .onErrorReturn(false);
    }

    @Override
    public Mono<Map<String, Object>> getMetadata(String connectorTransactionId) {
        log.debug("Retrieving metadata for connector transaction: {}", connectorTransactionId);
        
        return Mono.fromCallable(() -> {
            Map<String, Object> metadata = metadataStore.get(connectorTransactionId);
            if (metadata != null) {
                return Map.copyOf(metadata);
            } else {
                return Map.<String, Object>of();
            }
        })
        .doOnNext(metadata -> {
            if (metadata.isEmpty()) {
                log.debug("No metadata found for transaction: {}", connectorTransactionId);
            }
        });
    }

    @Override
    public Mono<Boolean> updateMetadata(String connectorTransactionId, Map<String, Object> metadata) {
        log.debug("Updating metadata for connector transaction: {}", connectorTransactionId);
        
        return Mono.fromCallable(() -> {
            Map<String, Object> existing = metadataStore.get(connectorTransactionId);
            if (existing != null) {
                existing.putAll(metadata);
                return true;
            }
            return false;
        })
        .doOnSuccess(success -> {
            if (Boolean.FALSE.equals(success)) {
                log.warn("Metadata not found for update, transaction: {}", connectorTransactionId);
            }
        })
        .onErrorReturn(false);
    }

    @Override
    public Mono<Boolean> deleteMetadata(String connectorTransactionId) {
        log.debug("Deleting metadata for connector transaction: {}", connectorTransactionId);
        
        return Mono.fromCallable(() -> {
            Map<String, Object> removed = metadataStore.remove(connectorTransactionId);
            return removed != null;
        })
        .doOnSuccess(success -> {
            if (Boolean.TRUE.equals(success)) {
                log.debug("Metadata deleted successfully for transaction: {}", connectorTransactionId);
            } else {
                log.debug("No metadata found to delete for transaction: {}", connectorTransactionId);
            }
        })
        .onErrorReturn(false);
    }
}

