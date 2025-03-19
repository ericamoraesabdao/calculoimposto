package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.model.Role;
import com.desafio.calculoimposto.model.User;
import com.desafio.calculoimposto.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDatailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDatailsService customUserDatailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        String username = "testuser";
        String password = "password123";
        Role role = new Role();
        role.setName("ROLE_USER");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Collections.singleton(role));

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDatailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        Set<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        assertTrue(authorities.contains("ROLE_USER"));
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        String username = "nonexistentuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> customUserDatailsService.loadUserByUsername(username));
        verify(userRepository, times(1)).findByUsername(username);
    }
}