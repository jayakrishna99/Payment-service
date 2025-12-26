CREATE TABLE IF NOT EXISTS configs (
    key VARCHAR(255) NOT NULL,
    config TEXT NOT NULL,
    PRIMARY KEY (key)
);

CREATE INDEX IF NOT EXISTS idx_configs_key ON configs(key);

