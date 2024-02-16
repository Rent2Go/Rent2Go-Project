package com.example.rent2gojavaproject.core.config;

import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User defaultUser = User
                    .builder()
                    .name("default")
                    .surname("default")
                    .phoneNumber("11111111111")
                    .email("default@rentogo.com.tr")
                    .birthDate(LocalDate.of(1992,03,24))
                    .idCardNumber("32432235412")
                    .imageUrl("https://res.cloudinary.com/dmusx2nmy/image/upload/v1707498026/rent2go/userImages/default%40rentogo.com.tr.png")
                    .password(passwordEncoder.encode("password"))
                    .isActive(true)
                    .role(Role.USER).build();


            User admin = User
                    .builder()
                    .name("admin")
                    .surname("admin")
                    .phoneNumber("5436751431")
                    .email("admin@rentogo.com.tr")
                    .birthDate(LocalDate.of(1992,03,24))
                    .idCardNumber("32032235456")
                    .imageUrl("https://res.cloudinary.com/dmusx2nmy/image/upload/v1707498263/rent2go/userImages/admin%40rentogo.com.tr.png")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ADMIN)
                    .isActive(true)
                    .build();

            userService.addUser(defaultUser);
            userService.addUser(admin);
        }
    }
}
