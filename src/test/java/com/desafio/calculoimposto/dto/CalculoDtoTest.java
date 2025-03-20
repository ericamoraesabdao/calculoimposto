package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculoDtoTest {
    @Test
    public void testHashCode() {
        CalculoDto dto1 = new CalculoDto();
        dto1.setTipoImpostoId(1L);
        dto1.setValorBase(100.0);

        CalculoDto dto2 = new CalculoDto();
        dto2.setTipoImpostoId(1L);
        dto2.setValorBase(100.0);

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testEquals() {
        CalculoDto dto1 = new CalculoDto();
        dto1.setTipoImpostoId(1L);
        dto1.setValorBase(100.0);

        CalculoDto dto2 = new CalculoDto();
        dto2.setTipoImpostoId(1L);
        dto2.setValorBase(100.0);

        CalculoDto dto3 = new CalculoDto();
        dto3.setTipoImpostoId(2L);
        dto3.setValorBase(200.0);

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        CalculoDto dto = new CalculoDto();
        dto.setTipoImpostoId(1L);
        dto.setValorBase(100.0);

        String expected = "CalculoDto(tipoImpostoId=1, valorBase=100.0)";
        String actual = dto.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        CalculoDto dto1 = new CalculoDto();
        dto1.setTipoImpostoId(1L);
        dto1.setValorBase(100.0);

        CalculoDto dto2 = new CalculoDto();
        dto2.setTipoImpostoId(1L);
        dto2.setValorBase(100.0);

        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}