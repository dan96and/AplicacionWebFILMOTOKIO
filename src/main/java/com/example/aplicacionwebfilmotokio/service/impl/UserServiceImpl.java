package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Role;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.repository.RoleRepository;
import com.example.aplicacionwebfilmotokio.repository.UserRepository;
import com.example.aplicacionwebfilmotokio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    BCryptPasswordEncoder bCryp = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Boolean registerUser(User user) {

        user.setPassword(bCryp.encode(user.getPassword()));
        user.setCreationDate(LocalDate.now());
        user.setActive(true);

        Role role = roleRepository.getRoleByName("USER");
        user.setRoles(role);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error when registering the user in the database");
            return false;
        }

        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
