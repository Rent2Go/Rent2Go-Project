package com.example.rent2gojavaproject.core.config;

import com.example.rent2gojavaproject.core.filter.JwtAuthenticationFilter;
import com.example.rent2gojavaproject.models.Role;
import com.example.rent2gojavaproject.services.concretes.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",

    };
    private static final String[] GET_WHITE_LIST_URL = {"/api/brands",
            "/api/brands/getallisactive",
            "/api/brands/{id}",
            "/api/cars/getallisactive",
            "/api/cities",
            "/api/colors",
            "/api/colors/{id}",
            "/api/colors/getallisactive",
            "/api/districts",
            "/api/models",
            "/api/models/{id}",
            "/api/models/getallisactive",
            "/api/ourteams",
            "/api/settings",
            "/api/settings/{id}",
            "/api/confirm",
            "/api/cars",
            "/api/cars/{id}",



    };
    private static final String[] GET_ADMIN_URL = {"/api/creditcard",
            "/api/departments",
            "/api/departments/{id}",
            "/api/customers",
            "/api/customers/getallisactive",
            "/api/employees",
            "/api/employees/{id}",
            "/api/employees/getallisactive",
            "/api/imagedata/{fileName}",
            "/api/jobtitles",
            "/api/jobtitles/{id}",
            "/api/mail-configuration",
            "/api/users",
            "/api/users/getallisactive",

    };
    private static final String[] GET_USER_ADMIN_URL = {"/api/discounts",
            "/api/discounts/getallisactive",
            "/api/discounts/{id}",
            "/api/discounts/code/{discountCode}",
            "/api/rentals",
            "/api/rentals/getallisactive",
            "/api/rentals/{id}",
            "/api/rentals/uniquediscount/{discountId}",
            "/api/users/{id}",
            "/api/getCustomerrentals",
            "/api/customers/{id}",
            "/api/users/email",


    };
    private static final String [] POST_WHITE_LIST_URL = {"/api/users/resetpassword",
            "/api/signup",
            "/api/signin",
            "/api/admins/signin",
            "/api/send-contact-email",
            "/api/customers",

    };

    private static final String[] ADMIN_POST_URL = {"/api/brands",
            "/api/cars",
            "/api/cars/imageupdate",
            "/api/colors",
            "/api/discounts",
            "/api/employees",
            "/api/mail-configuration",
            "/api/models",
            "/api/ourteams",
            "/api/settings",
            "/api/users",
            "/api/creditcard",


    };
    private static final String[] ADMIN_USER_POST_URL = {
            "/api/refreshtoken",
            "/api/creditcard/checkpayment",
            "/api/users/imageupdate",
            "/api/rentals",
            "/api/files/upload",
            "/api/imagedata/upload",
            "/api/reservation-details",
            "/api/send-cash-email",


    };
    private static final String[] ADMIN_PUT_URL = {"/api/brands",
            "/api/cars",
            "/api/colors",
            "/api/customers",
            "/api/discounts",
            "/api/employees",
            "/api/mail-configuration",
            "/api/models",
            "/api/settings",
            "/api/settings/settingsimage",
            "/api/rentals",
            "/api/creditcard",
    };

    private static final String[] ADMIN_DELETE_URL = {"/api/brands/{id}",
            "/api/cars/{id}",
            "/api/colors/{id}",
            "/api/customers/{id}",
            "/api/discounts/{id}",
            "/api/employees/{id}",
            "/api/models/{id}",
            "/api/ourteams/{id}",

            "/api/users/{id}",
            "/api/creditcard/{id}",
    };
    private static final String[] ADMIN_USER_DELETE_URL = {"/api/rentals/{id}",
    };

    private static final String[] PATCH_WHITE_LIST_URL = {"/api/users/changepassword",

    };
    private static final String[] ADMIN_PATCH_URL = {
            "/api/users",
            "api/rentals/vehicle-delivery",

    };
    private static final String[] ADMIN_USER_PATCH_URL = {
            "/api/cars/isactive/{id}",
            "/api/users/isactive/{id}",
            "/api/users/updatelocation/{id}",
            "/api/customers//driverlicence",
            "/api/accountsettingsandimage/{id}",

    };



    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserManager userService;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_ADMIN_URL).hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, GET_USER_ADMIN_URL).hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, ADMIN_PUT_URL).hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, POST_WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.POST, ADMIN_POST_URL).hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, ADMIN_USER_POST_URL).hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ADMIN_DELETE_URL).hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ADMIN_USER_DELETE_URL).hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, PATCH_WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.PATCH, ADMIN_PATCH_URL).hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, ADMIN_USER_PATCH_URL).hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())

                        .anyRequest().authenticated()

                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }
}