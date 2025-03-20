package com.desafio.calculoimposto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoImpostoTest {
    @Test
    public void testSetDescricao() {
        TipoImposto tipoImposto = new TipoImposto();
        String novaDescricao = "Nova descrição do imposto";

        tipoImposto.setDescricao(novaDescricao);

        assertEquals(novaDescricao, tipoImposto.getDescricao(), "A descrição deve ser atualizada corretamente pelo método setDescricao");
    }

    @Test
    public void testHashCode() {
        TipoImposto tipo1 = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImposto tipo2 = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImposto tipo3 = new TipoImposto(2L, "ISS", "Imposto sobre Serviços", 5.0);

        assertEquals(tipo1.hashCode(), tipo2.hashCode(), "Hash codes should be equal for equal objects");
        assertFalse(tipo1.hashCode() == tipo3.hashCode(), "Hash codes should not be equal for different objects");
    }

    @Test
    public void testEquals() {
        TipoImposto tipo1 = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImposto tipo2 = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImposto tipo3 = new TipoImposto(2L, "ISS", "Imposto sobre Serviços", 5.0);

        assertTrue(tipo1.equals(tipo2), "Objects with the same values should be equal");
        assertFalse(tipo1.equals(tipo3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        TipoImposto tipo = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);

        String expected = "TipoImposto(id=1, nome=ICMS, descricao=Imposto sobre Circulação de Mercadorias e Serviços, aliquota=18.0)";
        String actual = tipo.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        TipoImposto tipo1 = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        TipoImposto tipo2 = new TipoImposto(1L, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        Object nonTipoImpostoObject = new Object();

        assertTrue(tipo1.canEqual(tipo2), "canEqual should return true for objects of the same type");
        assertFalse(tipo1.canEqual(nonTipoImpostoObject), "canEqual should return false for objects of different types");
    }
}