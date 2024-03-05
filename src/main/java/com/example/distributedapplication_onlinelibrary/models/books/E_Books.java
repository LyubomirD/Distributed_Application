package com.example.distributedapplication_onlinelibrary.models.books;

import com.example.distributedapplication_onlinelibrary.models.authors.Authors;
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

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 100, unique = true)
    private String genre;

    @Column(nullable = false, precision = 3, scale = 1)
    private Double rating;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean audioVariant;

    @Column(nullable = false)
    private LocalDateTime whenBookAdded;

    public E_Books(String title, String genre, Double rating, Integer pages, BigDecimal price, Boolean audioVariant, LocalDateTime whenBookAdded) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.pages = pages;
        this.price = price;
        this.audioVariant = audioVariant;
        this.whenBookAdded = whenBookAdded;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors author;
}
