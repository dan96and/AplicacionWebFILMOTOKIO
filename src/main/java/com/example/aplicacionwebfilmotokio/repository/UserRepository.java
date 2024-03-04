package com.example.aplicacionwebfilmotokio.repository;

import com.example.aplicacionwebfilmotokio.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User u set u.token = :token where u.username = :username")
    void updateToken(@Param("username") String username, @Param("token") String token);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.lastLogin = :lastLogin where u.username= :name")
    void updateLastLogin(@Param("lastLogin") LocalDateTime lastLogin, @Param("name") String name);

    @Query("select u.token from User u where u.username =:username")
    String getTokenByUserUsername(@Param("username") String username);
}
