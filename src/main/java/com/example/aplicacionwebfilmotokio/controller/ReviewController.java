package com.example.aplicacionwebfilmotokio.controller;

import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.dto.ReviewDTO;
import com.example.aplicacionwebfilmotokio.service.ReviewService;
import com.example.aplicacionwebfilmotokio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping("/new-review/{id}")
    String newReview(@ModelAttribute(value = "review") ReviewDTO review, @PathVariable Long id) {

        Long idUser = userService.findUserByUsername(SecurityConfig.getAuthenticatedUserDetails().getUsername()).getId();

        review.setUserId(idUser);
        review.setFilmId(id);
        review.setDate(LocalDate.now());
        review.setId(null);

        String response = reviewService.newReview(review);

        if (response != null) {
            response = response.replace("\"", "");
        }

        if (!response.equals("CREATED")) {
            throw new RuntimeException("Error al crear la review de la película. Intentelo más tarde.");
        }

        return "redirect:/film/" + id + "?message=Review+Succesfull";
    }
}
