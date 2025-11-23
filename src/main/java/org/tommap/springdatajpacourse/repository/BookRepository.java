package org.tommap.springdatajpacourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.springdatajpacourse.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
