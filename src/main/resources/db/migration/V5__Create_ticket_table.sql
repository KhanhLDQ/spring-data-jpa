CREATE TABLE ticket (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    origin VARCHAR(255),
    destination VARCHAR(255),
    scheduled_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP,
    created_by VARCHAR(255) NOT NULL,
    last_modified_by VARCHAR(255)
);