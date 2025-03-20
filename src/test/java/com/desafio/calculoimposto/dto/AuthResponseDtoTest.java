package com.desafio.calculoimposto.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseDtoTest {
    @Test
    void testAccessTokenGetterAndSetter() {
        AuthResponseDto authResponseDto = new AuthResponseDto();
        String expectedAccessToken = "testAccessToken";

        authResponseDto.setAccessToken(expectedAccessToken);

        assertEquals(expectedAccessToken, authResponseDto.getAccessToken(), "The access token should match the expected value");
    }

    @Test
    void testDefaultConstructor() {
        AuthResponseDto authResponseDto = new AuthResponseDto();

        assertEquals(null, authResponseDto.getAccessToken(), "The default access token should be null");
    }

    @Test
    void testEquals() {
        AuthResponseDto dto1 = new AuthResponseDto();
        AuthResponseDto dto2 = new AuthResponseDto();
        dto1.setAccessToken("testToken");
        dto2.setAccessToken("testToken");

        assertTrue(dto1.equals(dto2), "Objects with the same accessToken should be equal");
        assertTrue(dto2.equals(dto1), "Equality should be symmetric");

        dto2.setAccessToken("differentToken");
        assertFalse(dto1.equals(dto2), "Objects with different accessToken should not be equal");
    }

    @Test
    void testHashCode() {
        AuthResponseDto dto1 = new AuthResponseDto();
        AuthResponseDto dto2 = new AuthResponseDto();
        dto1.setAccessToken("testToken");
        dto2.setAccessToken("testToken");

        assertEquals(dto1.hashCode(), dto2.hashCode(), "Objects with the same accessToken should have the same hashCode");

        dto2.setAccessToken("differentToken");
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Objects with different accessToken should have different hashCodes");
    }

    @Test
    void testToString() {
        AuthResponseDto dto = new AuthResponseDto();
        dto.setAccessToken("testToken");

        String toStringResult = dto.toString();

        assertTrue(toStringResult.contains("AuthResponseDto"), "toString should contain the class name");
        assertTrue(toStringResult.contains("testToken"), "toString should contain the accessToken value");
    }

    @Test
    void testCanEqual() {
        AuthResponseDto dto1 = new AuthResponseDto();
        AuthResponseDto dto2 = new AuthResponseDto();
        Object nonDtoObject = new Object();

        assertTrue(dto1.canEqual(dto2), "canEqual should return true for objects of the same type");
        assertFalse(dto1.canEqual(nonDtoObject), "canEqual should return false for objects of different types");
    }
}