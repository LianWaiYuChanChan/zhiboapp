DROP TABLE IF EXISTS  account;
CREATE TABLE account (
    id SERIAL PRIMARY KEY,
   name varchar NOT NULL,
    phoneNumber varchar
);