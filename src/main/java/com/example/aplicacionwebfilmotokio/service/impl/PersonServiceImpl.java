package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Person;
import com.example.aplicacionwebfilmotokio.repository.PersonRepository;
import com.example.aplicacionwebfilmotokio.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Boolean newPerson(Person person) {

        try {
            log.info("Saving person in the database");
            personRepository.save(person);
        } catch (Exception e) {
            log.warn("Error when registering the person in the database");
            return false;
        }

        return true;
    }

    @Override
    public List<Person> allPerson() {
        List<Person> listPerson = null;
        try {
            log.info("Return people from the database");
            listPerson = personRepository.findAll();
        } catch (Exception e) {
            log.warn("Error returning persons from the database");
            return null;
        }
        return listPerson;
    }
}
