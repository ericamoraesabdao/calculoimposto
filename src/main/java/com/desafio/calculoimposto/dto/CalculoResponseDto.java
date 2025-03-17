package com.desafio.calculoimposto.dto;

import lombok.Data;

@Data
public class CalculoResponseDto {
    private String tipoImposto;
    private double valorBase;
    private double aliquota;
    private double valorImposto;

    public CalculoResponseDto(String tipoImposto, double valorBase, double aliquota, double valorImposto) {
        this.tipoImposto = tipoImposto;
        this.valorBase = valorBase;
        this.aliquota = aliquota;
        this.valorImposto = valorImposto;
    }
}
