package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.TipoImpostoDto;
import com.desafio.calculoimposto.exception.ResourceNotFoundException;
import com.desafio.calculoimposto.exception.UniqueConstraintViolationException;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.repository.TipoImpostoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public TipoImpostoDto cadastraImposto(@Valid TipoImposto tipoImposto) {
        try {
            tipoImpostoRepository.save(tipoImposto);
            return new TipoImpostoDto(tipoImposto.getId(), tipoImposto.getNome(), tipoImposto.getDescricao(), tipoImposto.getAliquota());
        } catch (DataIntegrityViolationException e) {
            throw new UniqueConstraintViolationException("O imposto já existe");
        }
    }

    public TipoImpostoDto listaImpostoPorID(Long id) {
        TipoImposto entity = tipoImpostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imposto não encontrado!"));
        return new TipoImpostoDto(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getAliquota());
    }

    public boolean impostoExiste(Long id) {
        return tipoImpostoRepository.existsById(id);
    }

    public void excluirImposto(Long id) {
        TipoImposto existeImposto = tipoImpostoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O imposto não foi deletado pois o Id não foi encontrado."));
        tipoImpostoRepository.deleteById(id);
    }
}
