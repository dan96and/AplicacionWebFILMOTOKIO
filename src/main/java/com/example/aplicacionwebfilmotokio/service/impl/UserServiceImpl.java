package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.repository.UserRepository;
import com.example.aplicacionwebfilmotokio.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryp = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    @Override
    public Boolean registerUser(User user) {

        user.setPassword(bCryp.encode(user.getPassword()));
        user.setCreationDate(LocalDate.now());
        user.setActive(true);

        try {
            log.info("Saving user in the database");
            userRepository.save(user);
        } catch (Exception e) {
            log.warn("Error when registering the user in the database");
            return false;
        }

        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        log.info("Returning user from the database");
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateLastLogin(LocalDateTime lastLogin, String name) {
        userRepository.updateLastLogin(lastLogin, name);
    }

    @Override
    public void updateToken(String name, String token) {
        userRepository.updateToken(name, token);
    }
}
