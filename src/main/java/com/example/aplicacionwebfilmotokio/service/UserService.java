package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.domain.User;

import java.time.LocalDateTime;

public interface UserService {
    Boolean registerUser(User user);
    User findUserByUsername(String username);
    void updateLastLogin(LocalDateTime lastLogin, String name);
}
