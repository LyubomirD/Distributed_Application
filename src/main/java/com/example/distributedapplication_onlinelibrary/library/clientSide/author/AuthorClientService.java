package com.example.distributedapplication_onlinelibrary.library.clientSide.author;

import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.AuthorRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.authors.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorClientService {

    private final AuthorService authorService;
    private final AuthorRequestMapper authorRequestMapper;

    public List<AuthorDto> getAllAuthors() {
        List<Author> authorList = authorService.viewAllAuthors();
        return authorRequestMapper.authorListToAuthorDtoList(authorList);
    }

}
