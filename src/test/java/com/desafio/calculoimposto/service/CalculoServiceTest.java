package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.CalculoDto;
import com.desafio.calculoimposto.dto.CalculoResponseDto;
import com.desafio.calculoimposto.exception.ResourceNotFoundException;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.repository.TipoImpostoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CalculoServiceTest {

    @Mock
    private TipoImpostoRepository tipoImpostoRepository;

    @InjectMocks
    private CalculoService calculoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculoImpostoSuccess() {
        CalculoDto calculoDto = new CalculoDto();
        calculoDto.setTipoImpostoId(1L);
        calculoDto.setValorBase(1000.0);

        TipoImposto tipoImposto = new TipoImposto(2L, "IPI", "Imposto sobre produtos industrializados", 10.0);
        tipoImposto.setId(1L);
        tipoImposto.setNome("ICMS");
        tipoImposto.setAliquota(18.0);

        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.of(tipoImposto));

        CalculoResponseDto response = calculoService.calculoImposto(calculoDto);

        assertEquals("ICMS", response.getTipoImposto());
        assertEquals(1000.0, response.getValorBase());
        assertEquals(18.0, response.getAliquota());
        assertEquals(180.0, response.getValorImposto());

        verify(tipoImpostoRepository, times(1)).findById(1L);
    }

    @Test
    void testCalculoImpostoTipoImpostoNotFound() {
        CalculoDto calculoDto = new CalculoDto();
        calculoDto.setTipoImpostoId(1L);
        calculoDto.setValorBase(1000.0);

        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> calculoService.calculoImposto(calculoDto));

        verify(tipoImpostoRepository, times(1)).findById(1L);
    }
}