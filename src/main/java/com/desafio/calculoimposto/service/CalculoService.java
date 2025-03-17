package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.CalculoDto;
import com.desafio.calculoimposto.dto.CalculoResponseDto;
import com.desafio.calculoimposto.exception.ResourceNotFoundException;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.repository.TipoImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculoService {
    @Autowired
    TipoImpostoRepository tipoImpostoRepository;

    public CalculoResponseDto calculoImposto(CalculoDto calculoDto){

        TipoImposto tipoImposto = (TipoImposto) tipoImpostoRepository.findById(calculoDto.getTipoImpostoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de imposto não encontrado!"));

            Double aliquota = tipoImposto.getAliquota();
            Double valorImposto = (calculoDto.getValorBase() * aliquota) / 100;

            return new CalculoResponseDto(tipoImposto.getNome(), calculoDto.getValorBase(), aliquota, valorImposto);
    }
}
