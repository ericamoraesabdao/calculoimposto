package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.*;
import com.desafio.calculoimposto.exception.ResourceNotFoundException;
import com.desafio.calculoimposto.service.AuthService;
import com.desafio.calculoimposto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.login(loginDto);

            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setAccessToken(token);

            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorDto error = new ErrorDto(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        Long generatedId = userService.registerUser(registerUserDto);

        RegisterUserResponseDto response = new RegisterUserResponseDto();
        response.setId(generatedId);
        response.setUsername(registerUserDto.getUsername());
        response.setRole(registerUserDto.getRole());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}