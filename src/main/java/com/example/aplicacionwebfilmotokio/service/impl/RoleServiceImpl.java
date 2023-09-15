package com.example.aplicacionwebfilmotokio.service.impl;

import com.example.aplicacionwebfilmotokio.domain.Role;
import com.example.aplicacionwebfilmotokio.repository.RoleRepository;
import com.example.aplicacionwebfilmotokio.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.getRoleByName(roleName);
    }
}
