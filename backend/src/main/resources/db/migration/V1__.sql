CREATE TABLE atominfo
(
    id            INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    pdb_filename  LONGTEXT           NOT NULL,
    atom_serial   INT                NOT NULL,
    request_type  LONGTEXT           NOT NULL,
    info_response LONGTEXT           NOT NULL
);