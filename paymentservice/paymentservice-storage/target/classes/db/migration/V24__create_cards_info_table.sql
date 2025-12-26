CREATE TABLE IF NOT EXISTS cards_info (
    card_iin VARCHAR(16) PRIMARY KEY,
    card_issuer TEXT,
    card_network TEXT,
    card_type TEXT,
    card_subtype TEXT,
    card_issuing_country TEXT,
    bank_code_id VARCHAR(32),
    bank_code VARCHAR(32),
    country_code VARCHAR(32),
    date_created TIMESTAMP NOT NULL DEFAULT now()::TIMESTAMP,
    last_updated TIMESTAMP,
    last_updated_provider TEXT
);

CREATE INDEX IF NOT EXISTS idx_cards_info_card_iin ON cards_info(card_iin);
CREATE INDEX IF NOT EXISTS idx_cards_info_card_network ON cards_info(card_network);

