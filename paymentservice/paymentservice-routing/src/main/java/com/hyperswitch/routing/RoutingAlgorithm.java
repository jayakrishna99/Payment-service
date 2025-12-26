package com.hyperswitch.routing;

/**
 * Routing algorithms for connector selection
 */
public enum RoutingAlgorithm {
    /**
     * Simple priority-based routing
     */
    PRIORITY_BASED,
    
    /**
     * Volume-based split routing
     */
    VOLUME_BASED,
    
    /**
     * Success-rate-based dynamic routing
     */
    SUCCESS_RATE_BASED,
    
    /**
     * Rule-based routing using DSL
     */
    RULE_BASED,
    
    /**
     * Contract-based routing
     */
    CONTRACT_BASED
}

