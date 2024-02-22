package com.example.rent2gojavaproject.core.config;

import com.example.rent2gojavaproject.models.MailConfiguration;
import com.example.rent2gojavaproject.models.PageSettings;
import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.repositories.MailConfigurationRepository;
import com.example.rent2gojavaproject.repositories.PageSettingsRepository;
import com.example.rent2gojavaproject.repositories.UserRepository;
import com.example.rent2gojavaproject.services.abstracts.MailConfigurationService;
import com.example.rent2gojavaproject.services.abstracts.PageSettingsService;
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
    private final MailConfigurationRepository mailConfigurationRepository;
    private final MailConfigurationService mailConfigurationService;
    private final PageSettingsRepository pageSettingsRepository;
    private final PageSettingsService pageSettingsService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User defaultUser = User
                    .builder()
                    .name("default")
                    .surname("default")
                    .phoneNumber("11111111111")
                    .email("default@rentogo.com.tr")
                    .birthDate(LocalDate.of(1998, 1, 6))
                    .idCardNumber("11111111111")
                    .imageUrl("https://res.cloudinary.com/dmusx2nmy/image/upload/v1707498026/rent2go/userImages/default%40rentogo.com.tr.png")
                    .password(passwordEncoder.encode("password"))
                    .isActive(true)
                    .role(Role.USER).build();


            User admin = User
                    .builder()
                    .name("Rent2GO")
                    .surname("Admin")
                    .phoneNumber("5436751431")
                    .email("admin@rentogo.com.tr")
                    .birthDate(LocalDate.of(1999, 2, 1))
                    .idCardNumber("11111111112")
                    .imageUrl("https://res.cloudinary.com/dmusx2nmy/image/upload/v1707498263/rent2go/userImages/admin%40rentogo.com.tr.png")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ADMIN)
                    .isActive(true)
                    .build();

            userService.addDefaultUser(defaultUser);
            userService.addDefaultUser(admin);
        }

        if (mailConfigurationRepository.count() == 0) {
            MailConfiguration mailConfiguration = MailConfiguration
                    .builder()
                    .host("smtp.mailgun.org")
                    .port(587)
                    .username("postmaster@rentogo.com.tr")
                    .password("rent2GO2024")
                    .build();

            mailConfigurationService.addMailConfiguration(mailConfiguration);
        }

        if (pageSettingsRepository.count() == 0) {
            PageSettings pageSettings = PageSettings
                    .builder()
                    .title("Rent2Go")
                    .url("https://rentogo.com.tr")
                    .logo("https://res.cloudinary.com/dmusx2nmy/image/upload/v1708307806/rent2go/1.png")
                    .tabLogo("https://res.cloudinary.com/dmusx2nmy/image/upload/v1708307808/rent2go/2.png")
                    .phoneNumber("02163314800")
                    .email("support@rentogo.com.tr")
                    .address("Kavacık, Rüzgarlıbahçe Mah. Çampınarı Sok. No:4 Smart Plaza B Blok Kat:3 34805, Beykoz,İstanbul")
                    .linkedin("https://www.linkedin.com/company/tobeto/")
                    .instagram("https://www.instagram.com/tobeto_official/")
                    .github("https://github.com/rent2go")
                    .build();

            pageSettingsService.addDefaultSetting(pageSettings);
        }
    }
}
