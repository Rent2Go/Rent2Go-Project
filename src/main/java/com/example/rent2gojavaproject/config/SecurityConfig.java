package com.example.rent2gojavaproject.config;

import com.example.rent2gojavaproject.filter.JwtAuthenticationFilter;
import com.example.rent2gojavaproject.services.concretes.UserManager;
import lombok.RequiredArgsConstructor;
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

            "/swagger-ui.html"};
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
                        .requestMatchers(WHITE_LIST_URL).permitAll()

                        .requestMatchers(HttpMethod.POST, "api/signup", "api/signin","api/users/resetpassword").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/confirm/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/test/users").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "api/users/changePassword").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, "api/v1/test/admins").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "api/cars/add").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "createcar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "api/v1/test/anon").permitAll()
                        .anyRequest().authenticated()

                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
