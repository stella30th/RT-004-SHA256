package org.example;

import org.junit.Test; import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE037 {

@Test
public void testCaesarCipher_basicShiftAndWrapping() {
    EncryptionUtils utils = new EncryptionUtils("key");
    String result1 = utils.caesarCipher("Hello, World!", 3);
    assertEquals("Khoor, Zruog!", result1);

    String result2 = utils.caesarCipher("xyzXYZ", 3);
    assertEquals("abcABC", result2);
}

@Test
public void testCaesarCipher_noShiftAndSpecialChars() {
    EncryptionUtils utils = new EncryptionUtils("key");
    assertEquals("Test 123!@#", utils.caesarCipher("Test 123!@#", 0));
    assertEquals("Test 123!@#", utils.caesarCipher("Test 123!@#", 26));
    assertEquals("", utils.caesarCipher("", 5));
}

@Test
public void testVigenereCipher_basicAndKeyCaseInsensitivity() {
    EncryptionUtils utilsLower = new EncryptionUtils("key");
    assertEquals("Rijvs, Uyvjn!", utilsLower.vigenereCipher("Hello, World!"));

    EncryptionUtils utilsMixed = new EncryptionUtils("kEy");
    assertEquals("Rijvs, Uyvjn!", utilsMixed.vigenereCipher("Hello, World!"));
}

@Test
public void testVigenereCipher_nonLettersAndEmpty() {
    EncryptionUtils utils = new EncryptionUtils("secret");
    assertEquals("12345 !@#$%", utils.vigenereCipher("12345 !@#$%"));
    assertEquals("", utils.vigenereCipher(""));
}

@Test
public void testRailFenceCipher_threeRails() {
    EncryptionUtils utils = new EncryptionUtils("unused");
    String result = utils.railFenceCipher("HELLO WORLD", 3);
    assertEquals("HOREL OLLWD", result);
}

@Test
public void testRailFenceCipher_twoRailsAndEmpty() {
    EncryptionUtils utils = new EncryptionUtils("unused");
    assertEquals("135246", utils.railFenceCipher("123456", 2));
    assertEquals("", utils.railFenceCipher("", 3));
}

}
