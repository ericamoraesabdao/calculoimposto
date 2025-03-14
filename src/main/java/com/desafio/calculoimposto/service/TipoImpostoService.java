package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.TipoImpostoDto;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.repository.TipoImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoImpostoService {
    @Autowired
    private TipoImpostoRepository tipoImpostoRepository;

    public List<TipoImpostoDto> listarimpostos() {
        List<TipoImposto> tipoImpostos = tipoImpostoRepository.findAll();

        return tipoImpostos.stream()
                .map(tipoImposto -> new TipoImpostoDto(tipoImposto.getId(), tipoImposto.getNome(), tipoImposto.getDescricao(), tipoImposto.getAliquota()))
                .collect(Collectors.toList());
    }
}
