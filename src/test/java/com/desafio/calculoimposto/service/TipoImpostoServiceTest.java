package com.desafio.calculoimposto.service;

import com.desafio.calculoimposto.dto.TipoImpostoDto;
import com.desafio.calculoimposto.exception.ResourceNotFoundException;
import com.desafio.calculoimposto.exception.UniqueConstraintViolationException;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.repository.TipoImpostoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoImpostoServiceTest {

    @Mock
    private TipoImpostoRepository tipoImpostoRepository;

    @InjectMocks
    private TipoImpostoService tipoImpostoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarImpostos() {
        TipoImposto imposto1 = new TipoImposto(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        TipoImposto imposto2 = new TipoImposto(2L, "IPI", "Imposto sobre produtos industrializados", 10.0);
        when(tipoImpostoRepository.findAll()).thenReturn(Arrays.asList(imposto1, imposto2));

        List<TipoImpostoDto> result = tipoImpostoService.listarimpostos();

        assertEquals(2, result.size());
        assertEquals("ICMS", result.get(0).getNome());
        assertEquals("IPI", result.get(1).getNome());
        verify(tipoImpostoRepository, times(1)).findAll();
    }

    @Test
    void testCadastraImpostoSuccess() {
        TipoImposto imposto = new TipoImposto(null, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        when(tipoImpostoRepository.save(imposto)).thenReturn(imposto);

        TipoImpostoDto result = tipoImpostoService.cadastraImposto(imposto);

        assertEquals("ICMS", result.getNome());
        assertEquals(18.0, result.getAliquota());
        verify(tipoImpostoRepository, times(1)).save(imposto);
    }

    @Test
    void testCadastraImpostoUniqueConstraintViolation() {
        TipoImposto imposto = new TipoImposto(null, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        when(tipoImpostoRepository.save(imposto)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(UniqueConstraintViolationException.class, () -> tipoImpostoService.cadastraImposto(imposto));
        verify(tipoImpostoRepository, times(1)).save(imposto);
    }

    @Test
    void testListaImpostoPorIDSuccess() {
        TipoImposto imposto = new TipoImposto(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.of(imposto));

        TipoImpostoDto result = tipoImpostoService.listaImpostoPorID(1L);

        assertEquals("ICMS", result.getNome());
        assertEquals(18.0, result.getAliquota());
        verify(tipoImpostoRepository, times(1)).findById(1L);
    }

    @Test
    void testListaImpostoPorIDNotFound() {
        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tipoImpostoService.listaImpostoPorID(1L));
        verify(tipoImpostoRepository, times(1)).findById(1L);
    }

    @Test
    void testImpostoExiste() {
        when(tipoImpostoRepository.existsById(1L)).thenReturn(true);

        boolean result = tipoImpostoService.impostoExiste(1L);

        assertTrue(result);
        verify(tipoImpostoRepository, times(1)).existsById(1L);
    }

    @Test
    void testExcluirImpostoSuccess() {
        TipoImposto tipoImposto = new TipoImposto();
        tipoImposto.setId(1L);
        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.of(tipoImposto));

        tipoImpostoService.excluirImposto(1L);

        verify(tipoImpostoRepository, times(1)).findById(1L);
        verify(tipoImpostoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testExcluirImpostoIdNotFound() {
        when(tipoImpostoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tipoImpostoService.excluirImposto(1L));
        verify(tipoImpostoRepository, times(1)).findById(1L);
        verify(tipoImpostoRepository, never()).deleteById(anyLong());
    }
}