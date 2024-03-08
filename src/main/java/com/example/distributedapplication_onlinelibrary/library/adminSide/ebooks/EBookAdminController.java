package com.example.distributedapplication_onlinelibrary.library.adminSide.ebooks;


import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/library-admin/ebook")
@AllArgsConstructor
public class EBookAdminController {

    private final EBookAdminServer eBookAdminServer;

    @GetMapping("/get-ebookId/{title}/{genre}")
    public Long getAuthorById(@PathVariable String title, @PathVariable String genre) {
        return eBookAdminServer.getEBookId(title, genre);
    }

    @PostMapping("/add-ebook")
    public String includeNewAuthorToLibrary(@RequestBody EBookDto request) {
        return eBookAdminServer.includeNewEBookToLibrary(request);
    }

    @PutMapping("/update-ebook/{ebook_id}")
    public String updateAuthorInformation(@PathVariable Long ebook_id, @RequestBody EBookDto request) {
        return eBookAdminServer.changeExistingEBookInform(ebook_id, request);
    }

    @DeleteMapping("/delete-ebook/{ebook_id}")
    public String deleteAuthorFromLibrary(@PathVariable Long ebook_id) {
        return eBookAdminServer.deleteEBookFromLibrary(ebook_id);
    }

}
