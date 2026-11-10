package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE033 {

@Test
public void testCaesarDecipherBasic() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "Khoor, Zruog!";
    String result = utils.caesarDecipher(ciphertext, 3);
    assertEquals("Hello, World!", result);
}

@Test
public void testCaesarDecipherWrapAround() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "abc ABC";
    String result = utils.caesarDecipher(ciphertext, 3);
    assertEquals("xyz XYZ", result);
}

@Test
public void testCaesarDecipherShifts() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String original = "Testing Caesar 123!";

    assertEquals(original, utils.caesarDecipher(original, 0));
    assertEquals(original, utils.caesarDecipher(original, 26));
    assertEquals(original, utils.caesarDecipher(original, 52));
}

@Test
public void testCaesarDecipherNonAlphabetic() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "12345!@#$%^&*()_+ ";
    String result = utils.caesarDecipher(ciphertext, 7);
    assertEquals("12345!@#$%^&*()_+ ", result);
}

@Test
public void testVigenereDecipherBasic() {
    DecryptionUtils utils = new DecryptionUtils("key");
    String ciphertext = "Rijvs, Uyvjn!";
    String result = utils.vigenereDecipher(ciphertext);
    assertEquals("Hello, World!", result);
}

@Test
public void testVigenereDecipherUppercaseKey() {
    DecryptionUtils utils = new DecryptionUtils("CIPHER");
    String ciphertext = "CBIGHBCBSHAE";
    String result = utils.vigenereDecipher(ciphertext);
    assertEquals("ATTACKATDAWN", result);
}

@Test
public void testVigenereDecipherPreservesNonLettersAndKeyIndex() {
    DecryptionUtils utils = new DecryptionUtils("b"); // 'b' shifts by 1
    String ciphertext = "123 Bcd 456!";
    String result = utils.vigenereDecipher(ciphertext);
    assertEquals("123 Abc 456!", result);
}

@Test
public void testRailFenceDecipherTwoRails() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "Hloel";
    String result = utils.railFenceDecipher(ciphertext, 2);
    assertEquals("Hello", result);
}

@Test
public void testRailFenceDecipherThreeRails() {
    DecryptionUtils utils = new DecryptionUtils("defaultKey");
    String ciphertext = "WECRLTEERDSOEEFEAOCAIVDEN";
    String result = utils.railFenceDecipher(ciphertext, 3);
    assertEquals("WEAREDISCOVEREDFLEEATONCE", result);
}

@Test
public void testEmptyInputs() {
    DecryptionUtils utils = new DecryptionUtils("key");
    assertEquals("", utils.caesarDecipher("", 5));
    assertEquals("", utils.vigenereDecipher(""));
    assertEquals("", utils.railFenceDecipher("", 3));
}

}