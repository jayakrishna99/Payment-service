package com.hyperswitch.core.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service for caching connector API responses
 * Implements TTL-based caching similar to hyperswitch patterns
 */
@Service
public class ConnectorCacheService {
    
    private static final Logger log = LoggerFactory.getLogger(ConnectorCacheService.class);
    
    // Cache entries with TTL
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    
    // Default TTL: 5 minutes for status checks, 1 hour for other responses
    private static final Duration DEFAULT_TTL = Duration.ofMinutes(5);
    private static final Duration STATUS_TTL = Duration.ofMinutes(1);
    
    /**
     * Get cached response if available and not expired
     */
    public <T> Mono<T> getCached(String key, Class<T> type) {
        CacheEntry entry = cache.get(key);
        
        if (entry == null) {
            return Mono.empty();
        }
        
        if (entry.isExpired()) {
            cache.remove(key);
            return Mono.empty();
        }
        
        @SuppressWarnings("unchecked")
        T value = (T) entry.getValue();
        return Mono.just(value);
    }
    
    /**
     * Cache a response with TTL
     */
    public <T> void put(String key, T value, Duration ttl) {
        cache.put(key, new CacheEntry(value, System.currentTimeMillis() + ttl.toMillis()));
        log.debug("Cached response for key: {}, TTL: {}ms", key, ttl.toMillis());
    }
    
    /**
     * Cache a response with default TTL
     */
    public <T> void put(String key, T value) {
        put(key, value, DEFAULT_TTL);
    }
    
    /**
     * Cache a status response with shorter TTL
     */
    public <T> void putStatus(String key, T value) {
        put(key, value, STATUS_TTL);
    }
    
    /**
     * Invalidate cache entry
     */
    public void invalidate(String key) {
        cache.remove(key);
        log.debug("Invalidated cache for key: {}", key);
    }
    
    /**
     * Invalidate all cache entries for a connector
     */
    public void invalidateConnector(String connectorName) {
        String prefix = "connector:" + connectorName.toLowerCase() + ":";
        cache.entrySet().removeIf(entry -> entry.getKey().startsWith(prefix));
        log.debug("Invalidated all cache entries for connector: {}", connectorName);
    }
    
    /**
     * Clear all cache
     */
    public void clear() {
        cache.clear();
        log.debug("Cleared all cache entries");
    }
    
    /**
     * Generate cache key for connector operation
     */
    public String generateKey(String connectorName, String operation, String identifier) {
        return String.format("connector:%s:%s:%s", 
            connectorName.toLowerCase(), operation, identifier);
    }
    
    /**
     * Cache entry with expiration
     */
    private static class CacheEntry {
        private final Object value;
        private final long expiresAt;
        
        CacheEntry(Object value, long expiresAt) {
            this.value = value;
            this.expiresAt = expiresAt;
        }
        
        Object getValue() {
            return value;
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt;
        }
    }
}

