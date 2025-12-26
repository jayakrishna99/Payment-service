-- Routing Algorithm Table
CREATE TABLE IF NOT EXISTS routing_algorithm (
    algorithm_id VARCHAR(64) PRIMARY KEY,
    profile_id VARCHAR(64),
    merchant_id VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(256),
    kind VARCHAR(50) NOT NULL, -- 'single', 'priority', 'volume_split', 'advanced'
    algorithm_data JSONB NOT NULL,
    algorithm_for VARCHAR(20) DEFAULT 'payment' NOT NULL, -- 'payment' or 'payout'
    decision_engine_routing_id VARCHAR(64),
    is_active BOOLEAN DEFAULT FALSE,
    is_default BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_routing_algorithm_profile_id ON routing_algorithm(profile_id);
CREATE INDEX idx_routing_algorithm_merchant_id ON routing_algorithm(merchant_id);
CREATE INDEX idx_routing_algorithm_active ON routing_algorithm(merchant_id, is_active) WHERE is_active = TRUE;
CREATE INDEX idx_routing_algorithm_default ON routing_algorithm(merchant_id, is_default) WHERE is_default = TRUE;

-- Decision Manager Config Table
CREATE TABLE IF NOT EXISTS decision_manager_config (
    id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    profile_id VARCHAR(64),
    config_type VARCHAR(50) NOT NULL, -- 'standard' or 'surcharge'
    config_data JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(merchant_id, profile_id, config_type)
);

CREATE INDEX idx_decision_manager_merchant_id ON decision_manager_config(merchant_id);
CREATE INDEX idx_decision_manager_profile_id ON decision_manager_config(profile_id);

