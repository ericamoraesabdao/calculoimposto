package com.desafio.calculoimposto.infra.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JwtAuthenticationEntryPointTest {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authException;

    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();

        responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    void testCommenceSetsUnauthorizedStatus() throws Exception {
        jwtAuthenticationEntryPoint.commence(request, response, authException);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    void testCommenceSetsContentTypeToJson() throws Exception {
        jwtAuthenticationEntryPoint.commence(request, response, authException);

        verify(response).setContentType("application/json");
    }

    @Test
    void testCommenceWritesErrorDetailsToResponse() throws Exception {
        jwtAuthenticationEntryPoint.commence(request, response, authException);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> errorDetails = objectMapper.readValue(responseWriter.toString(), Map.class);

        assertEquals("Usuario nao autorizado ou token expirou", errorDetails.get("error"));
    }

    @Test
    void testCommenceDoesNotThrowException() {
        try {
            jwtAuthenticationEntryPoint.commence(request, response, authException);
        } catch (Exception e) {
            throw new AssertionError("O método commence lançou uma exceção inesperada", e);
        }
    }
}