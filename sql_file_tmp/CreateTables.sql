-- This script is for creating domain tables in postgreSQL.

DROP TABLE IF EXISTS  account;
CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    phoneNumber varchar
);

DROP TABLE IF EXISTS  livestream;
CREATE TABLE livestream (
    id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    pushurl varchar,
    pullurl varchar
);