package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.RegisterUserDto;
import com.desafio.calculoimposto.model.Role;
import com.desafio.calculoimposto.model.User;
import com.desafio.calculoimposto.repository.RoleRepository;
import com.desafio.calculoimposto.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encodePassword(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Transactional
    public Long registerUser(RegisterUserDto registerUserDto) {
        if (userRepository.existsByUsername(registerUserDto.getUsername())) {
            throw new IllegalArgumentException("Usuário já registrado");
        }

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));

        Set<Role> roleEntities = new HashSet<>();
        String roleName = "ROLE_" + registerUserDto.getRole().toUpperCase();
        Optional<Role> optionalRole = roleRepository.findByName(roleName);

        Role role;

        if (optionalRole.isEmpty()) {
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        } else {
            role = optionalRole.get();
        }

        roleEntities.add(role);

        user.setRoles(roleEntities);
        userRepository.save(user);
        return user.getId();
    }
}