package com.example.distributedapplication_onlinelibrary.library.adminSide.author;


import com.example.distributedapplication_onlinelibrary.mapper.dto.AuthorDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/library-author-admin")
@AllArgsConstructor
public class AuthorAdminController {

    private final AuthorAdminService authorAdminService;

    @GetMapping("/get-authorId/{firstName}/{lastName}")
    public Long getAuthorById(@PathVariable String firstName, @PathVariable String lastName) {
        return authorAdminService.getAuthorId(firstName, lastName);
    }

    @PostMapping("/add-author")
    public void includeNewAuthorToLibrary(@RequestBody AuthorDto request) {
        authorAdminService.includeNewAuthorToLibrary(request);
    }

    @PutMapping("/update-author/{author_id}")
    public void updateAuthorInformation(@PathVariable Long author_id, @RequestBody AuthorDto request) {
        authorAdminService.changeExistingAuthorInform(author_id, request);
    }

    @DeleteMapping("/delete-author/{author_id}")
    public void deleteAuthorFromLibrary(@PathVariable Long author_id) {
        authorAdminService.deleteAuthorFromLibrary(author_id);
    }
}
