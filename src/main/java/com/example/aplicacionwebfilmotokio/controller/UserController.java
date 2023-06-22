package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new-user")
    public String newUser(@ModelAttribute("user") @DateTimeFormat(pattern = "yyyy-MM-dd") User user, Model model) {

        if (userService.registerUser(user)) {
            model.addAttribute("registerUser", "Usuario creado correctamente");
        } else {
            model.addAttribute("registerUser", "Error al crear el usuario. Vuelva a intentarlo más tarde");
        }

        return "ok";
    }

}
