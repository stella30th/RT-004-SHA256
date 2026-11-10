package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE033 {

@Test
public void testCaesarDecipherNormalAndWrap() {
    DecryptionUtils utils = new DecryptionUtils("dummyKey");

    // Standard shift of 3
    String decrypted = utils.caesarDecipher("Khoor, Zruog!", 3);
    assertEquals("Hello, World!", decrypted);

    // Shift greater than 26 (29 % 26 == 3)
    String decryptedLargeShift = utils.caesarDecipher("Khoor", 29);
    assertEquals("Hello", decryptedLargeShift);
}

@Test
public void testCaesarDecipherNonAlphabeticAndZeroShift() {
    DecryptionUtils utils = new DecryptionUtils("dummyKey");

    // Shift 0 should return the same string
    String zeroShift = utils.caesarDecipher("NoChangeHere!", 0);
    assertEquals("NoChangeHere!", zeroShift);

    // Ciphertext with no letters should remain unchanged
    String symbolsOnly = utils.caesarDecipher("12345!@#$%^&*()_+", 7);
    assertEquals("12345!@#$%^&*()_+", symbolsOnly);
}

@Test
public void testCaesarDecipherEmptyString() {
    DecryptionUtils utils = new DecryptionUtils("dummyKey");
    String emptyResult = utils.caesarDecipher("", 5);
    assertEquals("", emptyResult);
}

@Test
public void testVigenereDecipherBasic() {
    DecryptionUtils utils = new DecryptionUtils("key");

    // "HELLO" encrypted with "key" -> 'H'+10='R', 'E'+4='I', 'L'+24='J', 'L'+10='V', 'O'+4='S'
    String decrypted = utils.vigenereDecipher("RIJVS");
    assertEquals("HELLO", decrypted);
}

@Test
public void testVigenereDecipherWithNonLettersAndMixedCaseKey() {
    // Mixed-case key should be normalized using toLowerCase
    DecryptionUtils utils = new DecryptionUtils("LeMoN");

    // "attack at dawn" encrypted with "lemon" yields "lxfopv ef rnhr"
    // Non-letter characters (spaces) should be preserved without advancing keyIndex
    String decrypted = utils.vigenereDecipher("lxfopv ef rnhr");
    assertEquals("attack at dawn", decrypted);
}

@Test
public void testRailFenceDecipherTwoRails() {
    DecryptionUtils utils = new DecryptionUtils("dummyKey");

    // "ABCDEFGH" divided into 2 rails:
    // Rail 0: A C E G
    // Rail 1: B D F H -> ciphertext "ACEGBDFH"
    String decrypted = utils.railFenceDecipher("ACEGBDFH", 2);
    assertEquals("ABCDEFGH", decrypted);
}

@Test
public void testRailFenceDecipherThreeRails() {
    DecryptionUtils utils = new DecryptionUtils("dummyKey");

    // "ABCDEFGH" divided into 3 rails:
    // Rail 0: A . . . E . . .
    // Rail 1: . B . D . F . H
    // Rail 2: . . C . . . G . -> ciphertext "AEBDFHCG"
    String decrypted = utils.railFenceDecipher("AEBDFHCG", 3);
    assertEquals("ABCDEFGH", decrypted);

    // "Hello World" divided into 3 rails yields "Horel ollWd"
    String decryptedPhrase = utils.railFenceDecipher("Horel ollWd", 3);
    assertEquals("Hello World", decryptedPhrase);
}

@Test
public void testRailFenceDecipherEdgeCases() {
    DecryptionUtils utils = new DecryptionUtils("dummyKey");

    // Empty string
    String emptyResult = utils.railFenceDecipher("", 3);
    assertEquals("", emptyResult);

    // Single character with rails > length
    String singleCharResult = utils.railFenceDecipher("Z", 3);
    assertEquals("Z", singleCharResult);
}

}