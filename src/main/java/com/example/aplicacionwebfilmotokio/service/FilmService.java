package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.User;

import java.util.List;

public interface FilmService {
    Boolean saveFilm(Film film, User user);

    List<Film> searchFilmsByTitle (String tiltle);
}
