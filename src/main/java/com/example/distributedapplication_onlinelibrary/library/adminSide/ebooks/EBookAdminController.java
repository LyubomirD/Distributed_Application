package com.example.distributedapplication_onlinelibrary.library.adminSide.ebooks;


import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/library-ebook-admin")
@AllArgsConstructor
public class EBookAdminController {

    private final EBookAdminServer eBookAdminServer;

    @GetMapping("/view-all-ebooks")
    public List<EBookDto> viewAllEBooks() {
        return eBookAdminServer.getAllEBooks();
    }

    @GetMapping("/get-ebookId/{title}/{genre}")
    public Long getAuthorById(@PathVariable String title, @PathVariable String genre) {
        return eBookAdminServer.getEBookId(title, genre);
    }

    @PostMapping("/add-ebook")
    public void includeNewAuthorToLibrary(@RequestBody EBookDto request) {
        eBookAdminServer.includeNewEBookToLibrary(request);
    }

    @PutMapping("/update-ebook/{ebook_id}")
    public void updateAuthorInformation(@PathVariable Long ebook_id, @RequestBody EBookDto request) {
        eBookAdminServer.changeExistingEBookInform(ebook_id, request);
    }

    @DeleteMapping("/delete-ebook/{ebook_id}")
    public void deleteAuthorFromLibrary(@PathVariable Long ebook_id) {
        eBookAdminServer.deleteEBookFromLibrary(ebook_id);
    }

}
