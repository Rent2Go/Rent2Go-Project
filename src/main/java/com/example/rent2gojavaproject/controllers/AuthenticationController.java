package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.token.VerificationToken;
import com.example.rent2gojavaproject.event.RegistrationCompleteEvent;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.VerificationTokenRepository;
import com.example.rent2gojavaproject.services.AuthenticationService;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignInRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignUpRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.JwtAuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignUpRequest signUpRequest, final HttpServletRequest request) {
        User user = authenticationService.signup(signUpRequest);

        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

        return "Success! Please, check your email to confirm your account.";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);

        if (theToken.getUser().isEnabled()){
            return "Your email address has already been verified, please log in.";
        }
        String verificationResult = userService.validateToken(token);

        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verification successful! Now you can log in to your account.";
        }
        return "Your email verification link is invalid or has expired. Please, sign up again.";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@Valid @RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}