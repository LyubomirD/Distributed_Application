package com.example.distributedapplication_onlinelibrary.library.adminSide.author;

import com.example.distributedapplication_onlinelibrary.library.adminSide.CheckIsUserRoleAdminAndExistingWithEnabled;
import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.AuthorRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.authors.AuthorService;
import com.example.distributedapplication_onlinelibrary.models.books.EBook;
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

@Service
@AllArgsConstructor
public class AuthorAdminService {

    private final AuthorService authorService;
    private final AuthorRequestMapper authorRequestMapper;
    private final CheckIsUserRoleAdminAndExistingWithEnabled permission;


    public Long getAuthorId(String firstName, String lastName) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }

        return authorService.findAuthorId(firstName, lastName);
    }

    public void includeNewAuthorToLibrary(AuthorDto request) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }

        Author author = authorRequestMapper.authorDtoToAuthor(request);
        authorService.addNewAuthor(author);
    }

    public void changeExistingAuthorInform(Long authorId, AuthorDto request) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }

        Author author = authorRequestMapper.authorDtoToAuthor(request);
        authorService.updateExistingAuthorInformation(authorId, author);
    }

    public void deleteAuthorFromLibrary(Long authorId) {
        if (!permission.isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }

        authorService.deleteAuthor(authorId);
    }
}
