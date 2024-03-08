package com.example.distributedapplication_onlinelibrary.library.adminSide.author;


import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/library-admin/author")
@AllArgsConstructor
public class AuthorAdminController {

    private final AuthorAdminService authorAdminService;

    @GetMapping("/get-authorId/{firstName}/{lastName}")
    public Long getAuthorById(@PathVariable String firstName, @PathVariable String lastName) {
        return authorAdminService.getAuthorId(firstName, lastName);
    }

    @PostMapping("/add-author")
    public String includeNewAuthorToLibrary(@RequestBody AuthorDto request) {
        return authorAdminService.includeNewAuthorToLibrary(request);
    }

    @PutMapping("/update-author/{author_id}")
    public String updateAuthorInformation(@PathVariable Long author_id, @RequestBody AuthorDto request) {
        return authorAdminService.changeExistingAuthorInform(author_id, request);
    }

    @DeleteMapping("/delete-author/{author_id}")
    public String deleteAuthorFromLibrary(@PathVariable Long author_id) {
        return authorAdminService.deleteAuthorFromLibrary(author_id);
    }
}
