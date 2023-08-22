
package com.example.aplicacionwebfilmotokio.config;

import com.example.aplicacionwebfilmotokio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain configureMvcSecurity(HttpSecurity http) throws Exception {

        http.authorizeRequests((request) -> request
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/login", "/new-user", "/registration", "/index").permitAll()
                        .requestMatchers("/new-person", "/new-film", "/search-film", "/searched-film/{title}",
                                "/film", "/film/{id}", "/image/{titleImage}", "/add-score/{id}", "/film/{id}/succesfull").hasAnyAuthority("ADMIN", "USER"))

                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                                    userService.updateLastLogin(LocalDateTime.now(), authentication.getName());
                                    response.sendRedirect("/index");
                                }
                        )
                )

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