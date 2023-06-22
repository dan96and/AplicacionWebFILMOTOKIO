package com.example.aplicacionwebfilmotokio.repository;

import com.example.aplicacionwebfilmotokio.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
