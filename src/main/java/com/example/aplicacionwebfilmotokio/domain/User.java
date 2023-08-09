package com.example.aplicacionwebfilmotokio.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    private String image;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<Score> filmScores;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;

    @OneToMany(mappedBy = "user")
    private List<Review> filmsReview;

    @OneToMany(mappedBy = "user")
    private List<Film> films;

}