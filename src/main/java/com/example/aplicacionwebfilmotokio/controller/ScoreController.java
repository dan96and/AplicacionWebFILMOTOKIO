package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.Score;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import com.example.aplicacionwebfilmotokio.service.ScoreService;
import com.example.aplicacionwebfilmotokio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScoreController {
    @Autowired
    FilmService filmService;
    @Autowired
    UserService userService;
    @Autowired
    ScoreService scoreService;

    @PostMapping("/add-score/{id}")
    public String addScore(@PathVariable("id") Long id, @RequestParam("number") String value) {

        User user = userService.findUserByUsername(SecurityConfig.getAuthenticatedUserDetails().getUsername());

        Film film = filmService.searchFilmById(id);

        if (film == null) {
            throw new RuntimeException("Error al encontrar la película. Vuelva a intentarlo más tarde");
        }

        Score score = Score.builder()
                .value(Integer.valueOf(value))
                .user(user)
                .film(film)
                .build();

        if (!scoreService.newScore(score)) {
            throw new RuntimeException("Error al añadir el score a la película. Vuelva a intentarlo más tarde");
        }

        return "redirect:/film/" + id + "?message=Score+Succesfull";
    }
}
