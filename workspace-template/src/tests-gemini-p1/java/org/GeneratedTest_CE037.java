package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE037 {

    private EncryptionUtils encryptionUtils;

    @Before
    public void setUp() {
        encryptionUtils = new EncryptionUtils("key");
    }

    @Test
    public void testCaesarCipher() {
        String plaintext = "Hello, World!";
        String encrypted = encryptionUtils.caesarCipher(plaintext, 3);
        assertEquals("Khoor, Zruog!", encrypted);

        String lower = "xyz";
        assertEquals("abc", encryptionUtils.caesarCipher(lower, 3));
    }

    @Test
    public void testVigenereCipher() {
        String plaintext = "Hello, World!";
        String encrypted = encryptionUtils.vigenereCipher(plaintext);
        assertEquals("Rijvs, Uyvjn!", encrypted);

        EncryptionUtils abcUtils = new EncryptionUtils("abc");
        assertEquals("bbb", abcUtils.vigenereCipher("bba"));
    }

    @Test
    public void testRailFenceCipher() {
        String text = "HELLO WORLD";
        String encrypted = encryptionUtils.railFenceCipher(text, 3);
        assertEquals("HOREL OLLWD", encrypted);

        String shortText = "HELLO!";
        assertEquals("HOEL!L", encryptionUtils.railFenceCipher(shortText, 3));
    }

    @Test
    public void testRailFenceCipherWithSpacesAndPunctuation() {
        String text = "A B C!";
        String encrypted = encryptionUtils.railFenceCipher(text, 2);
        assertEquals("ABC  !", encrypted);
    }
}
