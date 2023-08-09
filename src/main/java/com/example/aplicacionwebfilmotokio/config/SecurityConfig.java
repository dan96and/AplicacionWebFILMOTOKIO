package com.example.aplicacionwebfilmotokio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureMvcSecurity(HttpSecurity http) throws Exception {

        http
                .authorizeRequests((request) -> request
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/login", "/new-user", "/registration", "/index", "/new-person", "/new-film", "/search-film").permitAll())

                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index"))

                .logout((logout) -> logout
                        .logoutSuccessUrl("/login"));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static UserDetails getAuthenticatedUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) principal;
            }
        }
        throw new RuntimeException("Necesitas estar logueado para poder realizar esta acción");
    }
}
