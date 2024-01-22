package com.example.rent2gojavaproject.core.registration.token;

import java.util.Optional;

public interface ConfirmationTokenService {
    public void saveConfirmationToken(ConfirmationToken token);

    public Optional<ConfirmationToken> getToken(String token);

    public int setConfirmedAt(String token);
}
