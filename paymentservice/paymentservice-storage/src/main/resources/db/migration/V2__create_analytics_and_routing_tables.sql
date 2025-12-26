-- Analytics and Routing Configuration Tables

-- Routing configuration table for storing connector priorities
CREATE TABLE IF NOT EXISTS routing_config (
    id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    profile_id VARCHAR(64),
    connector VARCHAR(64) NOT NULL,
    priority INTEGER NOT NULL DEFAULT 0,
    enabled BOOLEAN DEFAULT TRUE,
    volume_percentage DECIMAL(5,2) DEFAULT 0.00,
    min_amount BIGINT,
    max_amount BIGINT,
    currency VARCHAR(3),
    payment_method VARCHAR(64),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(merchant_id, connector, profile_id)
);

-- Success rate analytics table
CREATE TABLE IF NOT EXISTS connector_success_rate (
    id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    profile_id VARCHAR(64),
    connector VARCHAR(64) NOT NULL,
    payment_method VARCHAR(64),
    currency VARCHAR(3),
    total_attempts BIGINT DEFAULT 0,
    successful_attempts BIGINT DEFAULT 0,
    failed_attempts BIGINT DEFAULT 0,
    success_rate DECIMAL(5,2) DEFAULT 0.00,
    last_calculated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(merchant_id, connector, profile_id, payment_method, currency)
);

-- Routing decision log for analytics
CREATE TABLE IF NOT EXISTS routing_decision_log (
    id VARCHAR(64) PRIMARY KEY,
    payment_id VARCHAR(64) NOT NULL,
    attempt_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    profile_id VARCHAR(64),
    selected_connector VARCHAR(64) NOT NULL,
    routing_algorithm VARCHAR(50) NOT NULL,
    amount BIGINT NOT NULL,
    currency VARCHAR(3),
    payment_method VARCHAR(64),
    success BOOLEAN,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Scheduled tasks table
CREATE TABLE IF NOT EXISTS scheduled_task (
    id VARCHAR(64) PRIMARY KEY,
    task_id VARCHAR(64) NOT NULL UNIQUE,
    task_type VARCHAR(50) NOT NULL,
    merchant_id VARCHAR(64),
    payment_id VARCHAR(64),
    task_data JSONB,
    scheduled_at TIMESTAMP NOT NULL,
    executed_at TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'pending',
    retry_count INTEGER DEFAULT 0,
    max_retries INTEGER DEFAULT 3,
    error_message TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_routing_config_merchant ON routing_config(merchant_id);
CREATE INDEX IF NOT EXISTS idx_routing_config_profile ON routing_config(profile_id);
CREATE INDEX IF NOT EXISTS idx_success_rate_merchant ON connector_success_rate(merchant_id);
CREATE INDEX IF NOT EXISTS idx_success_rate_connector ON connector_success_rate(connector);
CREATE INDEX IF NOT EXISTS idx_routing_decision_payment ON routing_decision_log(payment_id);
CREATE INDEX IF NOT EXISTS idx_routing_decision_merchant ON routing_decision_log(merchant_id);
CREATE INDEX IF NOT EXISTS idx_scheduled_task_status ON scheduled_task(status);
CREATE INDEX IF NOT EXISTS idx_scheduled_task_scheduled_at ON scheduled_task(scheduled_at);
CREATE INDEX IF NOT EXISTS idx_scheduled_task_type ON scheduled_task(task_type);

