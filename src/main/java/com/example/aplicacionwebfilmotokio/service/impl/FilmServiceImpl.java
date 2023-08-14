package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.repository.FilmRepository;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

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
            List<Film> listFilmByTitle = filmRepository.searchFilmsByTitleContaining(title);
            return listFilmByTitle;
        } catch (Exception e) {
            log.warn("Error show the films in the database");
        }

        return null;
    }
}
