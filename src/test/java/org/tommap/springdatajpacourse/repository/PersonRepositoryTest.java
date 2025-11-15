package org.tommap.springdatajpacourse.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.TestPropertySource;
import org.tommap.springdatajpacourse.entity.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.STARTING;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void initData() {
        List<Person> persons = List.of(
            createPerson("gillamy01", "Amy", "Gill", "amy@somewhrelse.com", 1),
            createPerson("smithjohn02", "John", "Smith", "john@somewhr.com", 2),
            createPerson("lawsonmike03", "Mike", "Lawson", "mike@else.com", 3),
            createPerson("lambian03", "Ian", "Lamb", "ian@somewhr.com", 1),
            createPerson("bailektora02", "Tora", "Bailek", "tora@somewhr.com", 2),
            createPerson("smithsadie01", "Sadie", "Smith", "sadie@somewhrelse.com", 1),
            createPerson("ambrizsharon01", "Sharon", "Ambriz", "sharon@else.com", 1),
            createPerson("singhrahul02", "Rahul", "Singh", "rahul@somewhr.com", 2),
            createPerson("smithjoe02", "Joe", "Smith", "joe@else.com", 2),
            createPerson("johnsonleo03", "Leo", "Johnson", "leo@somewhr.com", 3),
            createPerson("leebrett04", "Brett", "Lee", "brett@else.com", 4)
        );

        personRepository.saveAll(persons);
    }

    @Test
    void testQueryByExample_I() {
        //arrange
        Person person = new Person(); //probe instance
        person.setLastname("Smith");
        person.setLevel(2);

        Example<Person> example = Example.of(person);

        //act
        List<Person> persons = personRepository.findAll(example);

        //assert
        assertThat(persons).hasSize(2);
        assertThat(persons)
            .extracting(Person::getUsername)
            .containsExactlyInAnyOrder("smithjohn02", "smithjoe02");
    }

    /*
     - by default only NULL values are ignored in the PROBE entity-instance
    */
    @Test
    void testQueryByExample_II() {
        //arrange
        Person person = new Person();
        person.setLastname("Smith"); //primitive int level defaults to 0, not null, so it's included in the query and won't be ignored

        Example<Person> example = Example.of(person);

        //act
        List<Person> persons = personRepository.findAll(example); //where lastName = Smith & level = 0

        //assert
        assertThat(persons).hasSize(0);
    }

    @Test
    void testQueryByExample_III() {
        //arrange
        Person person = new Person();
        person.setLastname("Smith");

        ExampleMatcher matcher = ExampleMatcher.matching() //include all non-null properties by default
                .withIgnorePaths("level");
        Example<Person> example = Example.of(person, matcher);

        //act
        List<Person> persons = personRepository.findAll(example);

        //assert
        assertThat(persons).hasSize(3);
        assertThat(persons)
            .extracting(Person::getUsername)
            .containsExactlyInAnyOrder("smithjohn02", "smithjoe02", "smithsadie01");
    }

    @Test
    void testQueryByExample_IV() {
        //arrange
        Person person = new Person();
        person.setFirstname("s");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level")
                .withStringMatcher(STARTING)
                .withIgnoreCase();
        Example<Person> example = Example.of(person, matcher);

        //act
        List<Person> persons = personRepository.findAll(example);

        //assert
        assertThat(persons).hasSize(2);
        assertThat(persons)
            .extracting(Person::getFirstname)
            .containsExactlyInAnyOrder("Sadie", "Sharon");
    }

    @Test
    void testQueryByExample_V() {
        //arrange
        Person person = new Person();
        person.setFirstname("s");
        person.setEmail("@else.com");

        ExampleMatcher matcher = ExampleMatcher.matchingAny() //OR statement
                .withIgnorePaths("level")
                .withStringMatcher(STARTING)
                .withIgnoreCase();
        Example<Person> example = Example.of(person, matcher);

        //act
        List<Person> persons = personRepository.findAll(example);

        //assert
        assertThat(persons).hasSize(2);
        assertThat(persons)
            .extracting(Person::getFirstname)
            .containsExactlyInAnyOrder("Sadie", "Sharon");
    }

    @Test
    void testQueryByExample_VI() {
        //arrange
        Person person = new Person();
        person.setFirstname("s");
        person.setEmail("@else.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level")
                .withMatcher("firstname", GenericPropertyMatcher::startsWith)
                .withMatcher("email", GenericPropertyMatcher::endsWith)
                .withIgnoreCase();
        Example<Person> example = Example.of(person, matcher);

        //act
        List<Person> persons = personRepository.findAll(example);

        //assert
        assertThat(persons).hasSize(1);
        assertThat(persons)
                .extracting(Person::getFirstname)
                .containsExactlyInAnyOrder("Sharon");
    }

    private Person createPerson(String username, String firstname, String lastname, String email, int level) {
        return Person.builder()
                .username(username)
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .level(level)
                .build();
    }
}
