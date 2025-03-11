package com.desafio.calculoimposto.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserDetailsService {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user") // Nome de usuário padrão
                .password(passwordEncoder.encode("password"))
                .roles("USER") // Defina a role
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}