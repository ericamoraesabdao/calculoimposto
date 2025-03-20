package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RegisterUserDtoTest {
    @Test
    public void testHashCode() {
        RegisterUserDto dto1 = new RegisterUserDto();
        dto1.setUsername("user1");
        dto1.setPassword("password1");
        dto1.setRole("ADMIN");

        RegisterUserDto dto2 = new RegisterUserDto();
        dto2.setUsername("user1");
        dto2.setPassword("password1");
        dto2.setRole("ADMIN");

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testEquals() {
        RegisterUserDto dto1 = new RegisterUserDto();
        dto1.setUsername("user1");
        dto1.setPassword("password1");
        dto1.setRole("ADMIN");

        RegisterUserDto dto2 = new RegisterUserDto();
        dto2.setUsername("user1");
        dto2.setPassword("password1");
        dto2.setRole("ADMIN");

        RegisterUserDto dto3 = new RegisterUserDto();
        dto3.setUsername("user2");
        dto3.setPassword("password2");
        dto3.setRole("USER");

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setUsername("user1");
        dto.setPassword("password1");
        dto.setRole("ADMIN");

        String expected = "RegisterUserDto(username=user1, password=password1, role=ADMIN)";
        String actual = dto.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        RegisterUserDto dto1 = new RegisterUserDto();
        dto1.setUsername("user1");
        dto1.setPassword("password1");
        dto1.setRole("ADMIN");

        RegisterUserDto dto2 = new RegisterUserDto();
        dto2.setUsername("user1");
        dto2.setPassword("password1");
        dto2.setRole("ADMIN");

        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}