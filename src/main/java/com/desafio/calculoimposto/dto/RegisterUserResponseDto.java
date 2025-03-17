package com.desafio.calculoimposto.dto;

import lombok.Data;

@Data
public class RegisterUserResponseDto {
    private Long id;
    private String username;
    private String role;
}
