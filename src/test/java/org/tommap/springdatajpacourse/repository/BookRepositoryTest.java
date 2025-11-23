package org.tommap.springdatajpacourse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tommap.springdatajpacourse.AbstractIntegrationTest;
import org.tommap.springdatajpacourse.entity.Book;

public class BookRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void testAudit() {
        Book book = Book.builder()
                .title("Spring Data JPA")
                .isbn("0209-1997")
                .build();

        Book savedBook = bookRepository.save(book);

        savedBook.setTitle("Microservices");
        Book updatedBook = bookRepository.save(savedBook);
    }
}
