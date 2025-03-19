package com.desafio.calculoimposto.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomAccessDeniedHandlerTest {

    private CustomAccessDeniedHandler customAccessDeniedHandler;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private AccessDeniedException mockAccessDeniedException;

    @BeforeEach
    void setUp() {
        customAccessDeniedHandler = new CustomAccessDeniedHandler();
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockAccessDeniedException = mock(AccessDeniedException.class);
    }

    @Test
    void testHandle_ShouldSetUnauthorizedStatusAndReturnErrorMessage() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(mockResponse.getWriter()).thenReturn(printWriter);

        customAccessDeniedHandler.handle(mockRequest, mockResponse, mockAccessDeniedException);

        verify(mockResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(mockResponse).setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> expectedErrorDetails = Map.of("error", "Usuario nao autorizado ou token expirou");
        String expectedJsonResponse = objectMapper.writeValueAsString(expectedErrorDetails);

        assertEquals(expectedJsonResponse, stringWriter.toString());
    }
}