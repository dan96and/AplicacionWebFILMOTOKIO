package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.request.UserRequest;
import com.example.aplicacionwebfilmotokio.service.ApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String urlBase = "http://localhost:8090";

    @Override
    public String loginApi(String username, String password) {

        UserRequest userRequest = UserRequest.builder().username(username).password(password).build();

        return restTemplate.postForObject(urlBase + "/auth/login", userRequest, String.class);
    }
}
