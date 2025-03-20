package com.desafio.calculoimposto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
class CalculoimpostoApplicationTest {
    @Test
    void contextLoads() {
    }

    @Test
    public void testMainMethod() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(CalculoimpostoApplication.class, new String[]{}))
                    .thenReturn(null);

            CalculoimpostoApplication.main(new String[]{});

            mockedSpringApplication.verify(() -> SpringApplication.run(CalculoimpostoApplication.class, new String[]{}));
        }
    }
}
