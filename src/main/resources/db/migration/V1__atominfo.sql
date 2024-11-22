CREATE TABLE atom_info
(
    id          SERIAL PRIMARY KEY,
    created     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atom_id     LONG         NOT NULL,
    pdb_data_id VARCHAR(255) NOT NULL,
    info        LONGTEXT     NOT NULL
);