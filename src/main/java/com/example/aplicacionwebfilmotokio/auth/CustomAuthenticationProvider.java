package com.example.aplicacionwebfilmotokio.auth;

import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.service.ApiService;
import com.example.aplicacionwebfilmotokio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ApiService apiService;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userService.findUserByUsername(authentication.getName());

        if (user == null) {
            throw new UsernameNotFoundException("Usuario/contraseña incorrectos");
        }

        List<GrantedAuthority> authority = List.of(new SimpleGrantedAuthority(user.getRoles().getName()));

        String token = apiService.loginApi(authentication.getName(), authentication.getCredentials().toString());
        token = token.substring(10,135);
        userService.updateToken(authentication.getName(), token);

        userService.updateLastLogin(LocalDateTime.now(), authentication.getName());

        Authentication userAuthenticated = new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        true, true, true, true,
                        authority),
                null,
                authority);

        return userAuthenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}