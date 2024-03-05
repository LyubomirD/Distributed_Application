package com.example.distributedapplication_onlinelibrary.models.authors;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Authors {

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
    private String authorFirstName;

    @Column(nullable = false, length = 100)
    private String authorLastName;

    @Column(nullable = false)
    private LocalDate authorDateOfBirth;

    @Column(nullable = false)
    private Boolean isDeath;

    @Column
    private LocalDate authorDateOfDeath;

    @Column(nullable = false, precision = 3, scale = 2)
    private Double averageRating;

    @PrePersist
    @PreUpdate
    private void validateDeathDate() {
        if (isDeath && authorDateOfDeath == null) {
            throw new IllegalArgumentException("Author's date of death must be provided if isDeath is true");
        }
    }

    public Authors(String authorFirstName, String authorLastName, LocalDate authorDateOfBirth, Boolean isDeath, LocalDate authorDateOfDeath, Double averageRating) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.authorDateOfBirth = authorDateOfBirth;
        this.isDeath = isDeath;
        this.authorDateOfDeath = authorDateOfDeath;
        this.averageRating = averageRating;
    }
}
