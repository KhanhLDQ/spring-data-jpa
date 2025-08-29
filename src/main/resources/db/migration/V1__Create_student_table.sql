CREATE TABLE student (
     id BIGINT NOT NULL AUTO_INCREMENT,
     name VARCHAR(255) NOT NULL,
     enrollment_id VARCHAR(255) NOT NULL,
     PRIMARY KEY (id),
     UNIQUE KEY uk_student_enrollment_id (enrollment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;