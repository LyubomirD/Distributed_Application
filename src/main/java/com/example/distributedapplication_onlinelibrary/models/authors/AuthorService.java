package com.example.distributedapplication_onlinelibrary.models.authors;

import com.example.distributedapplication_onlinelibrary.exceptions.ebook_exception.EBookNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private boolean authorExists(Author author) {
        boolean authorExisting = authorRepository
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(author.getFirstName(), author.getLastName())
                .isPresent();

        if (authorExisting) {
            return true;
        }

        return false;
    }

    public List<Author> viewAllAuthors() {
        return authorRepository.findAll();
    }

    public Long findAuthorId(String firstName, String lastName) {
        Optional<Author> author = authorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

        if (author.isEmpty()) {
            throw new EBookNotFoundException("Author does not exists");
        }

        return author.get().getId();
    }

    public void addNewAuthor(Author author) {
        if (authorExists(author)) {
            throw new RuntimeException("Author exists");
        }

        authorRepository.save(author);
    }

    public void updateExistingAuthorInformation(Long author_id, Author updatedAuthorInf) {
        Author existingAuthor = authorRepository.findById(author_id).orElseThrow(
                () -> new EntityNotFoundException("Author not found with ID: " + author_id));

        existingAuthor.setFirstName(updatedAuthorInf.getFirstName());
        existingAuthor.setLastName(updatedAuthorInf.getLastName());
        existingAuthor.setAuthorDateOfBirth(updatedAuthorInf.getAuthorDateOfBirth());
        existingAuthor.setIsDeath(updatedAuthorInf.getIsDeath());
        existingAuthor.setAuthorDateOfDeath(updatedAuthorInf.getAuthorDateOfDeath());
        existingAuthor.setAverageRating(updatedAuthorInf.getAverageRating());

        authorRepository.save(existingAuthor);
    }


    public void deleteAuthor(Long author_id) {
        Optional<Author> optionalAuthors = authorRepository.findById(author_id);

        if (optionalAuthors.isEmpty()) {
            throw new RuntimeException("Author does NOT exists");
        }

        authorRepository.deleteById(author_id);
    }

    public Author findAuthorById(Long author_id) {
        return authorRepository.getReferenceById(author_id);
    }
}

