package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.concretes.AuthenticationService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.RefreshTokenRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignInRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignUpRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.JwtAuthenticationResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public DataResult<String> signup(@Valid @RequestBody SignUpRequest request, HttpServletRequest servletRequest) throws Exception {
        return authenticationService.signup(request, servletRequest);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@Valid @RequestBody SignInRequest request) throws Exception {
        return authenticationService.signin(request);
    }

    @GetMapping(path = "/confirm")
    public RedirectView confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }


    @PostMapping("/refreshtoken")
    public JwtAuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authenticationService.refreshToken(refreshTokenRequest);
    }
}