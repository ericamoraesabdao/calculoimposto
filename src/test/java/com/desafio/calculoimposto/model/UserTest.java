package com.desafio.calculoimposto.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void testSetUsername() {
        User user = new User();
        String expectedUsername = "testUser";

        user.setUsername(expectedUsername);

        assertEquals(expectedUsername, user.getUsername(), "O username deve ser atualizado corretamente pelo método setUsername");
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        String expectedPassword = "securePassword";

        user.setPassword(expectedPassword);

        assertEquals(expectedPassword, user.getPassword(), "A senha deve ser atualizada corretamente pelo método setPassword");
    }

    @Test
    public void testSetRoles() {
        User user = new User();
        Set<Role> expectedRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_USER");
        expectedRoles.add(role1);

        user.setRoles(expectedRoles);

        assertEquals(expectedRoles, user.getRoles(), "As roles devem ser atualizadas corretamente pelo método setRoles");
    }

    @Test
    public void testEquals() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("user1");
        user2.setPassword("password1");

        User user3 = new User();
        user3.setId(2L);
        user3.setUsername("user2");
        user3.setPassword("password2");

        assertEquals(user1, user2, "Os objetos devem ser iguais");
        assertNotEquals(user1, user3, "Os objetos não devem ser iguais");
    }

    @Test
    public void testHashCode() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("user1");
        user2.setPassword("password1");

        User user3 = new User();
        user3.setId(2L);
        user3.setUsername("user2");
        user3.setPassword("password2");

        assertEquals(user1.hashCode(), user2.hashCode(), "Os hashCodes devem ser iguais");
        assertNotEquals(user1.hashCode(), user3.hashCode(), "Os hashCodes não devem ser iguais");
    }

    @Test
    public void testCanEqual() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("user1");
        user2.setPassword("password1");

        Object otherObject = new Object();

        assertTrue(user1.canEqual(user2), "O método canEqual deve retornar true para objetos do mesmo tipo");
        assertFalse(user1.canEqual(otherObject), "O método canEqual deve retornar false para objetos de tipos diferentes");
    }
}