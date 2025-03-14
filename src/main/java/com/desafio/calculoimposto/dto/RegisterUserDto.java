package com.desafio.calculoimposto.dto;

import com.desafio.calculoimposto.model.Roles;
import lombok.Data;
import java.util.Set;

@Data
public class RegisterUserDto {
    private String username;
    private String password;
    private String role;
}