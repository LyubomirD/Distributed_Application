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

import java.util.List;


@Service
@AllArgsConstructor
public class EBookAdminServer {

    private final EBookService eBookService;
    private final AuthorService authorService;
    private final EBookRequestMapper eBookRequestMapper;
    private final CheckIsUserRoleAdminAndExistingWithEnabled permission;


    public List<EBookDto> getAllEBooks() {
        List<EBook> eBookList = eBookService.viewAllBooks();

        return eBookRequestMapper.eBookListToEBookDtoList(eBookList);
    }

    public Long getEBookId(String title, String genre) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }

        return eBookService.findBookId(title, genre);
    }

    public void includeNewEBookToLibrary(EBookDto request) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }
        EBook ebook = eBookRequestMapper.eBookDtoToEBook(request);
        ebook.setAuthor(request.getAuthor());

        authorService.addNewAuthor(request.getAuthor());

        eBookService.addNewBook(ebook);
    }

    public void changeExistingEBookInform(Long ebookId, EBookDto request) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }

        EBook ebook = eBookRequestMapper.eBookDtoToEBook(request);
        eBookService.updateExistingBook(ebookId, ebook);
    }

    public void deleteEBookFromLibrary(Long ebookId) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }

        eBookService.deleteBook(ebookId);
    }
}
