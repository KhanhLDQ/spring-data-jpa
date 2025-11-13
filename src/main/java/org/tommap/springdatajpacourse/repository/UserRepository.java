package org.tommap.springdatajpacourse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.springdatajpacourse.entity.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/*
    - page(I)
        + two queries -> select & count -> count expensive - slower
        + used for traditional pagination | need 'X of Y total' display | small to medium datasets
    - slice(I)
        + one query -> select (limit n+1)
        + used for infinite scroll (load more) | do not need total count | large datasets | performance critical

    - Pageable.unpaged() -> create a special pageable that returns ALL records without pagination
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByRegistrationDateDesc();
    List<User> findByRegistrationDateOrderByUsernameAsc(LocalDate registrationDate);
    List<User> findByRegistrationDateBetween(LocalDate registrationDateStart, LocalDate registrationDateEnd);
    List<User> findByRegistrationDateBefore(LocalDate registrationDate);
    List<User> findByLevelIn(Collection<Integer> levels);
    List<User> findByLevelNotIn(Collection<Integer> levels);
    List<User> findByLevelLessThan(int level);
    List<User> findByLevelLessThanEqual(int level);
    List<User> findByUsernameContainsAllIgnoreCase(String username);
    List<User> findByIsActiveTrue();
    List<User> findFirst2ByIsActiveTrueAndUsernameEndsWith(String username);

    //lab-exercises (section 15)
    List<User> findAllByOrderByLevelDesc();
    List<User> findFirst2ByOrderByLevelDesc();
    List<User> findFirstByOrderByLevelDesc();
    List<User> findByIsActiveFalseOrLevel(int level);
    List<User> findByEmailContains(String email);

    //pagination & sorting
    List<User> findByLevelOrderByRegistrationDateDescUsernameAsc(int level);
    List<User> findByLevel(int level, Sort sort);

    //lab-exercises (section 17)
    Page<User> findByLevel(int level, Pageable pageable);
}
