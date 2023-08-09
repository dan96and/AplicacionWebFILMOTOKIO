package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.service.FilmImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FilmImageServiceImpl implements FilmImageService {

    @Override
    public Film saveImage(MultipartFile archive, Film film) {

        try {
            Path pathFinal = Paths.get("src/main/resources/static/images/cover page/" + archive.getOriginalFilename());
            Files.write(pathFinal, archive.getBytes());
            film.setPoster(pathFinal.toString());
        } catch (IOException e) {
            throw new RuntimeException("No se ha introducido ninguna imagen. Vuelva a intentarlo");
        }

        return film;
    }
}
