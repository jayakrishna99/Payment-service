-- Payment Intent table
CREATE TABLE IF NOT EXISTS payment_intent (
    id VARCHAR(64) PRIMARY KEY,
    payment_id VARCHAR(64) NOT NULL UNIQUE,
    merchant_id VARCHAR(64) NOT NULL,
    status VARCHAR(50) NOT NULL,
    amount BIGINT NOT NULL,
    currency VARCHAR(3),
    amount_captured BIGINT,
    customer_id VARCHAR(64),
    description VARCHAR(255),
    return_url VARCHAR(255),
    metadata JSONB,
    connector_id VARCHAR(64),
    active_attempt_id VARCHAR(64),
    attempt_count INTEGER DEFAULT 0,
    profile_id VARCHAR(64),
    organization_id VARCHAR(32),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_synced TIMESTAMP,
    setup_future_usage VARCHAR(50),
    off_session BOOLEAN DEFAULT FALSE,
    client_secret VARCHAR(128)
);

-- Payment Attempt table
CREATE TABLE IF NOT EXISTS payment_attempt (
    id VARCHAR(64) PRIMARY KEY,
    payment_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    status VARCHAR(50) NOT NULL,
    connector VARCHAR(64),
    error_message TEXT,
    error_code VARCHAR(255),
    payment_method_id VARCHAR(64),
    authentication_type VARCHAR(50),
    connector_transaction_id VARCHAR(128),
    amount_to_capture BIGINT,
    amount_capturable BIGINT,
    amount_captured BIGINT,
    browser_info JSONB,
    connector_metadata JSONB,
    payment_method_data JSONB,
    profile_id VARCHAR(64),
    organization_id VARCHAR(32),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_synced TIMESTAMP
);

-- Refund table
CREATE TABLE IF NOT EXISTS refund (
    id VARCHAR(64) PRIMARY KEY,
    refund_id VARCHAR(64) NOT NULL UNIQUE,
    payment_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    connector_transaction_id VARCHAR(128) NOT NULL,
    connector VARCHAR(64) NOT NULL,
    connector_refund_id VARCHAR(128),
    refund_type VARCHAR(50) NOT NULL,
    total_amount BIGINT NOT NULL,
    currency VARCHAR(3) NOT NULL,
    refund_amount BIGINT NOT NULL,
    refund_status VARCHAR(50) NOT NULL,
    sent_to_gateway BOOLEAN DEFAULT FALSE,
    refund_error_message TEXT,
    refund_error_code TEXT,
    metadata JSONB,
    refund_reason VARCHAR(255),
    attempt_id VARCHAR(64) NOT NULL,
    profile_id VARCHAR(64),
    organization_id VARCHAR(32),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_payment_intent_merchant_id ON payment_intent(merchant_id);
CREATE INDEX IF NOT EXISTS idx_payment_intent_status ON payment_intent(status);
CREATE INDEX IF NOT EXISTS idx_payment_attempt_payment_id ON payment_attempt(payment_id);
CREATE INDEX IF NOT EXISTS idx_payment_attempt_merchant_id ON payment_attempt(merchant_id);
CREATE INDEX IF NOT EXISTS idx_refund_payment_id ON refund(payment_id);
CREATE INDEX IF NOT EXISTS idx_refund_merchant_id ON refund(merchant_id);

