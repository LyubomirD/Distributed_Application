package com.example.distributedapplication_onlinelibrary.library.adminSide.author;

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



    public Long getAuthorId(String firstName, String lastName) {
        return null;
    }

    public void includeNewAuthorToLibrary(AuthorDto request) {
    }

    public void changeExistingAuthorInform(Long authorId, AuthorDto request) {
    }

    public void deleteAuthorFromLibrary(Long authorId) {
    }
}
