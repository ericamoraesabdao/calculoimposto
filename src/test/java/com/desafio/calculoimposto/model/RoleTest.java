package com.desafio.calculoimposto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {
    @Test
    public void testSetName() {
        Role role = new Role();
        String expectedName = "ROLE_ADMIN";

        role.setName(expectedName);

        assertEquals(expectedName, role.getName(), "O nome deve ser atualizado corretamente pelo método setName");
    }

    @Test
    public void testGetName() {
        String expectedName = "ROLE_USER";
        Role role = new Role(expectedName);

        String actualName = role.getName();

        assertEquals(expectedName, actualName, "O método getName deve retornar o nome correto");
    }

    @Test
    public void testSetId() {
        Role role = new Role();
        Long expectedId = 1L;

        role.setId(expectedId);

        assertEquals(expectedId, role.getId(), "O ID deve ser atualizado corretamente pelo método setId");
    }

    @Test
    public void testGetId() {
        Long expectedId = 2L;
        Role role = new Role();
        role.setId(expectedId);

        Long actualId = role.getId();

        assertEquals(expectedId, actualId, "O método getId deve retornar o ID correto");
    }

    @Test
    public void testEquals() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_ADMIN");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("ROLE_ADMIN");

        Role role3 = new Role();
        role3.setId(2L);
        role3.setName("ROLE_USER");

        assertTrue(role1.equals(role2), "Os objetos com os mesmos valores devem ser iguais");
        assertFalse(role1.equals(role3), "Os objetos com valores diferentes não devem ser iguais");
        assertFalse(role1.equals(null), "Um objeto não deve ser igual a null");
        assertFalse(role1.equals(new Object()), "Um objeto não deve ser igual a um objeto de outro tipo");
    }

    @Test
    public void testHashCode() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_ADMIN");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("ROLE_ADMIN");

        Role role3 = new Role();
        role3.setId(2L);
        role3.setName("ROLE_USER");

        assertEquals(role1.hashCode(), role2.hashCode(), "Objetos iguais devem ter o mesmo hashCode");
        assertNotEquals(role1.hashCode(), role3.hashCode(), "Objetos diferentes devem ter hashCodes diferentes");
    }

    @Test
    public void testCanEqual() {
        Role role1 = new Role();
        Role role2 = new Role();
        Object otherObject = new Object();

        assertTrue(role1.canEqual(role2), "O método canEqual deve retornar true para objetos do mesmo tipo");
        assertFalse(role1.canEqual(otherObject), "O método canEqual deve retornar false para objetos de tipos diferentes");
    }
}