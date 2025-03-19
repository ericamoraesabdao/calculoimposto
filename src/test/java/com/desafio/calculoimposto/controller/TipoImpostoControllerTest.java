package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.TipoImpostoDto;
import com.desafio.calculoimposto.model.TipoImposto;
import com.desafio.calculoimposto.service.TipoImpostoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TipoImpostoControllerTest {
    @InjectMocks
    private TipoImpostoController tipoImpostoController;

    @Mock
    private TipoImpostoService tipoImpostoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListaImpostoPorID_Success() {
        Long id = 1L;
        TipoImpostoDto tipoImpostoDto = mock(TipoImpostoDto.class);
        when(tipoImpostoService.listaImpostoPorID(id)).thenReturn(tipoImpostoDto);

        ResponseEntity<TipoImpostoDto> response = tipoImpostoController.listaImpostoPorID(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tipoImpostoDto, response.getBody());
        verify(tipoImpostoService, times(1)).listaImpostoPorID(id);
    }

    @Test
    void testListarImpostos_Success() {
        List<TipoImpostoDto> tipoImpostoDtos = Arrays.asList(mock(TipoImpostoDto.class), mock(TipoImpostoDto.class));
        when(tipoImpostoService.listarimpostos()).thenReturn(tipoImpostoDtos);

        List<TipoImpostoDto> response = tipoImpostoController.listarimpostos();

        assertEquals(tipoImpostoDtos.size(), response.size());
        assertEquals(tipoImpostoDtos, response);
        verify(tipoImpostoService, times(1)).listarimpostos();
    }

    @Test
    void testCadastraImposto_Success() {
        TipoImposto tipoImposto = new TipoImposto();
        TipoImpostoDto tipoImpostoDto = mock(TipoImpostoDto.class);
        when(tipoImpostoService.cadastraImposto(tipoImposto)).thenReturn(tipoImpostoDto);

        ResponseEntity<?> response = tipoImpostoController.cadastraImposto(tipoImposto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(tipoImpostoDto, response.getBody());
        verify(tipoImpostoService, times(1)).cadastraImposto(tipoImposto);
    }

    @Test
    void testCadastraImposto_BadRequest() {
        TipoImposto tipoImposto = new TipoImposto();
        String errorMessage = "Invalid input";
        when(tipoImpostoService.cadastraImposto(tipoImposto)).thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<?> response = tipoImpostoController.cadastraImposto(tipoImposto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody());
        verify(tipoImpostoService, times(1)).cadastraImposto(tipoImposto);
    }

    @Test
    void testExcluirImposto_Success() {
        Long id = 1L;
        doNothing().when(tipoImpostoService).excluirImposto(id);

        ResponseEntity<Void> response = tipoImpostoController.excluirImposto(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(tipoImpostoService, times(1)).excluirImposto(id);
    }
}