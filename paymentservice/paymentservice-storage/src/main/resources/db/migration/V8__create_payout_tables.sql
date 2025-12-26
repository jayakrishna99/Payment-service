-- Create PayoutStatus enum
CREATE TYPE "PayoutStatus" AS ENUM (
    'SUCCESS',
    'FAILED',
    'CANCELLED',
    'INITIATED',
    'EXPIRED',
    'REVERSED',
    'PENDING',
    'INELIGIBLE',
    'REQUIRES_CREATION',
    'REQUIRES_PAYOUT_METHOD_DATA',
    'REQUIRES_FULFILLMENT'
);

-- Create PayoutType enum
CREATE TYPE "PayoutType" AS ENUM ('CARD', 'BANK', 'WALLET', 'BANK_REDIRECT');

-- Create payout_attempt table
CREATE TABLE payout_attempt (
    payout_attempt_id VARCHAR(64) PRIMARY KEY,
    payout_id VARCHAR(64) NOT NULL,
    customer_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    address_id VARCHAR(64) NOT NULL,
    connector VARCHAR(64) NOT NULL,
    connector_payout_id VARCHAR(128) NOT NULL,
    payout_token VARCHAR(64),
    status "PayoutStatus" NOT NULL,
    is_eligible BOOLEAN,
    error_message TEXT,
    error_code VARCHAR(64),
    business_country VARCHAR(2),
    business_label VARCHAR(64),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create payouts table
CREATE TABLE payouts (
    payout_id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    customer_id VARCHAR(64),
    address_id VARCHAR(64),
    payout_type "PayoutType",
    payout_method_id VARCHAR(64),
    amount BIGINT NOT NULL,
    destination_currency VARCHAR(3) NOT NULL,
    source_currency VARCHAR(3) NOT NULL,
    description VARCHAR(255),
    recurring BOOLEAN NOT NULL DEFAULT false,
    auto_fulfill BOOLEAN NOT NULL DEFAULT false,
    return_url VARCHAR(255),
    entity_type VARCHAR(64) NOT NULL,
    metadata JSONB DEFAULT '{}'::JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    attempt_count INTEGER DEFAULT 0,
    profile_id VARCHAR(64),
    status "PayoutStatus" NOT NULL,
    confirm BOOLEAN,
    payout_link_id VARCHAR(255),
    client_secret VARCHAR(128),
    priority VARCHAR(32),
    organization_id VARCHAR(32)
);

-- Create indexes
CREATE UNIQUE INDEX payout_attempt_index ON payout_attempt (merchant_id, payout_attempt_id, payout_id);
CREATE UNIQUE INDEX payouts_index ON payouts (merchant_id, payout_id);
CREATE INDEX payouts_merchant_id_index ON payouts (merchant_id);
CREATE INDEX payouts_status_index ON payouts (status);
CREATE INDEX payout_attempt_payout_id_index ON payout_attempt (payout_id);

