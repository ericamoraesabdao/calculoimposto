package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorDtoTest {
    @Test
    public void testHashCode() {
        ErrorDto dto1 = new ErrorDto("Erro 1");
        ErrorDto dto2 = new ErrorDto("Erro 1");
        ErrorDto dto3 = new ErrorDto("Erro 2");

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should be equal for equal objects");
        assertFalse(dto1.hashCode() == dto3.hashCode(), "Hash codes should not be equal for different objects");
    }

    @Test
    public void testEquals() {
        ErrorDto dto1 = new ErrorDto("Erro 1");
        ErrorDto dto2 = new ErrorDto("Erro 1");
        ErrorDto dto3 = new ErrorDto("Erro 2");

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        ErrorDto dto = new ErrorDto("Erro 1");

        String expected = "ErrorDto(erro=Erro 1)";
        String actual = dto.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        ErrorDto dto1 = new ErrorDto("Erro 1");
        ErrorDto dto2 = new ErrorDto("Erro 1");
        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}