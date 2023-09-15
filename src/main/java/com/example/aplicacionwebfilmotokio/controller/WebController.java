package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.Person;
import com.example.aplicacionwebfilmotokio.domain.Score;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.dto.ReviewDTO;
import com.example.aplicacionwebfilmotokio.enums.TypePersonEnum;
import com.example.aplicacionwebfilmotokio.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final PersonService personService;
    private final FilmService filmService;
    private final ReviewService reviewService;
    private final UserService userService;

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

        if (listFilm == null) {
            throw new RuntimeException("Error al mostrar las películas. Vuelva a intentarlo más tarde");
        }

        model.addAttribute("films", listFilm);

        return "searched-film";

    }

    @GetMapping("/film/{filmId}")
    public String showFilmById(@PathVariable Long filmId, @RequestParam(name = "message", required = false) String message, Model model) {
        model.addAttribute("review", new ReviewDTO());

        UserDetails userAuth = SecurityConfig.getAuthenticatedUserDetails();

        //Revisar si se ha hecho alguna review o score, para mostrar mensaje por pantalla
        if (message != null) {
            if (message.equals("Review Succesfull")) {
                model.addAttribute("messageSuccesfull", "Critica creada correctamente");
            } else if (message.equals("Score Succesfull")) {
                model.addAttribute("messageSuccesfull", "Puntuación creada correctamente");
            }
        }

        //Mostrar los datos de la pelicula
        Film film = filmService.searchFilmById(filmId);

        if (film == null) {
            throw new RuntimeException("Error al mostrar la pelicula. Intentelo más tarde.");
        }

        model.addAttribute("film", film);
        model.addAttribute("directors", film.getDirectors());
        model.addAttribute("screenwriters", film.getScreenwriters());
        model.addAttribute("actors", film.getActors());
        model.addAttribute("musicians", film.getMusicians());
        model.addAttribute("photographers", film.getPhotographers());

        //Revisar si el usuario ha hecho una critica, para no mostrar el componente
        List<ReviewDTO> reviews = reviewService.getReviews(filmId);

        long idUser = userService.findUserByUsername(userAuth.getUsername()).getId();

        List<ReviewDTO> reviewFilterByUsername = reviews.stream().filter(reviewDTO -> reviewDTO.getUserId() == idUser).toList();

        if (!reviewFilterByUsername.isEmpty()) {
            model.addAttribute("userWithReview", true);
        } else {
            model.addAttribute("userWithReview", false);
        }

        //Revisar si el usuario ha puesto una puntuación, para no mostrar el componente
        List<Score> listScore = film.getScores();
        for (Score score : listScore) {
            if (score.getUser().getUsername().equals(userAuth.getUsername())) {
                model.addAttribute("userWithScore", true);
                break;
            } else {
                model.addAttribute("userWithScore", false);
            }
        }

        //Calcular la media de las puntuaciones
        float scoreMedia = 0;

        if (!listScore.isEmpty()) {

            for (Score score : listScore) {
                scoreMedia = score.getValue() + scoreMedia;
            }

            if (scoreMedia > 0) {
                scoreMedia = scoreMedia / listScore.size();
            }
        }

        model.addAttribute("scoreMedia", scoreMedia);

        //Mostrar las reviews
        List<ReviewDTO> reviewDTOList = reviewService.getReviews(filmId);
        model.addAttribute("reviews", reviewDTOList);

        return "film";
    }
}