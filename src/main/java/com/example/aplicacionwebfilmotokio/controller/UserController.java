package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.service.RoleService;
import com.example.aplicacionwebfilmotokio.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/new-user")
    public ModelAndView newUser(@ModelAttribute("user") @DateTimeFormat(pattern = "yyyy-MM-dd") User user, @RequestParam(value = "role", defaultValue = "USER") String roleName, ModelAndView modelAndView) {

        user.setRoles(roleService.getRoleByName(roleName));

        if (userService.registerUser(user)) {
            modelAndView.addObject("registerUserSuccesfull", "Usuario creado correctamente.");
        } else {
            throw new RuntimeException("Error al crear el usuario. Vuelva a intentarlo más tarde");
        }

        modelAndView.setViewName("registration");

        return modelAndView;
    }
}
