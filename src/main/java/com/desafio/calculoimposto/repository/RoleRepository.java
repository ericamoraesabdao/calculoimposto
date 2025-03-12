package com.desafio.calculoimposto.repository;


import com.desafio.calculoimposto.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
}
