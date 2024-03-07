package com.example.distributedapplication_onlinelibrary.models.books;

import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class EBook {

    @SequenceGenerator(
            name = "ebook_sequence",
            sequenceName = "ebook_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ebook_sequence"
    )
    private Long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 100)
    private String genre;

    @Column(nullable = false, precision = 2, scale = 1)
    private Double rating;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false, precision = 10)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean audioVariant;

    @Column(nullable = false)
    private LocalDateTime whenBookAdded;

    public EBook(String title, String genre, Double rating, Integer pages, BigDecimal price, Boolean audioVariant, LocalDateTime whenBookAdded) {
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
    private Author author;
}
