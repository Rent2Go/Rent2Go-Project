package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.concretes.AuthenticationService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.SignInRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.JwtAuthenticationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@AllArgsConstructor
@CrossOrigin
public class AdminController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@Valid @RequestBody SignInRequest request) throws Exception {
        return authenticationService.adminSignin(request);
    }


}
