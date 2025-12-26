-- Create payment_method_session table for v2 API
CREATE TABLE payment_method_session (
    id VARCHAR(255) PRIMARY KEY,
    merchant_id VARCHAR(255) NOT NULL,
    customer_id VARCHAR(255),
    billing JSONB,
    psp_tokenization VARCHAR(255),
    network_tokenization JSONB,
    tokenization_data JSONB,
    return_url VARCHAR(500),
    expires_at TIMESTAMP NOT NULL,
    client_secret VARCHAR(255) NOT NULL,
    next_action JSONB,
    authentication_details JSONB,
    associated_payment_methods JSONB,
    associated_payment VARCHAR(255),
    associated_token_id VARCHAR(255),
    storage_type VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_payment_method_session_merchant_id ON payment_method_session(merchant_id);
CREATE INDEX idx_payment_method_session_customer_id ON payment_method_session(customer_id);
CREATE INDEX idx_payment_method_session_expires_at ON payment_method_session(expires_at);
CREATE INDEX idx_payment_method_session_client_secret ON payment_method_session(client_secret);

