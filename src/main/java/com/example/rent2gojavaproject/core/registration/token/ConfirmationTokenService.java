package com.example.rent2gojavaproject.core.registration.token;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    int setConfirmedAt(String token);

    void deleteConfirmationToken(String token);
}
