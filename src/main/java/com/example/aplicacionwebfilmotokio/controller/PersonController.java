package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.domain.Person;
import com.example.aplicacionwebfilmotokio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/new-person")
    ModelAndView newPerson(@ModelAttribute("person") Person person, ModelAndView modelAndView) {
        if (personService.newPerson(person)) {
            modelAndView.addObject("registerPersonSuccesfull", "Persona creada correctamente.");
        } else {
            throw new RuntimeException("Error al crear la persona. Intentelo de nuevo más tarde");
        }

        modelAndView.setViewName("new-person");

        return modelAndView;
    }
}
