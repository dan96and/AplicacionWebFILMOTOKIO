package com.example.aplicacionwebfilmotokio.controller;

import org.springframework.ui.Model;
import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.Person;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.enums.TypePersonEnum;
import com.example.aplicacionwebfilmotokio.service.FilmImageService;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import com.example.aplicacionwebfilmotokio.service.PersonService;
import com.example.aplicacionwebfilmotokio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FilmController {

    @Autowired
    FilmImageService filmImageService;
    @Autowired
    FilmService filmService;
    @Autowired
    UserService userService;
    @Autowired
    PersonService personService;

    @PostMapping("/new-film")
    ModelAndView newFilm(@ModelAttribute("film") Film film, @RequestParam("cartel") MultipartFile archive, ModelAndView modelAndView, Model model) {

        UserDetails userDetailsAuthenticate = SecurityConfig.getAuthenticatedUserDetails();

        User user = userService.findUserByUsername(userDetailsAuthenticate.getUsername());

        film = filmImageService.saveImage(archive, film);

        filmService.saveFilm(film, user);

        List<Person> listPerson = personService.allPerson();

        model.addAttribute("director", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.DIRECTOR)));
        model.addAttribute("musician", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.MUSICO)));
        model.addAttribute("screenwriter", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.GUIONISTA)));
        model.addAttribute("photographer", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.FOTOGRAFO)));
        model.addAttribute("actor", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.ACTOR)));

        modelAndView.addObject("newFilmSuccesfull", "Se ha creado la película");

        modelAndView.setViewName("new-film");
        return modelAndView;
    }
}
