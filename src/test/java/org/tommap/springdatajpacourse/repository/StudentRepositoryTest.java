package org.tommap.springdatajpacourse.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.tommap.springdatajpacourse.entity.Student;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
    - create minimal application context with only JPA-related components
    - transactions roll back after each test [default] to ensure test isolation
    - H2 in-memory db used [default]
 */
@DataJpaTest(showSql = false)
@ActiveProfiles("test")
public class StudentRepositoryTest{
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void initData() {
        Student test = new Student("test", "2025-test-container-XXXX");
        studentRepository.save(test);
    }

    @Test
    @DisplayName("find student by valid enrollment_id")
    void testFindByEnrollmentId_WhenValidDataProvided_ShouldReturnStudentDetails() {
        //arrange
        //act
        Optional<Student> optionalStudent = studentRepository.findByEnrollmentId("2025-test-container-XXXX");

        //assert
        assertThat(optionalStudent).isPresent()
            .get()
            .satisfies(student -> {
                assertThat(student.getName()).isEqualTo("test");
            });
    }

    @Test
    @DisplayName("find student by invalid enrollment_id")
    void testFindByEnrollmentId_WhenInvalidDataProvided_ShouldReturnEmpty() {
        //arrange
        //act
        Optional<Student> optionalStudent = studentRepository.findByEnrollmentId("invalid-enrollment-id");

        //assert
        assertThat(optionalStudent).isEmpty();
    }
}
