package com.desafio.calculoimposto.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueConstraintViolationExceptionTest {

    @Test
    void testExceptionMessage() {
        String expectedMessage = "Unique constraint violated";

        UniqueConstraintViolationException exception = new UniqueConstraintViolationException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected message");
    }
}