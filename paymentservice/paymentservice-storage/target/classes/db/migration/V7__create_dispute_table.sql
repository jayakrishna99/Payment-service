-- Create DisputeStage enum
CREATE TYPE "DisputeStage" AS ENUM ('PRE_DISPUTE', 'DISPUTE', 'PRE_ARBITRATION', 'ARBITRATION', 'DISPUTE_REVERSAL');

-- Create DisputeStatus enum
CREATE TYPE "DisputeStatus" AS ENUM ('DISPUTE_OPENED', 'DISPUTE_EXPIRED', 'DISPUTE_ACCEPTED', 'DISPUTE_CANCELLED', 'DISPUTE_CHALLENGED', 'DISPUTE_WON', 'DISPUTE_LOST');

-- Create dispute table
CREATE TABLE dispute (
    dispute_id VARCHAR(64) PRIMARY KEY,
    amount VARCHAR(255) NOT NULL,
    currency VARCHAR(255) NOT NULL,
    dispute_stage "DisputeStage" NOT NULL,
    dispute_status "DisputeStatus" NOT NULL,
    payment_id VARCHAR(255) NOT NULL,
    attempt_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(255) NOT NULL,
    connector_status VARCHAR(255) NOT NULL,
    connector_dispute_id VARCHAR(255) NOT NULL,
    connector_reason VARCHAR(255),
    connector_reason_code VARCHAR(255),
    challenge_required_by TIMESTAMP,
    connector_created_at TIMESTAMP,
    connector_updated_at TIMESTAMP,
    connector VARCHAR(255) NOT NULL,
    evidence JSONB,
    profile_id VARCHAR(255),
    merchant_connector_id VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE UNIQUE INDEX merchant_id_dispute_id_index ON dispute (merchant_id, dispute_id);
CREATE UNIQUE INDEX merchant_id_payment_id_connector_dispute_id_index ON dispute (merchant_id, payment_id, connector_dispute_id);
CREATE INDEX dispute_status_index ON dispute (dispute_status);
CREATE INDEX dispute_stage_index ON dispute (dispute_stage);
CREATE INDEX dispute_merchant_id_index ON dispute (merchant_id);
CREATE INDEX dispute_payment_id_index ON dispute (payment_id);

