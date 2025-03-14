package com.desafio.calculoimposto.dto;

import lombok.Data;

@Data
public class TipoImpostoDto {
    private Long id;
    private String nome;
    private String descricao;
    private Double aliquota;

    public TipoImpostoDto(Long id, String nome, String descricao, Double aliquota) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }
}
