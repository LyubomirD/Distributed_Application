package com.example.distributedapplication_onlinelibrary.models.tokens;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public void deleteUnConfirmedToken(Long userId) {
        Optional<ConfirmationToken> token = confirmationTokenRepository.findConfirmationTokenByUserModelId(userId);
        if (token.isPresent()) {
            confirmationTokenRepository.deleteById(token.get().getId());
        }
    }
}
