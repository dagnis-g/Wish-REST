--liquibase formatted sql

--changeset dagnis:1

DROP TABLE IF EXISTS wish;

CREATE TABLE wish
(
    wish_id SERIAL PRIMARY KEY,
    wish    VARCHAR NOT NULL
);