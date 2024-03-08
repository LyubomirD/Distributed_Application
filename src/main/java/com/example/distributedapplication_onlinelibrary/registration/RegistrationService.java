package com.example.distributedapplication_onlinelibrary.registration;

import com.example.distributedapplication_onlinelibrary.exceptions.EmailTakenException;
import com.example.distributedapplication_onlinelibrary.mapper.dto.UserDto;
import com.example.distributedapplication_onlinelibrary.mapper.mappers.UserModelRequestMapper;
import com.example.distributedapplication_onlinelibrary.models.tokens.ConfirmationToken;
import com.example.distributedapplication_onlinelibrary.models.tokens.ConfirmationTokenService;
import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import com.example.distributedapplication_onlinelibrary.models.users.UserRole;
import com.example.distributedapplication_onlinelibrary.models.users.UserService;
import com.example.distributedapplication_onlinelibrary.validations.EmailValidator;
import com.example.distributedapplication_onlinelibrary.validations.PasswordValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserModelRequestMapper userModelRequestMapper;
    private static final UserRole ADMIN = UserRole.ADMIN;
    private static final UserRole CLIENT = UserRole.CLIENT;

    private void validateEmailAndPassword(UserDto request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        boolean isValidPassword = passwordValidator.test(request.getPassword());

        if (!isValidEmail && !isValidPassword) {
            throw new IllegalStateException("Email and password are not valid!");
        }
    }


    public String registerClient(UserDto request) {
        return registerUser(request, UserRole.CLIENT);
    }

    public String registerAdministrator(UserDto request) {
        return registerUser(request, UserRole.ADMIN);
    }

    private String registerUser(UserDto request, UserRole userRole) {
        validateEmailAndPassword(request);

        UserModel userModel = userModelRequestMapper.userDtoToUserModel(request);
        userModel.setUserRole(userRole);
        String token = userService.signUpUser(userModel);

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailTakenException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new EmailTakenException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUserModel(confirmationToken.getUserModel().getEmail());

        return "confirmed";
    }
}
