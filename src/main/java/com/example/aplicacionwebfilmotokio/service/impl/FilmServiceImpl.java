package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.domain.User;
import com.example.aplicacionwebfilmotokio.repository.FilmRepository;
import com.example.aplicacionwebfilmotokio.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Override
    public Boolean saveFilm(Film film, User user) {

        film.setMigrate(false);
        film.setUser(user);

        try {
            log.info("Saving de film in the database");
            filmRepository.save(film);
        } catch (Exception e) {
            log.warn("Error save the film in the database");
            return false;
        }

        return true;
    }
}
