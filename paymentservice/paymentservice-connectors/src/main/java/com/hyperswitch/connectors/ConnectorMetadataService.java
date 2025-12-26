package com.hyperswitch.connectors;

import reactor.core.publisher.Mono;
import java.util.Map;

/**
 * Service for managing connector-specific metadata
 */
public interface ConnectorMetadataService {
    
    /**
     * Store metadata for a connector transaction
     * @param connectorTransactionId Connector transaction ID
     * @param metadata Metadata to store
     * @return Success indicator
     */
    Mono<Boolean> storeMetadata(String connectorTransactionId, Map<String, Object> metadata);
    
    /**
     * Retrieve metadata for a connector transaction
     * @param connectorTransactionId Connector transaction ID
     * @return Metadata map
     */
    Mono<Map<String, Object>> getMetadata(String connectorTransactionId);
    
    /**
     * Update metadata for a connector transaction
     * @param connectorTransactionId Connector transaction ID
     * @param metadata Metadata to update
     * @return Success indicator
     */
    Mono<Boolean> updateMetadata(String connectorTransactionId, Map<String, Object> metadata);
    
    /**
     * Delete metadata for a connector transaction
     * @param connectorTransactionId Connector transaction ID
     * @return Success indicator
     */
    Mono<Boolean> deleteMetadata(String connectorTransactionId);
}

