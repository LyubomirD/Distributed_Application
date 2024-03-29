package com.example.distributedapplication_onlinelibrary.models.authors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Author {

    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    private Long id;
    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false)
    private LocalDate authorDateOfBirth;

    @Column(nullable = false)
    private Boolean isDeath;

    @Column
    private LocalDate authorDateOfDeath;

    @Column(nullable = false, precision = 2, scale = 1)
    private Double averageRating;

    @PrePersist
    @PreUpdate
    private void validateDeathDate() {
        if (isDeath && authorDateOfDeath == null) {
            throw new IllegalArgumentException("Author's date of death must be provided if isDeath is true");
        }
    }

    public Author(String firstName, String lastName, LocalDate authorDateOfBirth, Boolean isDeath, LocalDate authorDateOfDeath, Double averageRating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorDateOfBirth = authorDateOfBirth;
        this.isDeath = isDeath;
        this.authorDateOfDeath = authorDateOfDeath;
        this.averageRating = averageRating;
    }
}
