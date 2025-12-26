-- Create session_status enum
CREATE TYPE session_status AS ENUM (
    'ACTIVE',
    'COMPLETED',
    'EXPIRED',
    'CANCELLED'
);

-- Create payment_session table
CREATE TABLE payment_session (
    id VARCHAR(255) PRIMARY KEY,
    session_id VARCHAR(255) NOT NULL UNIQUE,
    merchant_id VARCHAR(255) NOT NULL,
    payment_id VARCHAR(255),
    status session_status NOT NULL DEFAULT 'ACTIVE',
    session_token VARCHAR(255) NOT NULL UNIQUE,
    amount BIGINT NOT NULL,
    currency VARCHAR(3) NOT NULL,
    customer_id VARCHAR(255),
    payment_method_id VARCHAR(255),
    metadata JSONB,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_payment_session_merchant_id ON payment_session(merchant_id);
CREATE INDEX idx_payment_session_payment_id ON payment_session(payment_id);
CREATE INDEX idx_payment_session_session_id ON payment_session(session_id);
CREATE INDEX idx_payment_session_session_token ON payment_session(session_token);
CREATE INDEX idx_payment_session_status ON payment_session(status);
CREATE INDEX idx_payment_session_expires_at ON payment_session(expires_at);

