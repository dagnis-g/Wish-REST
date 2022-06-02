--liquibase formatted sql

--changeset dagnis:2

CREATE TABLE if not exists wish
(
    wish_id SERIAL PRIMARY KEY,
    wish    VARCHAR NOT NULL
);