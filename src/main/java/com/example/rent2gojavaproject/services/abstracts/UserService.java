package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.*;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    DataResult<List<GetUserListResponse>> getAllUsers();

    DataResult<GetUserListResponse> getById(int id);

    List<String> addUser(User user) throws Exception;

    List<String> addUserWithoutMernis(User user) throws Exception;

    String addDefaultUser(User user) throws Exception;

    Result createUser(AddUserRequest user, MultipartFile file) throws IOException;

    Result updateUser(UpdateUserRequest updateUserRequest);

    Result UpdateUserAccountSettings(int id, AccountSettingsRequest request);

    Result UpdateUserLocation(int id, UpdateUserLocationRequest request);

    Result updateUserImage(String email, MultipartFile file) throws IOException;

    Result updateUserIsActive(int id, boolean isActive);

    Result deleteUser(int id);

    void hardDeleteUser(int id);

    DataResult<Iterable<GetUserListResponse>> findAll(boolean isActive);

    int enableAppUser(String email);

    String applicationUrl(HttpServletRequest request);

    User findByEmail(String email);

    DataResult<GetUserListResponse> getByEmail(String email);

    boolean existsByEmail(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest, HttpServletRequest servletRequest) throws Exception;

    String changePassword(ChangePasswordRequest changePasswordRequest);

    Result profilPageChangePassword(int id, ProfilePageChangePasswordRequest request);




}
