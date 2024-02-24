package com.example.rent2gojavaproject;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.exceptions.BusinessRuleException;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.services.rules.UserBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserBusinessRules userBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueWhenPasswordMatches() {
        String plainPassword = "password";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(plainPassword);

        assertTrue(userBusinessRules.checkPassword(plainPassword, encryptedPassword));
    }

    @Test
    void shouldReturnFalseWhenPasswordDoesNotMatch() {
        String plainPassword = "password";
        String encryptedPassword = "wrongPassword";

        assertFalse(userBusinessRules.checkPassword(plainPassword, encryptedPassword));
    }

    @Test
    void shouldThrowAlreadyExistsExceptionWhenEmailExists() {
        String email = "test@example.com";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> userBusinessRules.checkIfExistsByEmail(email));
    }

    @Test
    void shouldNotThrowExceptionWhenEmailDoesNotExist() {
        String email = "test@example.com";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        assertDoesNotThrow(() -> userBusinessRules.checkIfExistsByEmail(email));
    }

    @Test
    void shouldThrowAlreadyExistsExceptionWhenIdCardNumberExists() {
        String idCardNumber = "123456789";

        when(userRepository.existsByIdCardNumber(idCardNumber)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> userBusinessRules.checkIfExistsByIdCarNumber(idCardNumber));
    }

    @Test
    void shouldNotThrowExceptionWhenIdCardNumberDoesNotExist() {
        String idCardNumber = "123456789";

        when(userRepository.existsByIdCardNumber(idCardNumber)).thenReturn(false);

        assertDoesNotThrow(() -> userBusinessRules.checkIfExistsByIdCarNumber(idCardNumber));
    }

    @Test
    void shouldThrowAlreadyExistsExceptionWhenPhoneNumberExists() {
        String phoneNumber = "1234567890";

        when(userRepository.existsByPhoneNumber(phoneNumber)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> userBusinessRules.checkIfExistsPhoneNumber(phoneNumber));
    }

    @Test
    void shouldNotThrowExceptionWhenPhoneNumberDoesNotExist() {
        String phoneNumber = "1234567890";

        when(userRepository.existsByPhoneNumber(phoneNumber)).thenReturn(false);

        assertDoesNotThrow(() -> userBusinessRules.checkIfExistsPhoneNumber(phoneNumber));
    }

    @Test
    void shouldThrowAlreadyExistsExceptionWhenPhoneNumberExistsForDifferentUser() {
        int userId = 1;
        String phoneNumber = "1234567890";
        User user = new User();
        user.setId(userId);
        user.setPhoneNumber("0987654321");

        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(userRepository.existsByPhoneNumber(phoneNumber)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> userBusinessRules.checkIfExistsPhoneNumber(userId, phoneNumber));
    }

    @Test
    void shouldNotThrowExceptionWhenPhoneNumberDoesNotExistForDifferentUser() {
        int userId = 1;
        String phoneNumber = "1234567890";
        User user = new User();
        user.setId(userId);
        user.setPhoneNumber("0987654321");

        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(userRepository.existsByPhoneNumber(phoneNumber)).thenReturn(false);

        assertDoesNotThrow(() -> userBusinessRules.checkIfExistsPhoneNumber(userId, phoneNumber));
    }

    @Test
    void shouldThrowBusinessRuleExceptionWhenPasswordDoesNotMatch() {
        String oldPassword = "password";
        String newPassword = "newPassword";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(oldPassword);

        when(userBusinessRules.checkPassword(oldPassword, newPassword)).thenReturn(false);

        assertThrows(BusinessRuleException.class, () -> userBusinessRules.checkChangePassword(oldPassword, newPassword));
    }

}