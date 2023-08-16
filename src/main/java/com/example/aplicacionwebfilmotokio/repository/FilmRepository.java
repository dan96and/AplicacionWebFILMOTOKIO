package com.example.aplicacionwebfilmotokio.repository;

import com.example.aplicacionwebfilmotokio.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> searchFilmsByTitleContaining(String title);
    Film searchFilmsById(Long id);
    List<Film> findAll ();
}
