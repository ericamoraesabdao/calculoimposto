package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDtoTest {
    @Test
    public void testHashCode() {
        LoginDto dto1 = new LoginDto();
        dto1.setUsername("user1");
        dto1.setPassword("password1");

        LoginDto dto2 = new LoginDto();
        dto2.setUsername("user1");
        dto2.setPassword("password1");

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testEquals() {
        LoginDto dto1 = new LoginDto();
        dto1.setUsername("user1");
        dto1.setPassword("password1");

        LoginDto dto2 = new LoginDto();
        dto2.setUsername("user1");
        dto2.setPassword("password1");

        LoginDto dto3 = new LoginDto();
        dto3.setUsername("user2");
        dto3.setPassword("password2");

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        LoginDto dto = new LoginDto();
        dto.setUsername("user1");
        dto.setPassword("password1");

        String expected = "LoginDto(username=user1, password=password1)";
        String actual = dto.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        LoginDto dto1 = new LoginDto();
        dto1.setUsername("user1");
        dto1.setPassword("password1");

        LoginDto dto2 = new LoginDto();
        dto2.setUsername("user1");
        dto2.setPassword("password1");

        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}