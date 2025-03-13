package com.desafio.calculoimposto.repository;


import com.desafio.calculoimposto.model.Role;
import com.desafio.calculoimposto.model.Roles;
import com.desafio.calculoimposto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
