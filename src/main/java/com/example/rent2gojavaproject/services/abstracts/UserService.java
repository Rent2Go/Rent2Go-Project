package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ChangePasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ResetPasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.UpdateUserRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    DataResult<List<GetUserListResponse>> getAllUsers();

    DataResult<GetUserResponse> getById(int id);

    String addUser(User user);

    Result updateUser(UpdateUserRequest updateUserRequest);

    Result deleteUser(int id);

    void hardDeleteUser(int id);

    DataResult<Iterable<GetUserListResponse>> findAll(boolean isActive);

    int enableAppUser(String email);

     String applicationUrl(HttpServletRequest request) ;

    User findByEmail(String email);

    boolean existsByEmail(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest, HttpServletRequest servletRequest) throws Exception;

    String changePassword(ChangePasswordRequest changePasswordRequest);


}
