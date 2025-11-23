CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    isbn VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    last_modified_at TIMESTAMP,
    last_modified_by VARCHAR(255)
);