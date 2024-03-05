package com.example.distributedapplication_onlinelibrary.models.books;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EBookService {
    private final EBookRepository eBookRepository;

    private boolean bookExists(EBook book) {
        boolean bookExisting = eBookRepository
                .findByTitleAndAuthor(book.getTitle(), book.getAuthor().toString())
                .isPresent();

        if (bookExisting) {
            return true;
        }

        return false;
    }

    public List<EBook> viewAllBooks() {
        return eBookRepository.findAll();
    }

    public void addNewBook(EBook book) {
        if (bookExists(book)) {
            throw new RuntimeException("Book exists");
        }

        eBookRepository.save(book);
    }

    public void updateExistingBook(Long book_id, EBook updatedBook) {
        EBook existingBook = eBookRepository.findById(book_id).orElseThrow(
                () -> new EntityNotFoundException("Book not found with ID: " + book_id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setRating(updatedBook.getRating());
        existingBook.setPages(updatedBook.getPages());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setAudioVariant(updatedBook.getAudioVariant());
        existingBook.setWhenBookAdded(updatedBook.getWhenBookAdded());
        existingBook.setAuthor(updatedBook.getAuthor());

        eBookRepository.save(existingBook);
    }


    public void deleteBook(Long bookId) {
        Optional<EBook> optionalBook = eBookRepository.findById(bookId);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book does NOT exists");
        }

        eBookRepository.deleteById(bookId);
    }
}
