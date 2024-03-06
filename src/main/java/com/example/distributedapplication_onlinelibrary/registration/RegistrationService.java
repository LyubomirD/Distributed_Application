package com.example.distributedapplication_onlinelibrary.registration;

import com.example.distributedapplication_onlinelibrary.mapper.dto.UserModelDto;
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

    private void validateEmailAndPassword(UserModelDto request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        boolean isValidPassword = passwordValidator.test(request.getPassword());

        if (!isValidEmail && !isValidPassword) {
            throw new IllegalStateException("Email and password are not valid!");
        }
    }


    public String registerClient(UserModelDto request) {
        validateEmailAndPassword(request);

        UserModel userModel = userModelRequestMapper.userModelDtoToUserModel(request);
        userModel.setUserRole(CLIENT);
        String token = userService.signUpUser(userModel);

        return token;
    }

    public String registerAdministrator(UserModelDto request) {
        validateEmailAndPassword(request);

        UserModel userModel = userModelRequestMapper.userModelDtoToUserModel(request);
        userModel.setUserRole(ADMIN);
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
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUserModel(confirmationToken.getUserModel().getEmail());

        return "confirmed";
    }
}
