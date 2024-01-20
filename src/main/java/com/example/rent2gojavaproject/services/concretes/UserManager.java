package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.token.VerificationToken;
import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.repositories.VerificationTokenRepository;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.UpdateUserRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import com.example.rent2gojavaproject.services.rules.UserBusinessRules;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private ModelMapperService mapperService;
    private EntityManager entityManager;
    private UserBusinessRules businessRules;
    private final VerificationTokenRepository tokenRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new NotFoundException("User " + username));
            }
        };
    }

    @Override
    public DataResult<List<GetUserListResponse>> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        List<GetUserListResponse> responses = users.stream().map(user -> this.mapperService
                        .forResponse().map(user, GetUserListResponse.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetUserListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("isActiveFilterUser");

        filter.setParameter("isActive", isActive);

        Iterable<GetUserListResponse> users = this.userRepository.findAll()
                .stream().map(user -> this.mapperService.forResponse()
                        .map(user, GetUserListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter("isActiveFilterUser");

        return new SuccessDataResult<>(users, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {

        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find user id: " + id));
        GetUserResponse response = this.mapperService.forResponse().map(user, GetUserResponse.class);

        return new SuccessDataResult<>(response, Message.GET.getMessage());
    }

    @Override
    public User addUser(User user) {

        businessRules.checkIfExistsByEmail(user.getEmail());
        businessRules.checkIfExistsPhoneNumber(user.getPhoneNumber());

        user.setCreatedAt(LocalDate.now());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {

        this.userRepository.findById(updateUserRequest.getId()).orElseThrow(() -> new NotFoundException("Couldn't find user id"));

        User user = this.mapperService.forRequest().map(updateUserRequest, User.class);

        user.setUpdatedAt(LocalDate.now());
        this.userRepository.save(user);

        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteUser(int id) {

        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
        user.setDeletedAt(LocalDate.now());

        this.userRepository.save(user);
        this.userRepository.delete(user);

        return new SuccessResult(Message.DELETE.getMessage());
    }

    @Override
    public void saveUserVerificationToken(User theUser, String token) {

        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if (token == null){
            return "Invalid verification token!";
        }

        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();

        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            tokenRepository.delete(token);

            return "Token has already expired!";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
}
