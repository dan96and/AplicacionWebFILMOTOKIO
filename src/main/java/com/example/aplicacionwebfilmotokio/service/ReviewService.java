package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.dto.ReviewDTO;

public interface ReviewService {
    String newReview(ReviewDTO reviewDTO);

    int getSizeReviewsByUserIdAndFilmId(Long userId, Long filmId);
}