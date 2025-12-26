-- Payment Link table
CREATE TABLE IF NOT EXISTS payment_link (
    payment_link_id VARCHAR(64) PRIMARY KEY,
    payment_id VARCHAR(64) NOT NULL,
    link_to_pay VARCHAR(512) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    amount BIGINT NOT NULL,
    currency VARCHAR(3),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fulfilment_time TIMESTAMP,
    custom_merchant_name VARCHAR(255),
    payment_link_config JSONB,
    description TEXT,
    profile_id VARCHAR(64),
    secure_link VARCHAR(512),
    expires_at TIMESTAMP
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_payment_link_payment_id ON payment_link(payment_id);
CREATE INDEX IF NOT EXISTS idx_payment_link_merchant_id ON payment_link(merchant_id);
CREATE INDEX IF NOT EXISTS idx_payment_link_created_at ON payment_link(created_at);
CREATE INDEX IF NOT EXISTS idx_payment_link_expires_at ON payment_link(expires_at);
CREATE INDEX IF NOT EXISTS idx_payment_link_merchant_payment ON payment_link(merchant_id, payment_id);

-- Foreign key constraints (if tables exist)
-- ALTER TABLE payment_link ADD CONSTRAINT fk_payment_link_payment FOREIGN KEY (payment_id) REFERENCES payment_intent(payment_id);

