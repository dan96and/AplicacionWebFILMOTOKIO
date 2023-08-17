package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.Person;
import com.example.aplicacionwebfilmotokio.domain.Score;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.enums.TypePersonEnum;
import com.example.aplicacionwebfilmotokio.service.FilmImageService;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import com.example.aplicacionwebfilmotokio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    PersonService personService;

    @Autowired
    FilmService filmService;

    @Autowired
    FilmImageService filmImageService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/new-person")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "new-person";
    }

    @GetMapping("/new-film")
    public String newFilm(Model model) {

        model.addAttribute("film", new Film());

        List<Person> listPerson = personService.allPerson();

        model.addAttribute("director", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.DIRECTOR)));
        model.addAttribute("musician", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.MUSICO)));
        model.addAttribute("screenwriter", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.GUIONISTA)));
        model.addAttribute("photographer", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.FOTOGRAFO)));
        model.addAttribute("actor", listPerson.stream().filter(person -> person.getType().equals(TypePersonEnum.ACTOR)));

        return "new-film";
    }

    @GetMapping("/search-film")
    public String searchFilm() {
        return "search-film";
    }

    @GetMapping("/searched-film/{title}")
    public String searchedFilm(@PathVariable String title, Model model) {

        List<Film> listFilm;

        if (title.equals("all")) {
            listFilm = filmService.searchAllFilms();
        } else {
            listFilm = filmService.searchFilmsByTitle(title);
        }

        model.addAttribute("films", listFilm);

        return "searched-film";

    }

    @GetMapping("/film/{id}")
    public String showFilmById(@PathVariable Long id, Model model) {
        showFilmPage(id, model);
        return "film";
    }

    @GetMapping("/film/{id}/succesfull")
    public String showFilmByIdCriticSucces(@PathVariable Long id, Model model) {
        model.addAttribute("registerPersonSuccesfull", "La critica se ha agregado correctamente");
        showFilmPage(id, model);
        return "film";
    }

    private void showFilmPage(Long id, Model model) {
        Film film = filmService.searchFilmById(id);

        model.addAttribute("film", film);

        model.addAttribute("directors", film.getDirectors());
        model.addAttribute("screenwriters", film.getScreenwriters());
        model.addAttribute("actors", film.getActors());
        model.addAttribute("musicians", film.getMusicians());
        model.addAttribute("photographers", film.getPhotographers());

        List<Score> listScore = film.getScores();
        for (Score score : listScore) {
            if (score.getUser().getUsername().equals(SecurityConfig.getAuthenticatedUserDetails().getUsername())) {
                model.addAttribute("userWithScore", true);
                break;
            } else {
                model.addAttribute("userWithScore", false);
            }
        }

        float scoreMedia = 0;
        if (!listScore.isEmpty()) {
            for (Score score : listScore) {
                scoreMedia = score.getValue() + scoreMedia;
            }
        }

        if (scoreMedia > 0) {
            scoreMedia = scoreMedia / listScore.size();
        }

        model.addAttribute("scoreMedia", scoreMedia);
    }
}