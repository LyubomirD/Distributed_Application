package com.example.distributedapplication_onlinelibrary.library.clientSide.author;

import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/library-client/author")
@AllArgsConstructor
public class AuthorClientController {

    private final AuthorClientService authorClientService;

    @GetMapping("/view-all-authors-library")
    public List<AuthorDto> viewAllEBooks() {
        return authorClientService.getAllAuthors();
    }
}
