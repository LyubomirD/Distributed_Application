package com.example.distributedapplication_onlinelibrary.models.books;

import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EBookRepository extends JpaRepository<EBook, Long> {

    Optional<EBook> findByTitleAndAuthor(String title, Author author);

}
