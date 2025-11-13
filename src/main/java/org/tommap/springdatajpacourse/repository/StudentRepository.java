package org.tommap.springdatajpacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tommap.springdatajpacourse.entity.Student;

import java.util.List;
import java.util.Optional;

/*
    - wildcards are special pattern matching characters in sql LIKE operation
        + '%' -> match zero or more characters
        + '_' -> match exactly one character

    - escape -> way to tell the system - treat this special character as a regular text - not as its special meaning
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //derived queries are good as long as they are not complex -> for such complicated or custom scenarios - use @Query
    Optional<Student> findByEnrollmentId(String enrollmentId);
    List<Student> findByNameLikeAndEnrollmentIdStartsWith(String name, String enrollmentId);
    List<Student> findFirst2ByNameLikeAndEnrollmentIdStartsWith(String name, String enrollmentId);

    /*
        - JPQL
            + does not use * but require the alias -> operates on the entity not table | the alias s represents the entire Student entity
            + positional parameters -> ?1
            + named parameters -> :name | @Param("name")
     */
    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
    List<Student> findByNameLike(@Param("name") String name);

    //native query
    @Query(value = "SELECT * FROM student WHERE name LIKE %:name", nativeQuery = true)
    List<Student> findByNameEndingWith(@Param("name") String name);

    /*
        - @NamedQuery - to define a JPQL named query
        - @NamedNativeQuery - to define a native SQL named query
     */
    List<Student> findByNameLike_namedQuery(@Param("name") String name);
    List<Student> findByNameEndingWith_namedQuery(@Param("name") String name);
}
