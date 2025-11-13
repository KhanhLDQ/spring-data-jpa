package org.tommap.springdatajpacourse.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.tommap.springdatajpacourse.entity.User;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.flyway.enabled=false"
})
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void initData() {
        User john = User.builder()
                .username("john")
                .registrationDate(LocalDate.of(2021, 8, 4))
                .email("john@somewhr.com")
                .level(1)
                .isActive(true)
                .build();

        User jane = User.builder()
                .username("jane")
                .registrationDate(LocalDate.of(2019, 3, 18))
                .email("jane@somewhrelse.com")
                .level(2)
                .isActive(true)
                .build();

        User nicole = User.builder()
                .username("nicole")
                .registrationDate(LocalDate.of(2017, 7, 21))
                .email("nicole@somewhr.com")
                .level(1)
                .isActive(false)
                .build();

        User ravi = User.builder()
                .username("ravi")
                .registrationDate(LocalDate.of(2018, 6, 15))
                .email("ravi@somewhrelse.com")
                .level(1)
                .isActive(false)
                .build();

        User alissa = User.builder()
                .username("alissa")
                .registrationDate(LocalDate.of(2014, 4, 5))
                .email("alissa@somewhr.com")
                .level(2)
                .isActive(true)
                .build();

        User katie = User.builder()
                .username("katie")
                .registrationDate(LocalDate.of(2014, 4, 5))
                .email("katie@somewhrelse.com")
                .level(3)
                .isActive(true)
                .build();

        User mary = User.builder()
                .username("mary")
                .registrationDate(LocalDate.of(2020, 5, 14))
                .email("mary@somewhr.com")
                .level(4)
                .isActive(false)
                .build();

        User judy = User.builder()
                .username("judy")
                .registrationDate(LocalDate.of(2015, 1, 18))
                .email("judy@somewhrelse.com")
                .level(3)
                .isActive(true)
                .build();

        List<User> users = List.of(john, jane, nicole, ravi, alissa, katie, mary, judy);
        userRepository.saveAll(users);
    }

    @Test
    void testFindAllByOrderByRegistrationDateDesc() {
        //arrange
        //act
        List<User> users = userRepository.findAllByOrderByRegistrationDateDesc();

        //assert
        assertThat(users).isNotEmpty();
        assertThat(users)
            .extracting(User::getUsername)
            .startsWith("john", "mary", "jane");
    }

    @Test
    void testFindByRegistrationDateOrderByUsernameAsc() {
        //arrange
        //act
        List<User> users = userRepository.findByRegistrationDateOrderByUsernameAsc(LocalDate.of(2014, 4, 5));

        //assert
        assertThat(users).hasSize(2);
        assertThat(users)
            .extracting(User::getUsername)
            .containsExactly("alissa", "katie");
    }

    @Test
    void testFindByRegistrationDateBetween() {
        //arrange
        LocalDate start = LocalDate.of(2017, 4, 1);
        LocalDate end = LocalDate.of(2019, 3, 31);

        //act
        List<User> users = userRepository.findByRegistrationDateBetween(start, end);

        //assert
        assertThat(users).hasSize(3);
    }

    @Test
    void testFindByRegistrationDateBefore() {
        //arrange
        LocalDate before = LocalDate.of(2017, 1, 1);

        //act
        List<User> users = userRepository.findByRegistrationDateBefore(before);

        //assert
        assertThat(users).hasSize(3);
    }

    @Test
    void testFindByLevelIn() {
        //arrange
        //act
        List<User> users = userRepository.findByLevelIn(List.of(1,3));

        //assert
        assertThat(users).hasSize(5);
    }

    @Test
    void testFindByLevelNotIn() {
        //arrange
        //act
        List<User> users = userRepository.findByLevelNotIn(List.of(1,3));

        //assert
        assertThat(users).hasSize(3);
    }

    @Test
    void testFindByLevelLessThan() {
        //arrange
        //act
        List<User> users = userRepository.findByLevelLessThan(2);

        //assert
        assertThat(users).hasSize(3);
    }

    @Test
    void testFindByLevelLessThanEqual() {
        //arrange
        //act
        List<User> users = userRepository.findByLevelLessThanEqual(2);

        //assert
        assertThat(users).hasSize(5);
    }

    @Test
    void testFindByUsernameContainsAllIgnoreCase() {
        //arrange
        //act
        List<User> users = userRepository.findByUsernameContainsAllIgnoreCase("L");

        //assert
        assertThat(users).hasSize(2);
    }

    @Test
    void testFindByIsActiveTrue() {
        //arrange
        //act
        List<User> users = userRepository.findByIsActiveTrue();

        //assert
        assertThat(users).hasSize(5);
    }

    @Test
    void testFindFirst2ByIsActiveTrueAndUsernameEndsWith() {
        //arrange
        //act
        List<User> users = userRepository.findFirst2ByIsActiveTrueAndUsernameEndsWith("e");

        //assert
        assertThat(users).hasSize(2);
    }

    @Test
    void testFindAllByOrderByLevelDesc() {
        //arrange
        //act
        List<User> users = userRepository.findAllByOrderByLevelDesc();

        //assert
        assertThat(users).hasSize(8);
        assertThat(users)
            .extracting(User::getUsername)
            .startsWith("mary");
    }

    @Test
    void testFindFirst2ByOrderByLevelDesc() {
        //arrange
        //act
        List<User> users = userRepository.findFirst2ByOrderByLevelDesc();

        //assert
        assertThat(users).hasSize(2);
        assertThat(users)
            .extracting(User::getUsername)
            .startsWith("mary");
    }

    @Test
    void testFindFirstByOrderByLevelDesc() {
        //arrange
        //act
        List<User> users = userRepository.findFirstByOrderByLevelDesc();

        //assert
        assertThat(users).hasSize(1);
        assertThat(users)
            .extracting(User::getUsername)
            .startsWith("mary");
    }

    @Test
    void testFindByIsActiveFalseOrLevel() {
        //arrange
        //act
        List<User> users = userRepository.findByIsActiveFalseOrLevel(1);

        //assert
        assertThat(users).hasSize(4);
    }

    @Test
    void testFindByEmailContains() {
        //arrange
        //act
        List<User> users = userRepository.findByEmailContains("else");

        //assert
        assertThat(users).hasSize(4);
        assertThat(users)
            .extracting(User::getUsername)
            .containsExactlyInAnyOrder("jane", "ravi", "katie", "judy");
    }

    @Test
    void testFindAllPageable_pageI() {
        //arrange
        //act
        Page<User> userPage = userRepository.findAll(PageRequest.of(0,3));

        //assert
        assertEquals(3, userPage.getNumberOfElements());
        assertEquals(0, userPage.getNumber());
        assertEquals(3, userPage.getTotalPages());
        assertEquals(8, userPage.getTotalElements());
    }

    @Test
    void testFindAllPageable_pageIII() {
        //arrange
        //act
        Page<User> userPage = userRepository.findAll(PageRequest.of(2,3));

        //assert
        assertEquals(2, userPage.getNumberOfElements());
        assertEquals(2, userPage.getNumber());
        assertEquals(3, userPage.getTotalPages());
        assertEquals(8, userPage.getTotalElements());
    }

    @Test
    void testFindAllPageable_sortByLevelAndUsername() {
        //arrange
        Sort sort = Sort.by("level").descending().and(Sort.by("username"));
        Pageable pageable = PageRequest.of(0, 3, sort);

        //act
        Page<User> userPage = userRepository.findAll(pageable);

        //assert
        assertEquals(3, userPage.getNumberOfElements());
        assertEquals(0, userPage.getNumber());
        assertThat(userPage.getContent())
            .extracting(User::getUsername)
            .containsExactly("mary", "judy", "katie");
    }

    @Test
    void testFindByLevelOrderByRegistrationDateDescUsernameAsc() {
        //arrange
        //act
        List<User> users = userRepository.findByLevelOrderByRegistrationDateDescUsernameAsc(1);

        //assert
        assertThat(users).hasSize(3);
        assertThat(users)
            .extracting(User::getUsername)
            .containsExactly("john", "ravi", "nicole");
    }

    @Test
    void testFindByLevel() {
        //arrange
        Sort sort = Sort.by("registrationDate").descending().and(Sort.by("username"));

        //act
        List<User> users = userRepository.findByLevel(1, sort);

        //assert
        assertThat(users).hasSize(3);
        assertThat(users)
            .extracting(User::getUsername)
            .containsExactly("john", "ravi", "nicole");
    }

    @Test
    void testFindByLevel_pageable() {
        //arrange
        Sort sort = Sort.by("registrationDate");
        Pageable pageable = PageRequest.of(0, 2, sort);

        //act
        Page<User> userPage = userRepository.findByLevel(1, pageable);

        //assert
        assertEquals(2, userPage.getNumberOfElements());
        assertEquals(0, userPage.getNumber());
        assertThat(userPage.getContent())
            .extracting(User::getUsername)
            .containsExactly("nicole", "ravi");
    }
}
