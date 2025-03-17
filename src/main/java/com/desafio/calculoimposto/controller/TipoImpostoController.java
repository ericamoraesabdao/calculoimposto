package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.TipoImpostoDto;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.service.TipoImpostoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class TipoImpostoController {

    @Autowired
    private TipoImpostoService tipoImpostoService;

    @GetMapping("/{id}")
    public ResponseEntity<TipoImpostoDto> listaImpostoPorID(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(tipoImpostoService.listaImpostoPorID(id));
    }

    @GetMapping
    public List<TipoImpostoDto> listarimpostos() {
        return tipoImpostoService.listarimpostos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> cadastraImposto(@Valid @RequestBody TipoImposto tipoImpostodto) {
        try {
            TipoImpostoDto novoImposto = tipoImpostoService.cadastraImposto(tipoImpostodto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoImposto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirImposto(@PathVariable Long id) {
        tipoImpostoService.excluirImposto(id);
        return ResponseEntity.noContent().build();
    }
}