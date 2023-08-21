package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Film;
import com.example.aplicacionwebfilmotokio.service.FilmImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FilmImageServiceImpl implements FilmImageService {
    private static final String uploads = "src/main/resources/static/images/cover page/";

    @Override
    public Film saveImage(MultipartFile archive, Film film) {

        try {
            Path pathFinal = Paths.get(uploads + archive.getOriginalFilename());
            Files.write(pathFinal, archive.getBytes());
            film.setPoster(archive.getOriginalFilename());
            return film;
        } catch (IOException e) {
            log.warn("No se ha introducido ninguna imagen");
        }
        return null;
    }

    @Override
    public Resource loadImage(String titleResource) {

        try {
            Path path = Paths.get(uploads + titleResource);
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar la pelicula. Vuelva a intentarlo más tarde.");
        }
    }
}
