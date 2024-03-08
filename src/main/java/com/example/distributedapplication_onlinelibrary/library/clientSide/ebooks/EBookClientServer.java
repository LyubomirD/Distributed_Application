package com.example.distributedapplication_onlinelibrary.library.clientSide.ebooks;

import com.example.distributedapplication_onlinelibrary.exceptions.EmailValidationException;
import com.example.distributedapplication_onlinelibrary.mapper.dto.EBookDto;
import com.example.distributedapplication_onlinelibrary.mapper.dto.UserDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.EBookRequestMapper;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.UserModelRequestMapper;
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
            Long authorId = eBook.getAuthor().getId();
            eBookDtoList.get(i).setAuthorId(authorId);
        }

        return eBookDtoList;
    }

    public Optional<UserDto> addEBookToUser(EBookDto request) {
        return modifyBookAction(request, true);
    }

    public Optional<UserDto> removeEBookFromUser(EBookDto request) {
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

    private Optional<UserDto> modifyBookAction(EBookDto request, boolean addBook) {
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

                UserDto userDto = userModelRequestMapper.userModelToUserDto(user);

                return Optional.of(userDto);
            }
        }

        return Optional.empty();
    }

}
