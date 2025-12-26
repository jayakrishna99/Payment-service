-- Payment Method table
CREATE TABLE IF NOT EXISTS payment_method (
    id VARCHAR(64) PRIMARY KEY,
    payment_method_id VARCHAR(64) NOT NULL UNIQUE,
    customer_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    payment_method_type VARCHAR(50) NOT NULL,
    payment_method_subtype VARCHAR(50),
    payment_method_data JSONB,
    locker_id VARCHAR(64),
    last_used_at TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'active',
    connector_mandate_details JSONB,
    network_transaction_id VARCHAR(128),
    client_secret VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_payment_method_customer_id ON payment_method(customer_id);
CREATE INDEX IF NOT EXISTS idx_payment_method_merchant_id ON payment_method(merchant_id);
CREATE INDEX IF NOT EXISTS idx_payment_method_status ON payment_method(status);
CREATE INDEX IF NOT EXISTS idx_payment_method_created_at ON payment_method(created_at);

-- Foreign key constraints (if customer table exists)
-- ALTER TABLE payment_method ADD CONSTRAINT fk_payment_method_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id);
-- ALTER TABLE payment_method ADD CONSTRAINT fk_payment_method_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(id);

