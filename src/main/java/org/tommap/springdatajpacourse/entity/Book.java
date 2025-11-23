package org.tommap.springdatajpacourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Book extends BaseAuditEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7711683270163415942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    @Builder
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }
}
