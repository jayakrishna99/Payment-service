-- Ephemeral Key table
CREATE TABLE IF NOT EXISTS ephemeral_key (
    id VARCHAR(64) PRIMARY KEY,
    customer_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    secret VARCHAR(128) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_ephemeral_key_customer_id ON ephemeral_key(customer_id);
CREATE INDEX IF NOT EXISTS idx_ephemeral_key_merchant_id ON ephemeral_key(merchant_id);
CREATE INDEX IF NOT EXISTS idx_ephemeral_key_expires_at ON ephemeral_key(expires_at);

