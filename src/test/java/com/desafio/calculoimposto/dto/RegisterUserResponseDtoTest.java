package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterUserResponseDtoTest {
    @Test
    public void testHashCode() {
        RegisterUserResponseDto dto1 = new RegisterUserResponseDto();
        dto1.setId(1L);
        dto1.setUsername("user1");
        dto1.setRole("ADMIN");

        RegisterUserResponseDto dto2 = new RegisterUserResponseDto();
        dto2.setId(1L);
        dto2.setUsername("user1");
        dto2.setRole("ADMIN");

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testEquals() {
        RegisterUserResponseDto dto1 = new RegisterUserResponseDto();
        dto1.setId(1L);
        dto1.setUsername("user1");
        dto1.setRole("ADMIN");

        RegisterUserResponseDto dto2 = new RegisterUserResponseDto();
        dto2.setId(1L);
        dto2.setUsername("user1");
        dto2.setRole("ADMIN");

        RegisterUserResponseDto dto3 = new RegisterUserResponseDto();
        dto3.setId(2L);
        dto3.setUsername("user2");
        dto3.setRole("USER");

        assertTrue(dto1.equals(dto2), "Objects with the same values should be equal");
        assertFalse(dto1.equals(dto3), "Objects with different values should not be equal");
    }

    @Test
    public void testToString() {
        RegisterUserResponseDto dto = new RegisterUserResponseDto();
        dto.setId(1L);
        dto.setUsername("user1");
        dto.setRole("ADMIN");

        String expected = "RegisterUserResponseDto(id=1, username=user1, role=ADMIN)";
        String actual = dto.toString();

        assertEquals(expected, actual, "toString() output should match the expected format");
    }

    @Test
    public void testCanEqual() {
        RegisterUserResponseDto dto1 = new RegisterUserResponseDto();
        dto1.setId(1L);
        dto1.setUsername("user1");
        dto1.setRole("ADMIN");

        RegisterUserResponseDto dto2 = new RegisterUserResponseDto();
        dto2.setId(1L);
        dto2.setUsername("user1");
        dto2.setRole("ADMIN");

        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}