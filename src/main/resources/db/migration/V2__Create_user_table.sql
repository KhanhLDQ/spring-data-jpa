CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    registration_date DATE,
    email VARCHAR(255),
    level INT,
    active BOOLEAN
);