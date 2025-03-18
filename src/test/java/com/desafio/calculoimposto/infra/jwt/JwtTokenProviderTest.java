package com.desafio.calculoimposto.infra.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider();
    }

    @Test
    void testGenerateToken() {
        // Mock do objeto Authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");

        // Gera o token
        String token = jwtTokenProvider.generateToken(authentication);

        // Verifica se o token não é nulo ou vazio
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testGetUsernameFromValidToken() {
        // Mock do objeto Authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");

        // Gera o token
        String token = jwtTokenProvider.generateToken(authentication);

        // Obtém o nome do usuário a partir do token
        String username = jwtTokenProvider.getUsername(token);

        // Verifica se o nome do usuário é o esperado
        assertEquals("testUser", username);
    }

    @Test
    void testValidateTokenWithValidToken() {
        // Mock do objeto Authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");

        // Gera o token
        String token = jwtTokenProvider.generateToken(authentication);

        // Valida o token
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Verifica se o token é válido
        assertTrue(isValid);
    }

    @Test
    void testValidateTokenWithExpiredToken() {
        // Configura um token com expiração curta
        jwtTokenProvider = new JwtTokenProvider() {
            @Override
            public String generateToken(Authentication authentication) {
                String username = authentication.getName();
                Date currentDate = new Date();
                Date expireDate = new Date(currentDate.getTime() - 1000); // Token já expirado

                return Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(currentDate)
                        .setExpiration(expireDate)
                        .signWith(SignatureAlgorithm.HS256, key())
                        .compact();
            }
        };

        // Mock do objeto Authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");

        // Gera o token expirado
        String token = jwtTokenProvider.generateToken(authentication);

        // Valida o token
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Verifica se o token é inválido
        assertFalse(isValid);
    }

    @Test
    void testValidateTokenWithMalformedToken() {
        // Token malformado
        String malformedToken = "malformed.token.value";

        // Valida o token
        boolean isValid = jwtTokenProvider.validateToken(malformedToken);

        // Verifica se o token é inválido
        assertFalse(isValid);
    }

    @Test
    void testValidateTokenWithSignatureException() {
        // Gera uma chave segura com pelo menos 256 bits
        byte[] invalidSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        // Token com assinatura inválida
        String invalidSignatureToken = Jwts.builder()
                .setSubject("testUser")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, invalidSecretKey) // Usa a chave segura
                .compact();

        // Valida o token
        boolean isValid = jwtTokenProvider.validateToken(invalidSignatureToken);

        // Verifica se o token é inválido
        assertFalse(isValid);
    }
}