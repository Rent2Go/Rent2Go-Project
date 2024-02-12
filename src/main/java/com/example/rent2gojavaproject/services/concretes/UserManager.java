package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.constants.HibernateConstants;
import com.example.rent2gojavaproject.core.utilities.constants.UrlPathConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.core.registration.token.ConfirmationToken;
import com.example.rent2gojavaproject.core.registration.token.ConfirmationTokenService;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.core.services.JwtService;
import com.example.rent2gojavaproject.services.abstracts.EmailSenderService;
import com.example.rent2gojavaproject.services.abstracts.FileUpload;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.AddUserRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ChangePasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.ResetPasswordRequest;
import com.example.rent2gojavaproject.services.dtos.requests.userRequest.UpdateUserRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import com.example.rent2gojavaproject.services.rules.UserBusinessRules;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private final FileUpload fileUpload;



    @Value("${client.server}")
    String clientServer;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new NotFoundException(username + MessageConstants.NOT_FOUND.getMessage()));
            }
        };
    }

    @Override
    public DataResult<List<GetUserListResponse>> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        List<GetUserListResponse> responses = users.stream().map(user -> this.mapperService
                        .forResponse().map(user, GetUserListResponse.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetUserListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(HibernateConstants.IS_ACTIVE_FILTER_USER.getValue());

        filter.setParameter(HibernateConstants.IS_ACTIVE.getValue(), isActive);

        Iterable<GetUserListResponse> users = this.userRepository.findAll()
                .stream().map(user -> this.mapperService.forResponse()
                        .map(user, GetUserListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter(HibernateConstants.IS_ACTIVE_FILTER_USER.getValue());

        return new SuccessDataResult<>(users, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.USER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetUserResponse response = this.mapperService.forResponse().map(user, GetUserResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
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
                LocalDateTime.now().plusMinutes(30),
                user
        );
        tokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }


    @Override
    public Result  createUser(AddUserRequest user , MultipartFile file) throws IOException {

        businessRules.checkIfExistsByEmail(user.getEmail());
        businessRules.checkIfExistsPhoneNumber(user.getPhoneNumber());
        User userRequest= this.mapperService.forRequest().map(user,User.class);
        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        userRequest.setEnabled(true);
        userRequest.setImageUrl(this.fileUpload.uploadFileUser( file,user.getEmail()));
        this.userRepository.save(userRequest);
        return new SuccessResult(MessageConstants.ADD.getMessage());
    }


    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {

       User user = this.userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.USER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
       user.setId(updateUserRequest.getId());
       user.setName(updateUserRequest.getName());
       user.setEmail(updateUserRequest.getEmail());
       user.setSurname(updateUserRequest.getSurname());
       user.setPhoneNumber(updateUserRequest.getPhoneNumber());
       user.setPassword(updateUserRequest.getPassword());
       user.setRole(Role.valueOf( updateUserRequest.getRole().toString()));
        this.userRepository.save(user);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result updateUserImage(String email, MultipartFile file) throws IOException {
          User user =  this.userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(MessageConstants.EMAIL_NOT_FOUND.getMessage()));
           String userImage = this.fileUpload.uploadFileUser(file,email);
           user.setImageUrl(userImage);

           this.userRepository.save(user);


        return  new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result updateUserIsActive(int id, boolean isActive) {
        User user = this.userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage()));
        user.setActive(isActive);
        this.userRepository.save(user);
        return new SuccessResult(MessageConstants.UPDATE.getMessage()) ;
    }

    @Override
    public Result deleteUser(int id) {

        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));
        user.setDeletedAt(LocalDate.now());
        user.setActive(false);
        user.setEnabled(false);

        this.userRepository.save(user);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }

    @Override
    public void hardDeleteUser(int id) {

            User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));

            this.userRepository.delete(user);
    }

    public void resetPassword(ResetPasswordRequest resetPasswordRequest, HttpServletRequest servletRequest) throws Exception {

        User user = this.userRepository.findByEmailAndName(resetPasswordRequest.getEmail(),
                        resetPasswordRequest.getFirstname())
                .orElseThrow(()->new NotFoundException(resetPasswordRequest.getFirstname() + MessageConstants.NOT_FOUND.getMessage()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String token =  jwtService.generateToken(user);

        String link = clientServer + UrlPathConstants.PASSWORD_CHANGE_PATH.getPath() + token;

        emailSenderService.sendResetPasswordEmail(user.getName()+ " " + user.getSurname(),resetPasswordRequest.getEmail(), link);
    }

    public String changePassword(ChangePasswordRequest changePasswordRequest){

        userRepository.findByEmail(changePasswordRequest.getEmail()).orElseThrow(()->new NotFoundException(MessageConstants.EMAIL_NOT_FOUND.getMessage() ));
        userRepository.passwordChange(changePasswordRequest.getEmail(),passwordEncoder.encode(changePasswordRequest.getPassword()));


        return MessageConstants.PASSWORD_CHANGED.getMessage();

    }

    public String applicationUrl(HttpServletRequest request) {
        return UrlPathConstants.BACKEND_URL.getPath() + request.getContextPath();
    }

    @Override
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(MessageConstants.EMAIL_NOT_FOUND.getMessage()));
    }

    @Override
    public DataResult<GetUserResponse> getByEmail(String email) {
     User user =   this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(MessageConstants.EMAIL_NOT_FOUND.getMessage()));
     GetUserResponse response=    this.mapperService.forResponse().map(user,GetUserResponse.class);
        return new SuccessDataResult<>( response,MessageConstants.GET.getMessage());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
