package org.example;

import org.junit.Test; import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE033 {

@Test
public void testCaesarDecipherStandardShift() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "Khoor, Zruog!";
    String decrypted = utils.caesarDecipher(ciphertext, 3);
    assertEquals("Hello, World!", decrypted);
}

@Test
public void testCaesarDecipherWrapAroundAndLargeShift() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    // Shift 29 is equivalent to shift 3 (29 % 26 == 3)
    // 'C' (2) - 3 + 26 = 25 -> 'Z'
    // 'D' (3) - 3 + 26 = 0  -> 'A'
    String ciphertext = "CDab";
    String decrypted = utils.caesarDecipher(ciphertext, 29);
    assertEquals("ZAxy", decrypted);
}

@Test
public void testCaesarDecipherZeroShiftAndPreserveSpecialCharacters() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "123 !@# Test_Zero";
    String decrypted = utils.caesarDecipher(ciphertext, 0);
    assertEquals("123 !@# Test_Zero", decrypted);
}

@Test
public void testCaesarDecipherEmptyString() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    assertEquals("", utils.caesarDecipher("", 5));
}

@Test
public void testVigenereDecipherNormal() {
    DecryptionUtils utils = new DecryptionUtils("key");
    String ciphertext = "Rijvs, Uyvjn!";
    String decrypted = utils.vigenereDecipher(ciphertext);
    assertEquals("Hello, World!", decrypted);
}

@Test
public void testVigenereDecipherKeyCaseInsensitive() {
    // Uppercase key "KEY" should produce same result as "key"
    DecryptionUtils utils = new DecryptionUtils("KEY");
    String ciphertext = "Rijvs, Uyvjn!";
    String decrypted = utils.vigenereDecipher(ciphertext);
    assertEquals("Hello, World!", decrypted);
}

@Test
public void testVigenereDecipherNonLettersAndEmpty() {
    DecryptionUtils utils = new DecryptionUtils("cipher");
    assertEquals("", utils.vigenereDecipher(""));
    assertEquals("12345 -- !!", utils.vigenereDecipher("12345 -- !!"));
}

@Test
public void testRailFenceDecipherThreeRails() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    // "HELLO WORLD" with 3 rails encodes to "HOREL OLLWD"
    String encrypted = "HOREL OLLWD";
    String decrypted = utils.railFenceDecipher(encrypted, 3);
    assertEquals("HELLO WORLD", decrypted);
}

@Test
public void testRailFenceDecipherTwoRails() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    // "HELLOWORLD" with 2 rails:
    // row 0: H L O R D
    // row 1: E L W L
    // encrypted: "HLORDELOWL"
    String encrypted = "HLORDELOWL";
    String decrypted = utils.railFenceDecipher(encrypted, 2);
    assertEquals("HELLOWORLD", decrypted);
}

@Test
public void testRailFenceDecipherEmptyAndSingleChar() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    assertEquals("", utils.railFenceDecipher("", 3));
    assertEquals("A", utils.railFenceDecipher("A", 3));
}

}