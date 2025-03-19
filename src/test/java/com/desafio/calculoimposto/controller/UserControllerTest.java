package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.*;
import com.desafio.calculoimposto.exception.ResourceNotFoundException;
import com.desafio.calculoimposto.service.AuthService;
import com.desafio.calculoimposto.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("password");

        String token = "mocked-jwt-token";
        when(authService.login(loginDto)).thenReturn(token);

        ResponseEntity<?> response = userController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        AuthResponseDto responseBody = (AuthResponseDto) response.getBody();
        assertEquals(token, responseBody.getAccessToken());
        verify(authService, times(1)).login(loginDto);
    }

    @Test
    void testLogin_NotFound() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("invaliduser");
        loginDto.setPassword("password");

        String errorMessage = "User not found";
        when(authService.login(loginDto)).thenThrow(new ResourceNotFoundException(errorMessage));

        ResponseEntity<?> response = userController.login(loginDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorDto responseBody = (ErrorDto) response.getBody();
        assertEquals(errorMessage, responseBody.getErro());
        verify(authService, times(1)).login(loginDto);
    }

    @Test
    void testRegisterUser_Success() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername("newuser");
        registerUserDto.setPassword("password");
        registerUserDto.setRole("USER");

        Long generatedId = 1L;
        when(userService.registerUser(registerUserDto)).thenReturn(generatedId);

        ResponseEntity<RegisterUserResponseDto> response = userController.registerUser(registerUserDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        RegisterUserResponseDto responseBody = response.getBody();
        assertEquals(generatedId, responseBody.getId());
        assertEquals(registerUserDto.getUsername(), responseBody.getUsername());
        assertEquals(registerUserDto.getRole(), responseBody.getRole());
        verify(userService, times(1)).registerUser(registerUserDto);
    }
}