package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoImpostoDtoTest {
    @Test
    void testConstructorAndGetters() {

        Long expectedId = 1L;
        String expectedNome = "ICMS";
        String expectedDescricao = "Imposto sobre Circulação de Mercadorias e Serviços";
        Double expectedAliquota = 18.0;

        TipoImpostoDto tipoImpostoDto = new TipoImpostoDto(expectedId, expectedNome, expectedDescricao, expectedAliquota);

        assertEquals(expectedId, tipoImpostoDto.getId(), "The id should match the expected value");
        assertEquals(expectedNome, tipoImpostoDto.getNome(), "The nome should match the expected value");
        assertEquals(expectedDescricao, tipoImpostoDto.getDescricao(), "The descricao should match the expected value");
        assertEquals(expectedAliquota, tipoImpostoDto.getAliquota(), "The aliquota should match the expected value");
    }

    @Test
    void testSetters() {
        TipoImpostoDto tipoImpostoDto = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        Long newId = 2L;
        String newNome = "IPI";
        String newDescricao = "Imposto sobre Produtos Industrializados";
        Double newAliquota = 10.0;

        tipoImpostoDto.setId(newId);
        tipoImpostoDto.setNome(newNome);
        tipoImpostoDto.setDescricao(newDescricao);
        tipoImpostoDto.setAliquota(newAliquota);

        assertEquals(newId, tipoImpostoDto.getId(), "The id should match the new value");
        assertEquals(newNome, tipoImpostoDto.getNome(), "The nome should match the new value");
        assertEquals(newDescricao, tipoImpostoDto.getDescricao(), "The descricao should match the new value");
        assertEquals(newAliquota, tipoImpostoDto.getAliquota(), "The aliquota should match the new value");
    }

    @Test
    void testEquals() {
        TipoImpostoDto dto1 = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImpostoDto dto2 = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImpostoDto dto3 = new TipoImpostoDto(2L, "IPI", "Imposto sobre Produtos Industrializados", 10.0);

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertTrue(dto2.equals(dto1), "Equality should be symmetric");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
        assertFalse(dto1.equals(null), "Object should not be equal to null");
        assertFalse(dto1.equals(new Object()), "Object should not be equal to an instance of a different class");
    }

    @Test
    void testHashCode() {
        TipoImpostoDto dto1 = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImpostoDto dto2 = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImpostoDto dto3 = new TipoImpostoDto(2L, "IPI", "Imposto sobre Produtos Industrializados", 10.0);

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Objects with the same values should have the same hashCode");
        assertNotEquals(dto1.hashCode(), dto3.hashCode(), "Objects with different values should have different hashCodes");
    }

    @Test
    void testToString() {
        TipoImpostoDto dto = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);

        String toStringResult = dto.toString();

        assertTrue(toStringResult.contains("TipoImpostoDto"), "toString should contain the class name");
        assertTrue(toStringResult.contains("1"), "toString should contain the id value");
        assertTrue(toStringResult.contains("ICMS"), "toString should contain the nome value");
        assertTrue(toStringResult.contains("Imposto sobre Circulação de Mercadorias e Serviços"), "toString should contain the descricao value");
        assertTrue(toStringResult.contains("18.0"), "toString should contain the aliquota value");
    }

    @Test
    void testCanEqual() {
        TipoImpostoDto dto1 = new TipoImpostoDto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImpostoDto dto2 = new TipoImpostoDto(2L, "IPI", "Imposto sobre Produtos Industrializados", 10.0);
        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}