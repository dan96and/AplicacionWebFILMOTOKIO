package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.Score;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.service.*;
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
        Score score = Score.builder()
                .value(Integer.valueOf(value))
                .user(user)
                .film(film)
                .build();

        scoreService.newScore(score);

        return "redirect:/film/" + id+"/succesfull";
    }
}
