package com.example.aplicacionwebfilmotokio.repository;

import com.example.aplicacionwebfilmotokio.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> { }
