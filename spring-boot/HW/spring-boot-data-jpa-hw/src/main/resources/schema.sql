DROP TABLE IF EXISTS PETS, EMAILS, USERS;

CREATE TABLE PETS
(
    ID    BIGINT PRIMARY KEY AUTO_INCREMENT,
    BREED VARCHAR2(100) UNIQUE NOT NULL
);

CREATE TABLE EMAILS
(
    ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
    EMAIL    VARCHAR2(100) UNIQUE NOT NULL,
    PASSWORD VARCHAR2(100)        NOT NULL
);

CREATE TABLE USERS
(
    ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
    FIO      VARCHAR2(256) NOT NULL,
    BIRTHDAY DATE,
    MALE     BOOLEAN,
    EMAIL_ID BIGINT REFERENCES EMAILS (ID),
    PET_ID   BIGINT REFERENCES PETS (ID)
);