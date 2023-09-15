package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    String newReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviews(Long filmId);
}