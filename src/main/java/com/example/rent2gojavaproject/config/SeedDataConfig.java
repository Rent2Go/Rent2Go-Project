package com.example.rent2gojavaproject.config;

import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
                    .password(passwordEncoder.encode("password"))
                    .role(Role.USER)
                    .isEnabled(true).build();


            User admin = User
                    .builder()
                    .name("admin")
                    .surname("admin")
                    .phoneNumber("5436751431")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ADMIN)
                    .isEnabled(true)
                    .build();

            userService.addUser(defaultUser);
            userService.addUser(admin);
        }
    }
}
