package com.example.aplicacionwebfilmotokio.service;

import com.example.aplicacionwebfilmotokio.domain.Person;

import java.util.List;

public interface PersonService {

    Boolean newPerson(Person person);

    List<Person> allPerson();
}
