package com.geekbrains.spring.market.geekmarket.services;

import com.geekbrains.spring.market.geekmarket.entities.Role;
import com.geekbrains.spring.market.geekmarket.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}