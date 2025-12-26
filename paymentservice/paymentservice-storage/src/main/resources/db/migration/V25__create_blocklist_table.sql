-- Create BlocklistDataKind enum
DO $$ BEGIN
    CREATE TYPE "BlocklistDataKind" AS ENUM (
        'payment_method',
        'card_bin',
        'extended_card_bin'
    );
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

-- Create blocklist table
CREATE TABLE IF NOT EXISTS blocklist (
    id SERIAL PRIMARY KEY,
    merchant_id VARCHAR(64) NOT NULL,
    fingerprint_id VARCHAR(64) NOT NULL,
    data_kind "BlocklistDataKind" NOT NULL,
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT now()::TIMESTAMP,
    UNIQUE (merchant_id, fingerprint_id)
);

CREATE INDEX IF NOT EXISTS idx_blocklist_merchant_id ON blocklist(merchant_id);
CREATE INDEX IF NOT EXISTS idx_blocklist_merchant_data_kind ON blocklist(merchant_id, data_kind, created_at DESC);

