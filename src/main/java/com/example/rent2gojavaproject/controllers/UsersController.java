package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.AddUserRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ChangePasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ResetPasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.UpdateUserRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin
public class UsersController {
    private final UserService userService;

    @GetMapping()
    public DataResult<List<GetUserListResponse>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getallisactive")
    public DataResult<Iterable<GetUserListResponse>> findAll(@RequestParam boolean isActive) {
        return this.userService.findAll(isActive);
    }

    @GetMapping("/{id}")
    public DataResult<GetUserListResponse> getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("/email")
    public DataResult<GetUserListResponse> getUserByEmail(@RequestParam("email") String email) {
        return userService.getByEmail(email);
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result  createUser(@RequestPart("addUserRequest")AddUserRequest user, @RequestPart("file") MultipartFile file) throws IOException {

        return this.userService.createUser(user,file);
    }


    @PatchMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @PostMapping("/imageupdate")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateUserImage(@RequestParam("email") String email , @RequestParam("file") MultipartFile file) throws IOException {

        return  this.userService.updateUserImage(email,file);
    }
    @PatchMapping("/isactive/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateUserIsActive(@PathVariable("id") int id, @RequestParam("active") boolean isActive) {

        return this.userService.updateUserIsActive(id,isActive);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, final HttpServletRequest httpRequest) throws Exception {

        userService.resetPassword(resetPasswordRequest, httpRequest);
        return "Success! Please, check your email to reset your password.";
    }

    @PatchMapping("/changepassword")
    public String changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){

        return userService.changePassword(changePasswordRequest);
    }


}


