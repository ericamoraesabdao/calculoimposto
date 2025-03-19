package com.desafio.calculoimposto.exception;

import com.desafio.calculoimposto.dto.ErrorDto;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleUniqueConstraintViolationException() {
        UniqueConstraintViolationException exception = new UniqueConstraintViolationException("Unique constraint violated");
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleUniqueConstraintViolationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Unique constraint violated", response.getBody().getErro());
    }

    @Test
    void testHandleInvalidJson() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Invalid JSON");
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleInvalidJson(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos", response.getBody().getErro());
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().getErro());
    }

    @Test
    void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument");
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Illegal argument", response.getBody().getErro());
    }

    @Test
    void testHandleUnauthorized() {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleUnauthorized(mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Usuário não autorizado ou token expirou", response.getBody().getErro());
    }

    @Test
    void testHandleJwtException() {
        JwtException exception = new JwtException("Invalid token");
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleJwtException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Token inválido ou expirado", response.getBody().getErro());
    }

    @Test
    void testHandleGenericException() {
        Exception exception = new Exception("Generic error");
        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Generic error", response.getBody().getErro());
    }
}