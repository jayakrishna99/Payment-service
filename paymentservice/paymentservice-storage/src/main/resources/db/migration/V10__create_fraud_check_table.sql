-- Create FraudCheckType enum
CREATE TYPE "FraudCheckType" AS ENUM ('PRE_FRM', 'POST_FRM');

-- Create FraudCheckStatus enum
CREATE TYPE "FraudCheckStatus" AS ENUM ('FRAUD', 'MANUAL_REVIEW', 'PENDING', 'LEGIT', 'TRANSACTION_FAILURE');

-- Create fraud_check table
CREATE TABLE fraud_check (
    frm_id VARCHAR(64) NOT NULL,
    payment_id VARCHAR(64) NOT NULL,
    merchant_id VARCHAR(64) NOT NULL,
    attempt_id VARCHAR(64) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    frm_name VARCHAR(255) NOT NULL,
    frm_transaction_id VARCHAR(255),
    frm_transaction_type "FraudCheckType" NOT NULL,
    frm_status "FraudCheckStatus" NOT NULL,
    frm_score INTEGER,
    frm_reason JSONB,
    frm_error VARCHAR(255),
    payment_details JSONB,
    metadata JSONB,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (frm_id, attempt_id, payment_id, merchant_id)
);

-- Create indexes
CREATE UNIQUE INDEX frm_id_index ON fraud_check (frm_id, attempt_id, payment_id, merchant_id);
CREATE INDEX fraud_check_payment_id_index ON fraud_check (payment_id);
CREATE INDEX fraud_check_merchant_id_index ON fraud_check (merchant_id);
CREATE INDEX fraud_check_status_index ON fraud_check (frm_status);

