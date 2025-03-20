package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculoResponseDtoTest {
    @Test
    public void testConstructorAndGetters() {
        String tipoImposto = "ICMS";
        double valorBase = 1000.0;
        double aliquota = 0.18;
        double valorImposto = 180.0;

        CalculoResponseDto dto = new CalculoResponseDto(tipoImposto, valorBase, aliquota, valorImposto);

        assertEquals(tipoImposto, dto.getTipoImposto());
        assertEquals(valorBase, dto.getValorBase());
        assertEquals(aliquota, dto.getAliquota());
        assertEquals(valorImposto, dto.getValorImposto());
    }

    @Test
    public void testSetters() {
        CalculoResponseDto dto = new CalculoResponseDto("", 0.0, 0.0, 0.0);

        dto.setTipoImposto("ISS");
        dto.setValorBase(2000.0);
        dto.setAliquota(0.05);
        dto.setValorImposto(100.0);

        assertEquals("ISS", dto.getTipoImposto());
        assertEquals(2000.0, dto.getValorBase());
        assertEquals(0.05, dto.getAliquota());
        assertEquals(100.0, dto.getValorImposto());
    }

    @Test
    public void testHashCode() {
        CalculoResponseDto dto1 = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);
        CalculoResponseDto dto2 = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testEquals() {
        CalculoResponseDto dto1 = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);
        CalculoResponseDto dto2 = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);
        CalculoResponseDto dto3 = new CalculoResponseDto("ISS", 2000.0, 0.05, 100.0);

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        CalculoResponseDto dto = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);

        String expected = "CalculoResponseDto(tipoImposto=ICMS, valorBase=1000.0, aliquota=0.18, valorImposto=180.0)";
        String actual = dto.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        CalculoResponseDto dto1 = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);
        CalculoResponseDto dto2 = new CalculoResponseDto("ICMS", 1000.0, 0.18, 180.0);
        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}