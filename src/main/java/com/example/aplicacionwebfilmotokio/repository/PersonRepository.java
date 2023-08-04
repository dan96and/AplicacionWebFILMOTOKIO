package com.example.aplicacionwebfilmotokio.repository;

import com.example.aplicacionwebfilmotokio.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
