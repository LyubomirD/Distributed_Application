package com.example.distributedapplication_onlinelibrary.mapper.mappers;

import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.books.EBook;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AuthorRequestMapper {

    AuthorDto authorToAuthorDto(Author author);

    List<AuthorDto> authorListToAuthorDtoList(List<Author> authorsList);

    Author authorDtoToAuthor(AuthorDto authorDto);
}
