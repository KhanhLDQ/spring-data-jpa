package org.tommap.springdatajpacourse.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.tommap.springdatajpacourse.entity.Student;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
    - create minimal application context with only JPA-related components
    - transactions roll back after each test [default] to ensure test isolation
    - H2 in-memory db used [default]
 */
@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.flyway.enabled=false"
})
public class StudentRepositoryTest{
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void initData() {
        Student alissa = new Student("Alissa Simmons", "2025AS12345");
        Student ryan = new Student("Ryan Le", "2024KL98765");
        Student sharon = new Student("Sharon Ambriz", "2022SA45665");
        Student tianna = new Student("Tianna Armentrout", "2020TA12589");
        Student angelica = new Student("Angelica Zapien", "2025AZ12543");
        Student aarti = new Student("Aarti Evatt", "2025AE86495");

        List<Student> students = List.of(alissa, ryan, sharon, tianna, angelica, aarti);
        studentRepository.saveAll(students);
    }

    @Test
    @DisplayName("find student by valid enrollment_id")
    void testFindByEnrollmentId_WhenValidDataProvided_ShouldReturnStudentDetails() {
        //arrange
        //act
        Optional<Student> optionalStudent = studentRepository.findByEnrollmentId("2025AS12345");

        //assert
        assertThat(optionalStudent).isPresent()
            .get()
            .satisfies(student -> {
                assertThat(student.getName()).isEqualTo("Alissa Simmons");
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

    @Test
    void testFindByNameLikeAndEnrollmentIdStartsWith() {
        //arrange
        //act
        List<Student> students = studentRepository.findByNameLikeAndEnrollmentIdStartsWith("A%", "2025");

        //assert
        assertThat(students)
            .hasSize(3)
            .extracting(Student::getName)
            .containsExactlyInAnyOrder("Alissa Simmons", "Angelica Zapien", "Aarti Evatt");
    }

    @Test
    void testFindFirst2ByNameLikeAndEnrollmentIdStartsWith() {
        //arrange
        //act
        List<Student> students = studentRepository.findFirst2ByNameLikeAndEnrollmentIdStartsWith("A%", "2025");

        //assert
        assertThat(students).hasSize(2);
    }

    @Test
    void testFindByNameLike() {
        //arrange
        //act
        List<Student> students = studentRepository.findByNameLike("Angelica");

        //assert
        assertThat(students).hasSize(1);
    }

    @Test
    void testFindByNameLike_namedQuery() {
        //arrange
        //act
        List<Student> students = studentRepository.findByNameLike_namedQuery("Angelica");

        //assert
        assertThat(students).hasSize(1);
    }

    @Test
    void testFindByNameEndingWith() {
        //arrange
        //act
        List<Student> students = studentRepository.findByNameEndingWith("Zapien");

        //act
        assertThat(students).hasSize(1);
    }

    @Test
    void testFindByNameEndingWith_namedQuery() {
        //arrange
        //act
        List<Student> students = studentRepository.findByNameEndingWith_namedQuery("Zapien");

        //act
        assertThat(students).hasSize(1);
    }
}
