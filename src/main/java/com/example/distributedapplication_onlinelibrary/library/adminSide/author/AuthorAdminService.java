package com.example.distributedapplication_onlinelibrary.library.adminSide.author;

import com.example.distributedapplication_onlinelibrary.library.adminSide.CheckIsUserRoleAdminAndExistingWithEnabled;
import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.AuthorRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.authors.AuthorService;
import com.example.distributedapplication_onlinelibrary.models.books.EBook;
import com.example.distributedapplication_onlinelibrary.models.books.EBookService;
import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import com.example.distributedapplication_onlinelibrary.models.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorAdminService {

    private final AuthorService authorService;
    private final EBookService eBookService;
    private final AuthorRequestMapper authorRequestMapper;
    private final CheckIsUserRoleAdminAndExistingWithEnabled permission;

    public Long getAuthorId(String firstName, String lastName) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }

        return authorService.findAuthorId(firstName, lastName);
    }

    public String includeNewAuthorToLibrary(AuthorDto request) {
        if (!requestCheckAuthorDto(request)) {
            return null;
        }

        Author author = authorRequestMapper.authorDtoToAuthor(request);
        authorService.addNewAuthor(author);

        return "Successfully added new author";
    }

    public String changeExistingAuthorInform(Long authorId, AuthorDto request) {
        if (!requestCheckAuthorDto(request)) {
            return null;
        }

        Author author = authorRequestMapper.authorDtoToAuthor(request);
        authorService.updateExistingAuthorInformation(authorId, author);

        return "Author was updated";
    }

    public String deleteAuthorFromLibrary(Long authorId) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }

        authorService.deleteAuthor(authorId);

        return "Author deleted";
    }

    private boolean requestCheckAuthorDto(AuthorDto request) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return false;
        }

        if (request == null) {
            return false;
        }

        if (request.getFirstName().isEmpty() || request.getLastName().isEmpty()) {
            return false;
        }

        if (request.getAuthorDateOfBirth() == null) {
            return false;
        }

        if (request.getIsDeath() && request.getAuthorDateOfDeath() == null) {
            return false;
        }

        if (request.getAverageRating() < 0 || request.getAverageRating() > 10) {
            return false;
        }

        return true;
    }

}
