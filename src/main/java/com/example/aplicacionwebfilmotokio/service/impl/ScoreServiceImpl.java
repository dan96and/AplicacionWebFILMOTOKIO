package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Score;
import com.example.aplicacionwebfilmotokio.repository.ScoreRepository;
import com.example.aplicacionwebfilmotokio.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    @Override
    public Boolean newScore(Score score) {
        try {
            scoreRepository.save(score);
            return true;
        } catch (Exception e) {
            log.warn("Error to save the score in the database");
            return false;
        }
    }
}
