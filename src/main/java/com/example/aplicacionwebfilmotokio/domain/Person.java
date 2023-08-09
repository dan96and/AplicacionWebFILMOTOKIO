package com.example.aplicacionwebfilmotokio.domain;

import com.example.aplicacionwebfilmotokio.enums.TypePersonEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Person")
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private TypePersonEnum type;

    @ManyToMany(mappedBy = "actors")
    private List<Film> filmsActor;

    @ManyToMany(mappedBy = "directors")
    private List<Film> filmsDirector;

    @ManyToMany(mappedBy = "musicians")
    private List<Film> filmsMusicians;

    @ManyToMany(mappedBy = "photographers")
    private List<Film> filmsPhotographers;

    @ManyToMany(mappedBy = "screenwriters")
    private List<Film> filmsScreenWriters;

}