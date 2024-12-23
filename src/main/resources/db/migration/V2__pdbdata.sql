CREATE TABLE pdb_data
(
    id       SERIAL PRIMARY KEY,
    created  TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name     VARCHAR(255) UNIQUE NOT NULL,
    data     TEXT                NOT NULL,
    compound VARCHAR(255),
    sha256   VARCHAR(255) UNIQUE NOT NULL
);
