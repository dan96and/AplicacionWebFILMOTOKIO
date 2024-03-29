package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.Person;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.enums.TypePersonEnum;
import com.example.aplicacionwebfilmotokio.service.FilmImageService;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import com.example.aplicacionwebfilmotokio.service.PersonService;
import com.example.aplicacionwebfilmotokio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {

    private final FilmImageService filmImageService;
    private final FilmService filmService;
    private final UserService userService;
    private final PersonService personService;

    @PostMapping("/new-film")
    ModelAndView newFilm(@ModelAttribute("film") Film film, @RequestParam("cartel") MultipartFile archive, ModelAndView modelAndView, Model model) {

        UserDetails userDetailsAuthenticate = SecurityConfig.getAuthenticatedUserDetails();

        User user = userService.findUserByUsername(userDetailsAuthenticate.getUsername());

        film = filmImageService.saveImage(archive, film);

        if (film == null) {
            throw new RuntimeException("No se ha introducido ninguna imagen.");
        }

        if (!filmService.saveFilm(film, user)) {
            throw new RuntimeException("Error al guardar la pelicula. Intentelo más tarde.");
        }

        List<Person> listPerson = personService.allPerson();

        if (listPerson == null) {
            throw new RuntimeException("Error al mostrar la lista de personas. Intentelo más tarde.");
        }

        model.addAttribute("director", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.DIRECTOR)));
        model.addAttribute("musician", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.MUSICO)));
        model.addAttribute("screenwriter", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.GUIONISTA)));
        model.addAttribute("photographer", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.FOTOGRAFO)));
        model.addAttribute("actor", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.ACTOR)));

        modelAndView.addObject("newFilmSuccesfull", "Se ha creado la película");

        modelAndView.setViewName("new-film");
        return modelAndView;
    }

    @PostMapping("/search-film")
    public String searchFilm(@RequestParam String title) {
        if (title.trim().isEmpty()) {
            title = "all";
        }
        return "redirect:/searched-film/" + title;
    }

    @GetMapping("/image/{titleImage}")
    public ResponseEntity<Resource> loadImage(@PathVariable String titleImage) {

        Resource resource = filmImageService.loadImage(titleImage);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}