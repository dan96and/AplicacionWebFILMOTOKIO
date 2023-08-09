package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.User;

public interface FilmService {
    Boolean saveFilm(Film film, User user);
}
