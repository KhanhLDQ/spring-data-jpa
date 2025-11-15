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
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = -3776318332580685463L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private int level;

    @Builder
    public Person(String username, String firstname, String lastname, String email, int level) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.level = level;
    }
}
