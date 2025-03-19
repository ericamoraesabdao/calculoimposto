package com.desafio.calculoimposto.controller;

import com.desafio.calculoimposto.dto.CalculoDto;
import com.desafio.calculoimposto.dto.CalculoResponseDto;
import com.desafio.calculoimposto.dto.ErrorDto;
import com.desafio.calculoimposto.service.CalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalculoControllerTest {
    @InjectMocks
    private CalculoController calculoController;

    @Mock
    private CalculoService calculoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalcularImposto_Success() {
        CalculoDto calculoDto = new CalculoDto();
        CalculoResponseDto calculoResponseDto = mock(CalculoResponseDto.class);
        when(calculoService.calculoImposto(calculoDto)).thenReturn(calculoResponseDto);

        ResponseEntity<?> response = calculoController.calcularImposto(calculoDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(calculoResponseDto, response.getBody());
        verify(calculoService, times(1)).calculoImposto(calculoDto);
    }

    @Test
    void testCalcularImposto_BadRequest() {
        CalculoDto calculoDto = new CalculoDto();
        String errorMessage = "Invalid input";
        when(calculoService.calculoImposto(calculoDto)).thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<?> response = calculoController.calcularImposto(calculoDto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals(new ErrorDto(errorMessage), response.getBody());
        verify(calculoService, times(1)).calculoImposto(calculoDto);
    }
}
