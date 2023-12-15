package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/V1/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/getall")
    public DataResult<List<GetUserListResponse>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public DataResult<GetUserResponse> getUserById(@PathVariable int id) {
        return userService.getById(id);
    }
}
