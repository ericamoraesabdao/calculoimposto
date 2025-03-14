package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.TipoImpostoDto;
import com.desafio.calculoimposto.service.TipoImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class TipoImpostoController {

    @Autowired
    private TipoImpostoService tipoImpostoService;

    @GetMapping
    public List<TipoImpostoDto> listarimpostos() {
        return tipoImpostoService.listarimpostos();
    }
}
