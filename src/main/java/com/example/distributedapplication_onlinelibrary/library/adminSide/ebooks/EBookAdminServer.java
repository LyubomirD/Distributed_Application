package com.example.distributedapplication_onlinelibrary.library.adminSide.ebooks;

import com.example.distributedapplication_onlinelibrary.library.adminSide.CheckIsUserRoleAdminAndExistingWithEnabled;
import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.EBookRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.authors.AuthorService;
import com.example.distributedapplication_onlinelibrary.models.books.EBook;
import com.example.distributedapplication_onlinelibrary.models.books.EBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class EBookAdminServer {

    private final EBookService eBookService;
    private final AuthorService authorService;
    private final EBookRequestMapper eBookRequestMapper;
    private final CheckIsUserRoleAdminAndExistingWithEnabled permission;

    public Long getEBookId(String title, String genre) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }

        return eBookService.findBookId(title, genre);
    }

    public String includeNewEBookToLibrary(EBookDto request) {
        if (!requestChecksEBooksDto(request)) {
            return null;
        }


        EBook ebook = eBookRequestMapper.eBookDtoToEBook(request);
        Long authorId = authorService.findAuthorId(request.getAuthorFirstName(), request.getAuthorLastName());

        Author author = authorService.findAuthorById(authorId);
        ebook.setAuthor(author);

        eBookService.addNewBook(ebook);

        return "Successfully added new ebook";
    }

    public String changeExistingEBookInform(Long ebookId, EBookDto request) {
        if (!requestChecksEBooksDto(request)) {
            return null;
        }

        EBook ebook = eBookRequestMapper.eBookDtoToEBook(request);

        Long author_id = authorService.findAuthorId(request.getAuthorFirstName(), request.getAuthorLastName());
        Author author = authorService.findAuthorById(author_id);

        ebook.setAuthor(author);

        eBookService.updateExistingBook(ebookId, ebook);

        return "EBook was updated";
    }

    public String deleteEBookFromLibrary(Long ebookId) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }

        eBookService.deleteBook(ebookId);

        return "EBook deleted";
    }

    private boolean requestChecksEBooksDto(EBookDto request) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return false;
        }

        if (request == null) {
            return false;
        }

        if (request.getAuthorFirstName().isEmpty() || request.getAuthorLastName().isEmpty()) {
            return false;
        }

        if (request.getTitle().isEmpty() || request.getGenre().isEmpty()) {
            return false;
        }

        if (request.getRating() < 0 || request.getRating() > 10) {
            return false;
        }

        if (request.getPages() <= 0) {
            return false;
        }

        if (request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (request.getWhenBookAdded() == null) {
            return false;
        }

        return true;
    }

}
