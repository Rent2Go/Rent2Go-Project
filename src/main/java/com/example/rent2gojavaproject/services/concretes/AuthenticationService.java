package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.InvalidPasswordException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.exceptions.UserNotEnabledException;
import com.example.rent2gojavaproject.core.registration.token.ConfirmationToken;
import com.example.rent2gojavaproject.core.registration.token.ConfirmationTokenService;
import com.example.rent2gojavaproject.core.services.JwtService;
import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.RefreshTokenRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignInRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignUpRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.JwtAuthenticationResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailSenderService emailSender;
    private final ConfirmationTokenService confirmationTokenService;
    private final AuthenticationManager authenticationManager;


    public String signup(SignUpRequest request, HttpServletRequest servletRequest) throws MessagingException, UnsupportedEncodingException {
        var user = User
                .builder()
                .name(request.getFirstName())
                .surname(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();


        String token = userService.addUser(user);
        String link = userService.applicationUrl(servletRequest) + "/api/confirm?token=" + token;
        emailSender.buildEmail(user.getName() + " " + user.getSurname(), request.getEmail(), link);


        return "Success! Please, check your email to confirm your account.";
    }


    public JwtAuthenticationResponse signin(SignInRequest request) {
        var user = userService.findByEmail(request.getEmail());

        if (!user.isEnabled()) {
            throw new UserNotEnabledException("User not enabled. ");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new InvalidPasswordException("Invalid password.");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var jwtToken = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);
        return JwtAuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }

    public JwtAuthenticationResponse adminSignin(SignInRequest request) {
        var user = userService.findByEmail(request.getEmail());

        if (!user.isEnabled()) {
            throw new UserNotEnabledException("User not enabled. ");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password.");
        }

        // Kullanıcının rolünü kontrol et
        if (Role.ADMIN.name().equals( user.getRole().toString())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            var jwtToken = jwtService.generateToken(user);
            var jwtRefreshToken = jwtService.generateRefreshToken(user);
            return JwtAuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
        } else {
            throw new AccessDeniedException("Admin privileges required.");
        }
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());

        User user = userService.findByEmail(userEmail);
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {

            var jwt = jwtService.generateToken(user);

            return JwtAuthenticationResponse.builder().token(jwt).refreshToken(refreshTokenRequest.getToken()).build();


        }


        return null;

    }


    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new NotFoundException("Token not found!"));

        if (confirmationToken.getConfirmedAt() != null) {
            return "Your email address has already been verified, please log in.";
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {

            confirmationTokenService.deleteConfirmationToken(token);
            userService.hardDeleteUser(confirmationToken.getUser().getId());
            return "Your email verification link is invalid or has expired. Please, sign up again.";

        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());

        confirmationTokenService.deleteConfirmationToken(token);

        return "Email verification successful! Now you can log in to your account.";
    }


}