package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.dto.ReviewDTO;
import com.example.aplicacionwebfilmotokio.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String newReview(ReviewDTO reviewDTO) {

        return restTemplate.postForObject("http://localhost:8090/new-review", reviewDTO, String.class);
    }

    @Override
    public int getSizeReviewsByUserIdAndFilmId(Long userId, Long filmId) {
        return restTemplate.getForObject("http://localhost:8090/getReviews?userId=" + userId + "&filmId=" + filmId, Integer.class);
    }
}
