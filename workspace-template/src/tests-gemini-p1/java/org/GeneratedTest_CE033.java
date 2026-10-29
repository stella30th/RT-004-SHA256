package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE033 {

    private DecryptionUtils decryptionUtils;

    @Before
    public void setUp() {
        decryptionUtils = new DecryptionUtils("key");
    }

    @Test
    public void testCaesarDecipher() {
        String ciphertext = "Khoor, Zruog!";
        String plaintext = decryptionUtils.caesarDecipher(ciphertext, 3);
        assertEquals("Hello, World!", plaintext);

        String cipherLower = "abc";
        assertEquals("xyz", decryptionUtils.caesarDecipher(cipherLower, 3));
    }

    @Test
    public void testVigenereDecipher() {
        DecryptionUtils utils = new DecryptionUtils("abc");
        String ciphertext = "bcd";
        assertEquals("bbb", utils.vigenereDecipher(ciphertext));

        String mixedCipher = "Hfnlo, Zptoe!";
        assertEquals("Hello, World!", utils.vigenereDecipher(mixedCipher));
    }

    @Test
    public void testRailFenceDecipherStandard() {
        String encrypted = "HOEL!L";
        String decrypted = decryptionUtils.railFenceDecipher(encrypted, 3);
        assertEquals("HELLO!", decrypted);

        String text = "HOREL OLLWD";
        assertEquals("HELLO WORLD", decryptionUtils.railFenceDecipher(text, 3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRailFenceDecipherOneRailThrowsException() {
        decryptionUtils.railFenceDecipher("ABCDEF", 1);
    }
}
