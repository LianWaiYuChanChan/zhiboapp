-- This script is for creating domain tables in postgreSQL.

DROP TABLE IF EXISTS  account CASCADE;
CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    phoneNumber varchar,
    watchingstream_id integer
);

DROP TABLE IF EXISTS  livestream;
CREATE TABLE livestream (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    account_id integer,
    status smallint DEFAULT 0,
    isPublic boolean,
    pushurl varchar,
    pullurl varchar,
    FOREIGN KEY (account_id) REFERENCES account (id)
);

ALTER TABLE account ADD CONSTRAINT accountfk FOREIGN KEY (watchingstream_id) REFERENCES livestream (id) MATCH FULL;