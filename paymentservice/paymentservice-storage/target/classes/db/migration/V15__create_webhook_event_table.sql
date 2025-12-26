-- Create delivery_status enum
CREATE TYPE delivery_status AS ENUM (
    'PENDING',
    'DELIVERED',
    'FAILED',
    'RETRIES_EXCEEDED'
);

-- Create webhook_event table
CREATE TABLE webhook_event (
    id VARCHAR(255) PRIMARY KEY,
    event_id VARCHAR(255) NOT NULL UNIQUE,
    event_type VARCHAR(100) NOT NULL,
    payment_id VARCHAR(255),
    merchant_id VARCHAR(255) NOT NULL,
    connector VARCHAR(50),
    data JSONB,
    delivery_status delivery_status NOT NULL DEFAULT 'PENDING',
    delivery_url VARCHAR(500),
    attempt_count INTEGER NOT NULL DEFAULT 0,
    last_attempt_at TIMESTAMP,
    delivered_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_webhook_event_merchant_id ON webhook_event(merchant_id);
CREATE INDEX idx_webhook_event_payment_id ON webhook_event(payment_id);
CREATE INDEX idx_webhook_event_event_id ON webhook_event(event_id);
CREATE INDEX idx_webhook_event_delivery_status ON webhook_event(delivery_status);
CREATE INDEX idx_webhook_event_created_at ON webhook_event(created_at);

