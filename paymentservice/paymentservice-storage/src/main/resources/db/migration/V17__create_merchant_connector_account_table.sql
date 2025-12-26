-- Create merchant_connector_account table
CREATE TABLE IF NOT EXISTS merchant_connector_account (
    id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    merchant_connector_id VARCHAR(128) NOT NULL UNIQUE,
    connector_name VARCHAR(64) NOT NULL,
    connector_account_details BYTEA,
    test_mode BOOLEAN DEFAULT FALSE,
    disabled BOOLEAN DEFAULT FALSE,
    payment_methods_enabled JSONB,
    connector_type VARCHAR(32),
    metadata JSONB,
    connector_label VARCHAR(255),
    business_country VARCHAR(2),
    business_label VARCHAR(255),
    business_sub_label VARCHAR(64),
    frm_configs JSONB,
    connector_webhook_details JSONB,
    profile_id VARCHAR(64),
    applepay_verified_domains TEXT[],
    pm_auth_config JSONB,
    status VARCHAR(32) DEFAULT 'active',
    additional_merchant_data BYTEA,
    connector_wallets_details BYTEA,
    version VARCHAR(16) DEFAULT 'v1',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_merchant_connector_account_merchant_id ON merchant_connector_account(merchant_id);
CREATE INDEX idx_merchant_connector_account_connector_name ON merchant_connector_account(connector_name);
CREATE INDEX idx_merchant_connector_account_profile_id ON merchant_connector_account(profile_id);
CREATE UNIQUE INDEX idx_merchant_connector_account_unique ON merchant_connector_account(merchant_id, connector_name, profile_id);

