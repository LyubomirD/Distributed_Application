package com.example.distributedapplication_onlinelibrary.library.clientSide.ebooks;


import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import com.example.distributedapplication_onlinelibrary.mapper.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/library-client/ebook")
@AllArgsConstructor
public class BorrowEBooksClientController {

    private final EBookClientServer eBookClientServer;

    @GetMapping("/view-all-ebooks-library")
    public List<EBookDto> viewAllEBooks() {
        return eBookClientServer.getAllEBooks();
    }

    @GetMapping("/view-personal-library")
    public List<EBookDto> viewUserBook() {
        return eBookClientServer.viewAllUsersEBook();
    }

    @PostMapping("/borrow-ebook")
    public String borrowBooks(@RequestBody EBookDto request) {
        return eBookClientServer.addEBookToUser(request);
    }

    @PutMapping("/remove-ebook")
    public String removeBorrowedBook(@RequestBody EBookDto request) {
        return eBookClientServer.removeEBookFromUser(request);
    }


}
