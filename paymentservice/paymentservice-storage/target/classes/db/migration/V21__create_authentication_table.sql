CREATE TABLE IF NOT EXISTS authentication (
    authentication_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    authentication_connector VARCHAR(64),
    connector_authentication_id VARCHAR(64),
    authentication_data JSONB,
    payment_method_id VARCHAR(64) NOT NULL,
    authentication_type VARCHAR(64),
    authentication_status VARCHAR(64) NOT NULL,
    authentication_lifecycle_status VARCHAR(64) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()::TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT now()::TIMESTAMP,
    error_message TEXT,
    error_code VARCHAR(64),
    connector_metadata JSONB,
    profile_id VARCHAR(64),
    payment_id VARCHAR(255),
    threeds_server_transaction_id VARCHAR(64),
    cavv VARCHAR(64),
    authentication_flow_type VARCHAR(64),
    eci VARCHAR(64),
    trans_status VARCHAR(64),
    acquirer_bin VARCHAR(64),
    acquirer_merchant_id VARCHAR(64),
    three_ds_method_data VARCHAR(255),
    three_ds_method_url VARCHAR(255),
    acs_url VARCHAR(255),
    challenge_request TEXT,
    acs_reference_number VARCHAR(64),
    acs_trans_id VARCHAR(64),
    PRIMARY KEY (authentication_id)
);

CREATE INDEX IF NOT EXISTS idx_authentication_merchant_id ON authentication(merchant_id);
CREATE INDEX IF NOT EXISTS idx_authentication_payment_id ON authentication(payment_id);
CREATE INDEX IF NOT EXISTS idx_authentication_payment_method_id ON authentication(payment_method_id);
CREATE INDEX IF NOT EXISTS idx_authentication_status ON authentication(authentication_status);

