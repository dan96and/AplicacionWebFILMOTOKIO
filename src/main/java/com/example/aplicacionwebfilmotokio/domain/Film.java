package com.example.aplicacionwebfilmotokio.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Film")
@Table(name = "FILM")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer year;

    private Integer duration;

    private String synopsis;

    private String poster;

    private Boolean migrate;

    @Column(name = "date_migrate")
    LocalDate dateMigrate;

    @OneToMany(mappedBy = "film")
    private List<Score> scores;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_person")
    )
    private List<Person> actors;

    @ManyToMany
    @JoinTable(
            name = "film_director",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_director")
    )
    private List<Person> directors;

    @ManyToMany
    @JoinTable(
            name = "film_musician",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_musician")
    )
    private List<Person> musicians;

    @ManyToMany
    @JoinTable(
            name = "film_photographer",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_photographer")
    )
    private List<Person> photographers;

    @ManyToMany
    @JoinTable(
            name = "film_screenwriter",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_screenwriter")
    )
    private List<Person> screenwriters;
}