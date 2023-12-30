package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.AuthenticationService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignInRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignUpRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}