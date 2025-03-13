package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.RegisterUserDto;
import com.desafio.calculoimposto.model.Role;
import com.desafio.calculoimposto.model.User;
import com.desafio.calculoimposto.repository.RoleRepository;
import com.desafio.calculoimposto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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

    public void registerUser(RegisterUserDto registerUserDto){
        if (userRepository.existsByUsername(registerUserDto.getUsername())){
            throw new RuntimeException("Usuário registrado");
        }

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));

        Set<Role> roles = registerUserDto.getRoles().stream()
                .map(r -> roleRepository.findByName(r.name())
                        .orElseThrow(() -> new RuntimeException("Role não encontrada: " + r.name())))
                .collect(Collectors.toSet());
        roleRepository.saveAll(roles);

        user.setRoles(roles);
        userRepository.save(user);
    }
}