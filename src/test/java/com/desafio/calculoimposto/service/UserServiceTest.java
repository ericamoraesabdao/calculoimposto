package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.RegisterUserDto;
import com.desafio.calculoimposto.model.Role;
import com.desafio.calculoimposto.model.User;
import com.desafio.calculoimposto.repository.RoleRepository;
import com.desafio.calculoimposto.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Simple() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername("testuser");
        registerUserDto.setPassword("password");
        registerUserDto.setRole("USER");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });

        Long userId = userService.registerUser(registerUserDto);

        assertEquals(1L, userId);
    }

    @Test
    public void testRegisterUser_Success() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername("testuser");
        registerUserDto.setPassword("password");
        registerUserDto.setRole("USER");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        Role newRole = new Role();
        newRole.setName("ROLE_USER");
        when(roleRepository.save(any(Role.class))).thenReturn(newRole);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("encodedPassword");
        savedUser.setRoles(Set.of(newRole));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        Long userId = userService.registerUser(registerUserDto);

        assertEquals(1L, userId);
        verify(userRepository, times(1)).save(any(User.class));
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername("existinguser");
        registerUserDto.setPassword("password");
        registerUserDto.setRole("USER");

        when(userRepository.existsByUsername("existinguser")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(registerUserDto);
        });

        assertEquals("Usuário já registrado", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterUser_RoleAlreadyExists() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername("testuser");
        registerUserDto.setPassword("password");
        registerUserDto.setRole("ADMIN");

        Role existingRole = new Role();
        existingRole.setName("ROLE_ADMIN");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(existingRole));

        User savedUser = new User();
        savedUser.setId(2L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("encodedPassword");
        savedUser.setRoles(Set.of(existingRole));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        Long userId = userService.registerUser(registerUserDto);

        assertEquals(2L, userId);
        verify(roleRepository, never()).save(any(Role.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testEncodePassword() {
        String rawPassword = "myPassword";
        String encodedPassword = "encodedPassword";
        when(bCryptPasswordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        String result = userService.encodePassword(rawPassword);

        assertEquals(encodedPassword, result);
        verify(bCryptPasswordEncoder, times(1)).encode(rawPassword);
    }
}