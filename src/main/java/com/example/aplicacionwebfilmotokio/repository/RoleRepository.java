package com.example.aplicacionwebfilmotokio.repository;

import com.example.aplicacionwebfilmotokio.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
}
