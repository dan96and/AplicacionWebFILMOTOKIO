package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.config.ConfigPropertiesApi;
import com.example.aplicacionwebfilmotokio.request.UserRequest;
import com.example.aplicacionwebfilmotokio.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    @Qualifier("configPropertiesApi")
    private ConfigPropertiesApi api;

    @Override
    public String loginApi(String username, String password) {

        UserRequest userRequest = UserRequest.builder().username(username).password(password).build();

        return restTemplate.postForObject(api.getUrl() + "/auth/login", userRequest, String.class);
    }
}
