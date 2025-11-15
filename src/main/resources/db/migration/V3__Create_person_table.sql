CREATE TABLE person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    level INT NOT NULL DEFAULT 0
);