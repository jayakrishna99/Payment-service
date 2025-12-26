-- Create RecoveryStatus enum
CREATE TYPE "RecoveryStatus" AS ENUM (
    'RECOVERED',
    'SCHEDULED',
    'NO_PICKED',
    'PROCESSING',
    'PARTIALLY_CAPTURED_AND_PROCESSING',
    'TERMINATED',
    'MONITORING',
    'QUEUED',
    'PARTIALLY_RECOVERED'
);

-- Create RevenueRecoveryAlgorithmType enum
CREATE TYPE "RevenueRecoveryAlgorithmType" AS ENUM (
    'EXPONENTIAL_BACKOFF',
    'LINEAR_BACKOFF',
    'FIXED_INTERVAL',
    'ADAPTIVE',
    'SMART_RETRY'
);

-- Create revenue_recovery table
CREATE TABLE revenue_recovery (
    recovery_id VARCHAR(64) PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    payment_id VARCHAR(64) NOT NULL,
    attempt_id VARCHAR(64) NOT NULL,
    profile_id VARCHAR(64) NOT NULL,
    billing_mca_id VARCHAR(64) NOT NULL,
    recovery_status "RecoveryStatus" NOT NULL DEFAULT 'MONITORING',
    retry_algorithm "RevenueRecoveryAlgorithmType" NOT NULL DEFAULT 'EXPONENTIAL_BACKOFF',
    retry_count INTEGER NOT NULL DEFAULT 0,
    max_retries INTEGER NOT NULL DEFAULT 5,
    retry_budget BIGINT,
    retry_budget_used BIGINT DEFAULT 0,
    next_retry_at TIMESTAMP,
    last_error_code VARCHAR(64),
    last_error_message TEXT,
    recovery_metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX revenue_recovery_merchant_id_index ON revenue_recovery (merchant_id);
CREATE INDEX revenue_recovery_payment_id_index ON revenue_recovery (payment_id);
CREATE INDEX revenue_recovery_status_index ON revenue_recovery (recovery_status);
CREATE INDEX revenue_recovery_next_retry_at_index ON revenue_recovery (next_retry_at);
CREATE INDEX revenue_recovery_merchant_status_index ON revenue_recovery (merchant_id, recovery_status);

