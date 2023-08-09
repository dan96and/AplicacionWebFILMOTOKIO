package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.domain.Film;
import org.springframework.web.multipart.MultipartFile;

public interface FilmImageService {
    Film saveImage(MultipartFile archive, Film film);
}
