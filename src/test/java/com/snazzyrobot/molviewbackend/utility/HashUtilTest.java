package com.snazzyrobot.molviewbackend.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {

    /**
     * The HashUtilTest class is designed to test the calculateSHA256 method of the HashUtil class.
     * The calculateSHA256 method computes the SHA-256 hash of a given text input and returns the hash as a hexadecimal string.
     */

    @Test
    void testCalculateSHA256_withValidInput() {
        String input = "hello world";
        String expectedHash = "b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9";
        String actualHash = HashUtil.calculateSHA256(input);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    void testCalculateSHA256_withEmptyString() {
        String input = "";
        String expectedHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        String actualHash = HashUtil.calculateSHA256(input);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    void testCalculateSHA256_withSpecialCharacters() {
        String input = "!@#$%^&*()_+-={}[]|;:'<>,.?/";
        String expectedHash = "d286061736c5ad6f9ae8cb4aafd0c172f5b32a918b9815b3b62e45d20aa8cfcd";
        String actualHash = HashUtil.calculateSHA256(input);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    void testCalculateSHA256_withUnicodeCharacters() {
        String input = "ελληνικά";
        String expectedHash = "8ab1661503fc902d0457fd921164d9eda9d23a9d3e9ce08f3120889519a08c31";
        String actualHash = HashUtil.calculateSHA256(input);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    void testCalculateSHA256_withLongString() {
        String input = "a".repeat(1000);
        String expectedHash = "41edece42d63e8d9bf515a9ba6932e1c20cbc9f5a5d134645adb5db1b9737ea3";
        String actualHash = HashUtil.calculateSHA256(input);
        assertEquals(expectedHash, actualHash);
    }
}