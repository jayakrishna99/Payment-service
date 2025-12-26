-- Customer table
CREATE TABLE IF NOT EXISTS customer (
    id VARCHAR(64) PRIMARY KEY,
    customer_id VARCHAR(64) NOT NULL UNIQUE,
    merchant_id VARCHAR(64) NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    phone_country_code VARCHAR(10),
    description TEXT,
    metadata JSONB,
    address_id VARCHAR(64),
    default_payment_method_id VARCHAR(64),
    connector_customer JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_customer_merchant_id ON customer(merchant_id);
CREATE INDEX IF NOT EXISTS idx_customer_email ON customer(email);
CREATE INDEX IF NOT EXISTS idx_customer_created_at ON customer(created_at);

-- Foreign key constraint (if merchant table exists in future)
-- ALTER TABLE customer ADD CONSTRAINT fk_customer_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(id);

