DROP TABLE IF EXISTS users, emails, pet;

CREATE TABLE users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    fio      VARCHAR2(256) NOT NULL,
    birthday DATE,
    male     BOOLEAN,
    email_id BIGINT,
    pet_id   BIGINT
);

CREATE TABLE emails
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR2(100) NOT NULL UNIQUE,
    password VARCHAR2(100) NOT NULL
);

CREATE TABLE pet
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    breed VARCHAR2(50) UNIQUE NOT NULL
);

ALTER TABLE users
    ADD FOREIGN KEY (email_id) REFERENCES emails (id);

ALTER TABLE users
    ADD FOREIGN KEY (pet_id) REFERENCES pet (id);