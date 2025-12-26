-- Create ReconciliationType enum
CREATE TYPE "ReconciliationType" AS ENUM ('TWO_WAY', 'THREE_WAY');

-- Create ReconciliationStatus enum
CREATE TYPE "ReconciliationStatus" AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'FAILED');

-- Create reconciliation table
CREATE TABLE reconciliation (
    reconciliation_id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    reconciliation_type "ReconciliationType" NOT NULL,
    status "ReconciliationStatus" NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    total_transactions INTEGER DEFAULT 0,
    matched_transactions INTEGER DEFAULT 0,
    unmatched_transactions INTEGER DEFAULT 0,
    discrepancies JSONB,
    connector_id VARCHAR(64),
    reconciliation_data JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX reconciliation_merchant_id_index ON reconciliation (merchant_id);
CREATE INDEX reconciliation_status_index ON reconciliation (status);
CREATE INDEX reconciliation_created_at_index ON reconciliation (created_at);
CREATE INDEX reconciliation_merchant_status_index ON reconciliation (merchant_id, status);

