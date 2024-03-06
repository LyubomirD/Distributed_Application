package com.example.distributedapplication_onlinelibrary.library.authors.adminSide;

import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.AuthorRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.authors.AuthorService;
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
    private static final UserRole ADMIN = UserRole.ADMIN;

    @SneakyThrows
    private boolean isUserRoleAdminElseThrowInvalidAccessRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ADMIN"));

        if (!isAdmin || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Access is denied");
        }

        UserModel currentUser = (UserModel) authentication.getPrincipal();
        if (!currentUser.isEnabled()) {
            throw new AccessDeniedException("User is not enabled");
        }

        return true;
    }

    public Long getAuthorId(String firstName, String lastName) {
        if (!isUserRoleAdminElseThrowInvalidAccessRole()) {
            return null;
        }
        return authorService.findAuthorId(firstName, lastName);
    }

    public void includeNewAuthorToLibrary(AuthorDto request) {
        if (!isUserRoleAdminElseThrowInvalidAccessRole()) {
            return;
        }
        Author author = authorRequestMapper.authorDtoToAuthor(request);
        authorService.addNewAuthor(author);
    }

    public void changeExistingAuthorInform(Long authorId, AuthorDto request) {
    }

    public void deleteAuthorFromLibrary(Long authorId) {
    }
}
