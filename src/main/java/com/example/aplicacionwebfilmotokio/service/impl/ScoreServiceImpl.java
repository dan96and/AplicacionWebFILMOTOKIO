package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Score;
import com.example.aplicacionwebfilmotokio.repository.ScoreRepository;
import com.example.aplicacionwebfilmotokio.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public Boolean newScore(Score score) {
        scoreRepository.save(score);
        return true;
    }
}
