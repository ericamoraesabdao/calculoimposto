package com.desafio.calculoimposto.dto;

import lombok.Data;

@Data
public class ErrorDto {
    String erro;

    public ErrorDto(String erro) {
        this.erro = erro;
    }
}
