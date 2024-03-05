package com.example.distributedapplication_onlinelibrary.models.authors;

import com.example.distributedapplication_onlinelibrary.models.books.EBooks;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private boolean authorExists(Authors author) {
        boolean authorExisting = authorRepository
                .findByAuthorFirstNameAndAuthorLastName(author.getAuthorFirstName(), author.getAuthorLastName())
                .isPresent();

        if (authorExisting) {
            return true;
        }

        return false;
    }

    public List<Authors> viewAllAuthors() {
        return authorRepository.findAll();
    }

    public void addNewAuthor(Authors author) {
        if (authorExists(author)) {
            throw new RuntimeException("Author exists");
        }

        authorRepository.save(author);
    }

    public void updateExistingAuthorInformation(Long author_id, Authors updatedAuthorInf) {
        Authors existingAuthor = authorRepository.findById(author_id).orElseThrow(
                () -> new EntityNotFoundException("Author not found with ID: " + author_id));

        existingAuthor.setAuthorFirstName(updatedAuthorInf.getAuthorFirstName());
        existingAuthor.setAuthorLastName(updatedAuthorInf.getAuthorLastName());
        existingAuthor.setAuthorDateOfBirth(updatedAuthorInf.getAuthorDateOfBirth());
        existingAuthor.setIsDeath(updatedAuthorInf.getIsDeath());
        existingAuthor.setAuthorDateOfDeath(updatedAuthorInf.getAuthorDateOfDeath());
        existingAuthor.setAverageRating(updatedAuthorInf.getAverageRating());

        authorRepository.save(existingAuthor);
    }


    public void deleteAuthor(Long author_id) {
        Optional<Authors> optionalAuthors = authorRepository.findById(author_id);

        if (optionalAuthors.isEmpty()) {
            throw new RuntimeException("Author does NOT exists");
        }

        authorRepository.deleteById(author_id);
    }
}

