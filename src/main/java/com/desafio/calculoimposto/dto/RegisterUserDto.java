package com.desafio.calculoimposto.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String username;
    private String password;
    private String role;
}