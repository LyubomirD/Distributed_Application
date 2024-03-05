package com.example.distributedapplication_onlinelibrary.models.books;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class E_Books {

    @SequenceGenerator(
            name = "e_book_sequence",
            sequenceName = "e_book_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "e_book_sequence"
    )
    private Long id;

    @Column(length = 250, nullable = false)
    @NotNull
    private String title;

    @Column(length = 100, unique = true, nullable = false)
    @NotNull
    private String genre;

    @Column(nullable = false)
    @NotNull
    private Integer pages;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull
    private Boolean audioVariant;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime whenBookAdded;

    public E_Books(String title, String genre, Integer pages, BigDecimal price, Boolean audioVariant, LocalDateTime whenBookAdded) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.price = price;
        this.audioVariant = audioVariant;
        this.whenBookAdded = whenBookAdded;
    }
}
