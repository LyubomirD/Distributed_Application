package com.example.distributedapplication_onlinelibrary.mapper.mappers;

import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import com.example.distributedapplication_onlinelibrary.models.books.EBook;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EBookRequestMapper {

    EBookDto eBookToEBookDto(EBook eBook);

    List<EBookDto> eBookListToEBookDtoList(List<EBook> eBooks);

    EBook eBookDtoToEBook(EBookDto eBookDto);
}
