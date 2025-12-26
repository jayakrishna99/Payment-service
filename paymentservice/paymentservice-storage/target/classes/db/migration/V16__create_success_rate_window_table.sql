-- Success rate window table for time-window based metrics
CREATE TABLE IF NOT EXISTS success_rate_window (
    id VARCHAR(64) PRIMARY KEY,
    profile_id VARCHAR(64) NOT NULL,
    connector VARCHAR(64) NOT NULL,
    payment_method VARCHAR(64),
    currency VARCHAR(3),
    window_start TIMESTAMP NOT NULL,
    window_end TIMESTAMP NOT NULL,
    total_attempts BIGINT DEFAULT 0,
    successful_attempts BIGINT DEFAULT 0,
    failed_attempts BIGINT DEFAULT 0,
    success_rate DECIMAL(5,2) DEFAULT 0.00,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_profile_connector (profile_id, connector),
    INDEX idx_window_time (window_start, window_end)
);

