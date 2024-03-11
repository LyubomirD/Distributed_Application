package com.example.distributedapplication_onlinelibrary.library.clientSide.ebooks;

import com.example.distributedapplication_onlinelibrary.exceptions.email_exceptions.EmailValidationException;
import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import com.example.distributedapplication_onlinelibrary.mapper.dto.UserDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.EBookRequestMapper;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.UserModelRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.authors.Author;
import com.example.distributedapplication_onlinelibrary.models.authors.AuthorService;
import com.example.distributedapplication_onlinelibrary.models.books.EBook;
import com.example.distributedapplication_onlinelibrary.models.books.EBookService;
import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import com.example.distributedapplication_onlinelibrary.models.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EBookClientServer {
    private final EBookService eBookService;
    private final UserService userService;
    private final UserModelRequestMapper userModelRequestMapper;
    private final EBookRequestMapper eBookRequestMapper;


    public List<EBookDto> getAllEBooks() {
        List<EBook> eBookList = eBookService.viewAllBooks();
        List<EBookDto> eBookDtoList = eBookRequestMapper.eBookListToEBookDtoList(eBookList);

        for (int i = 0; i < eBookList.size(); i++) {
            EBook eBook = eBookList.get(i);

            String firstName = eBook.getAuthor().getFirstName();
            String lastName = eBook.getAuthor().getFirstName();

            eBookDtoList.get(i).setAuthorFirstName(firstName);
            eBookDtoList.get(i).setAuthorLastName(lastName);
        }

        return eBookDtoList;
    }


    public String addEBookToUser(EBookDto request) {
        return modifyBookAction(request, true);
    }

    public String removeEBookFromUser(EBookDto request) {
        return modifyBookAction(request, false);
    }

    public List<EBookDto> viewAllUsersEBook() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (email == null) {
            throw new EmailValidationException("Email is not validated or not registered");
        }

        Optional<UserModel> user = userService.findUserByEmail(email);

        List<EBook> userBooks = user.get().getEBooks();

        return eBookRequestMapper.eBookListToEBookDtoList(userBooks);
    }

    private String modifyBookAction(EBookDto request, boolean addBook) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (email != null) {
            Optional<UserModel> userOptional = userService.findUserByEmail(email);
            String title = request.getTitle();
            String genre = request.getGenre();
            Optional<EBook> bookOptional = eBookService.findEBookByTitleAndGenre(title, genre);

            if (userOptional.isPresent() && bookOptional.isPresent()) {
                UserModel user = userOptional.get();
                EBook book = bookOptional.get();

                if (addBook) {
                    user.getEBooks().add(book);
                } else {
                    user.getEBooks().remove(book);
                }

                userService.save(user);

                if (addBook) {
                    return "Book borrowed successfully";
                } else {
                    return "Book removed successfully";
                }
                
            }
        }

        return "Email is null";
    }

}
