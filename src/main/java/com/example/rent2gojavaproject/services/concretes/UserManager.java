package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.core.registration.token.ConfirmationToken;
import com.example.rent2gojavaproject.core.registration.token.ConfirmationTokenService;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.core.services.JwtService;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ChangePasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ResetPasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.UpdateUserRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import com.example.rent2gojavaproject.services.rules.UserBusinessRules;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService mapperService;
    private final EntityManager entityManager;
    private final UserBusinessRules businessRules;
    private final ConfirmationTokenService tokenService;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Value("${client.server}")
    String clientServer;

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
    public String  addUser(User user) {

        businessRules.checkIfExistsByEmail(user.getEmail());
        businessRules.checkIfExistsPhoneNumber(user.getPhoneNumber());
        this.userRepository.save(user);


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                user
        );
        tokenService.saveConfirmationToken(
                confirmationToken);



        return token;
    }


    public String resetPassword(ResetPasswordRequest resetPasswordRequest, HttpServletRequest servletRequest) throws Exception {

        User user = this.userRepository.findByEmailAndName(resetPasswordRequest.getEmail(),
                resetPasswordRequest.getFirstname())
                .orElseThrow(()->new NotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String token =  jwtService.generateToken(user);

        String link = clientServer + "passwordchange?token=" + token;

        emailSenderService.send(user.getEmail(),
                emailSenderService.sendResetPasswordEmail(user.getName()+ " " + user.getSurname()  ,link));



        return "Successful Reset Password";
    }

    public String changePassword(ChangePasswordRequest changePasswordRequest){

        userRepository.findByEmail(changePasswordRequest.getEmail()).orElseThrow(()->new NotFoundException("Email not found"));
        userRepository.passwordChange(changePasswordRequest.getEmail(),passwordEncoder.encode(changePasswordRequest.getPassword()));


        return "Success";

    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
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

        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
        user.setDeletedAt(LocalDate.now());

        this.userRepository.save(user);
        this.userRepository.delete(user);

        return new SuccessResult(Message.DELETE.getMessage());
    }

    @Override
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

    @Override
    public User findByEmail(String email) {

      User user =   userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("email not found"));

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
