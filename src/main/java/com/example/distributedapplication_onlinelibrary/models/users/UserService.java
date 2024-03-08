package com.example.distributedapplication_onlinelibrary.models.users;

import com.example.distributedapplication_onlinelibrary.exceptions.email_exceptions.EmailTakenException;
import com.example.distributedapplication_onlinelibrary.models.tokens.ConfirmationToken;
import com.example.distributedapplication_onlinelibrary.models.tokens.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(UserModel user) {
        Optional<UserModel> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            if (existingUser.get().isEnabled()) {
                throw new EmailTakenException("email already taken");
            } else {
                Long userId = existingUser.get().getId();
                confirmationTokenService.deleteUnConfirmedToken(userId);
                userRepository.deleteById(userId);
            }
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUserModel(String email) {
        return userRepository.enableUserModel(email);
    }

    public Optional<UserModel> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(UserModel user) {
        userRepository.save(user);
    }

}
