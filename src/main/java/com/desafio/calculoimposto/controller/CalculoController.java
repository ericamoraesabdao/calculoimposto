package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.CalculoDto;
import com.desafio.calculoimposto.dto.CalculoResponseDto;
import com.desafio.calculoimposto.dto.ErrorDto;
import com.desafio.calculoimposto.service.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculo")
public class CalculoController {
    @Autowired
    CalculoService calculoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> calcularImposto(@RequestBody CalculoDto calculoDto) {
        try {
            CalculoResponseDto resultado = calculoService.calculoImposto(calculoDto);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            ErrorDto error = new ErrorDto(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
