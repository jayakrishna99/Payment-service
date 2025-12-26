-- Create subscription table
CREATE TABLE subscription (
    subscription_id VARCHAR(128) PRIMARY KEY,
    status VARCHAR(128) NOT NULL,
    billing_processor VARCHAR(128),
    payment_method_id VARCHAR(128),
    merchant_connector_id VARCHAR(128),
    client_secret VARCHAR(128),
    connector_subscription_id VARCHAR(128),
    merchant_id VARCHAR(64) NOT NULL,
    customer_id VARCHAR(64) NOT NULL,
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    profile_id VARCHAR(64),
    merchant_reference_id VARCHAR(255),
    plan_id VARCHAR(255),
    item_price_id VARCHAR(255)
);

-- Create indexes
CREATE UNIQUE INDEX merchant_subscription_unique_index ON subscription (merchant_id, subscription_id);
CREATE INDEX subscription_merchant_id_index ON subscription (merchant_id);
CREATE INDEX subscription_customer_id_index ON subscription (customer_id);
CREATE INDEX subscription_status_index ON subscription (status);

