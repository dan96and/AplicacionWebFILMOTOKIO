package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.repository.FilmRepository;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Override
    public Boolean saveFilm(Film film, User user) {

        try {
            log.info("Saving de film in the database");
            film.setMigrate(false);
            film.setUser(user);
            filmRepository.save(film);
        } catch (Exception e) {
            log.warn("Error save the film in the database");
            return false;
        }

        return true;
    }

    @Override
    public List<Film> searchFilmsByTitle(String title) {
        try {
            return filmRepository.searchFilmsByTitleContaining(title);
        } catch (Exception e) {
            log.warn("Error show the films by title in the database");
            return null;
        }
    }

    @Override
    public List<Film> searchAllFilms() {
        try {
            return filmRepository.findAll();
        } catch (Exception e) {
            log.warn("Error show the all films in the database");
            return null;
        }
    }

    @Override
    public Film searchFilmById(Long id) {
        try {
            return filmRepository.searchFilmsById(id);
        } catch (Exception e) {
            log.warn("Error search the film by id in the database.");
            return null;
        }
    }
}
