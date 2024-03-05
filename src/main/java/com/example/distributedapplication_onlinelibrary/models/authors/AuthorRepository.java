package com.example.distributedapplication_onlinelibrary.models.authors;

import com.example.distributedapplication_onlinelibrary.models.books.EBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {

    Optional<Authors> findByAuthorFirstNameAndAuthorLastName(String firstName, String lastName);
}
