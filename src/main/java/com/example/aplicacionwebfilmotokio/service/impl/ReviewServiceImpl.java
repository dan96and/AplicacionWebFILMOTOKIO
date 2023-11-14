package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.config.ConfigPropertiesApi;
import com.example.aplicacionwebfilmotokio.config.SecurityConfig;
import com.example.aplicacionwebfilmotokio.dto.ReviewDTO;
import com.example.aplicacionwebfilmotokio.repository.UserRepository;
import com.example.aplicacionwebfilmotokio.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    @Qualifier("configPropertiesApi")
    private ConfigPropertiesApi api;

    private final UserRepository userRepository;

    @Override
    public String newReview(ReviewDTO reviewDTO) {

        HttpEntity<ReviewDTO> requestEntity = new HttpEntity<>(reviewDTO, getHeaderWithToken());

        return restTemplate.postForObject(api.getUrl() + "/new-review", requestEntity, String.class);
    }

    @Override
    public List<ReviewDTO> getReviews(Long filmId) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl( api.getUrl() + "/getReviews")
                .queryParam("filmId", filmId);

        HttpEntity<String> requestEntity = new HttpEntity<>(getHeaderWithToken());

        ParameterizedTypeReference<List<ReviewDTO>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<ReviewDTO>> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, responseType);

        return responseEntity.getBody();

    }

    private HttpHeaders getHeaderWithToken() {

        String token = userRepository.getTokenByUserUsername(SecurityConfig.getAuthenticatedUserDetails().getUsername());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        return headers;
    }
}
