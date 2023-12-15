package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.AddUserRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.UpdateUserRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private ModelMapperService mapperService;
    @Override
    public DataResult<List<GetUserListResponse>> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<GetUserListResponse> responses = users.stream().map(employee -> this.mapperService.forResponse().map(users, GetUserListResponse.class)).collect(Collectors.toList());


        return new SuccessDataResult<List<GetUserListResponse>>(responses,"Transaction Successfully");
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {
        return null;
    }

    @Override
    public Result addUser(AddUserRequest addUserRequest) {
        return null;
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {
        return null;
    }

    @Override
    public Result deleteUser(int id) {
        return null;
    }
}
