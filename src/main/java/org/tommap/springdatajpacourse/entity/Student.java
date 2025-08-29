package org.tommap.springdatajpacourse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 3690222237570255927L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "enrollment_id", nullable = false, unique = true) //cannot have same enrollment_id
    private String enrollmentId;

    public Student(String name, String enrollmentId) {
        this.name = name;
        this.enrollmentId = enrollmentId;
    }
}
