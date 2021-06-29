CREATE SCHEMA IF NOT EXISTS shortener;

DROP TABLE IF EXISTS shortener.t_url_meta;

CREATE TABLE shortener.t_url_meta (
    shortener_key VARCHAR(20) NOT NULL,
    actual_url VARCHAR(100) NOT NULL,
    counter INT,

    CONSTRAINT pk_url_meta PRIMARY KEY (shortener_key)
);