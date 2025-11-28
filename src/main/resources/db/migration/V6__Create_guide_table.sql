CREATE TABLE guide (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    staff_id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255),
    salary INT,
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP,
    created_by VARCHAR(255) NOT NULL,
    last_modified_by VARCHAR(255)
);

INSERT INTO guide (staff_id, name, salary, created_at, created_by)
VALUES
    ('2000MO10789', 'Mike Lawson', 1000, NOW(), 'system'),
    ('2000IM10901', 'Ian Lamb', 2000, NOW(), 'system');