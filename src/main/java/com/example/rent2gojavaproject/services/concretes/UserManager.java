package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
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
        List<GetUserListResponse> responses = users.stream().map(user -> this.mapperService
                        .forResponse().map(user, GetUserListResponse.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<List<GetUserListResponse>>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find user id: " + id));

        GetUserResponse response = this.mapperService.forResponse().map(user, GetUserResponse.class);


        return new SuccessDataResult<GetUserResponse>(response, Message.GET.getMessage());
    }

    @Override
    public Result addUser(AddUserRequest addUserRequest) {
        User user = this.mapperService.forRequest().map(addUserRequest, User.class);

        this.userRepository.save(user);
        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {
        this.userRepository.findById(updateUserRequest.getId()).orElseThrow(() -> new NotFoundException("Couldn't find user id"));

        User user = this.mapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);
        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteUser(int id) {
        this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find user id : " + id));
        this.userRepository.deleteById(id);
        return new SuccessResult(Message.DELETE.getMessage());
    }
}
