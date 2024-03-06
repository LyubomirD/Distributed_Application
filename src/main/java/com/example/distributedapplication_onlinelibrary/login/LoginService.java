package com.example.distributedapplication_onlinelibrary.login;

import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import com.example.distributedapplication_onlinelibrary.models.users.UserRole;
import com.example.distributedapplication_onlinelibrary.models.users.UserService;
import com.example.distributedapplication_onlinelibrary.validations.EmailValidator;
import com.example.distributedapplication_onlinelibrary.validations.PasswordValidator;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LoginService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    public ResponseEntity<String> getUserBasicAuth(String authorizationHeader) {
        String[] credentialParts = splitCredentialsBase64(authorizationHeader);

        if (credentialParts == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No credentials were provided");
        }

        String username = credentialParts[0];
        String password = credentialParts[1];

        if (isUserEmailAndPasswordValid(username, password)) {
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    private String[] splitCredentialsBase64(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));

            return credentials.split(":", 2);
        }
        return null;
    }

    private boolean isUserEmailAndPasswordValid(String username, String password) {
        Optional<UserModel> userOptional = userService.findUserByEmail(username);

        if (userOptional.isEmpty()) {
            log.warn("Authentication failure: User '{}' not found", username);
            return false;
        }

        UserModel userModel = userOptional.get();

        if (!(passwordEncoder.matches(password, userModel.getPassword()))) {
            log.warn("Authentication failure for user '{}': Incorrect password", username);
            return false;
        }

        return true;
    }

}
