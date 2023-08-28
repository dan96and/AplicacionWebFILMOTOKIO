package com.example.aplicacionwebfilmotokio.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {

    private Long id;

    private String title;

    private String textReview;

    private LocalDate date;

    private Long userId;

    private Long filmId;
}
