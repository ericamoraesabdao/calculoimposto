package com.desafio.calculoimposto.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceNotFoundExceptionTest {
    @Test
    void testExceptionMessage() {
        String expectedMessage = "Resource not found";

        ResourceNotFoundException exception = new ResourceNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected message");
    }
}