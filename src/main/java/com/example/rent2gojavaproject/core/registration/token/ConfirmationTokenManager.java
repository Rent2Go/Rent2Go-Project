package com.example.rent2gojavaproject.core.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenManager implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {

        return confirmationTokenRepository.findByToken(token);

    }

    public String findByUserIdToken(int userId) {
        return confirmationTokenRepository.findByUserIdToken(userId);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    @Override
    public void deleteConfirmationToken(String token) {
        confirmationTokenRepository.deleteConfirmationToken(token);
    }
}