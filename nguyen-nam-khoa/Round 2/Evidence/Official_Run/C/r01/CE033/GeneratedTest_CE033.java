package org.example;

import org.junit.Test; import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE033 {

@Test
public void testCaesarDecipherBasic() {
    DecryptionUtils utils = new DecryptionUtils("key");
    // "Khoor, Zruog!" with shift 3 decodes to "Hello, World!"
    String decrypted = utils.caesarDecipher("Khoor, Zruog! 123", 3);
    assertEquals("Hello, World! 123", decrypted);
}

@Test
public void testCaesarDecipherShiftWrapping() {
    DecryptionUtils utils = new DecryptionUtils("key");
    // shift 0 and multiples of 26 should return the original string
    assertEquals("Sample Text", utils.caesarDecipher("Sample Text", 0));
    assertEquals("Sample Text", utils.caesarDecipher("Sample Text", 26));
    assertEquals("Sample Text", utils.caesarDecipher("Sample Text", 52));

    // shift 1 with wrap-around at 'a'/'A'
    // 'a' shifted by 1 backwards should wrap to 'z', 'A' to 'Z'
    assertEquals("zZ", utils.caesarDecipher("aA", 1));
}

@Test
public void testVigenereDecipherBasic() {
    // key = "key" -> shifts: k=10, e=4, y=24
    // 'h'(7) + 10 = 'r'(17), 'e'(4) + 4 = 'i'(8), 'l'(11) + 24 = 'j'(9), 'l'(11) + 10 = 'v'(21), 'o'(14) + 4 = 's'(18)
    // Ciphertext "rijvs" deciphers to "hello"
    DecryptionUtils utils = new DecryptionUtils("key");
    assertEquals("hello", utils.vigenereDecipher("rijvs"));
    assertEquals("HELLO", utils.vigenereDecipher("RIJVS"));

    // Uppercase key should work identically
    DecryptionUtils utilsUpper = new DecryptionUtils("KEY");
    assertEquals("hello", utilsUpper.vigenereDecipher("rijvs"));
}

@Test
public void testVigenereDecipherNonLetters() {
    // Non-letter characters should not advance keyIndex
    // key = "key"
    // 'r' (idx 0 -> k) -> 'h'
    // 'i' (idx 1 -> e) -> 'e'
    // 'j' (idx 2 -> y) -> 'l'
    // 'v' (idx 3 -> k) -> 'l'
    // 's' (idx 4 -> e) -> 'o'
    // ',' (non-letter, keyIndex remains 5)
    // ' ' (non-letter, keyIndex remains 5)
    // 'v' (idx 5 -> key[5%3]=key[2]='y' -> shift 24) -> 'v'(21) - 24 + 26 = 23 -> 'x'
    // '!' (non-letter)
    DecryptionUtils utils = new DecryptionUtils("key");
    assertEquals("hello, x!", utils.vigenereDecipher("rijvs, v!"));
}

@Test
public void testRailFenceDecipher() {
    DecryptionUtils utils = new DecryptionUtils("dummy");

    // rails = 3:
    // Plaintext: "WEAREDISCOVEREDFLEEATONCE"
    // Ciphertext: "WECRLTEERDSOEEFEAOCAIVDEN"
    String ciphertext3 = "WECRLTEERDSOEEFEAOCAIVDEN";
    assertEquals("WEAREDISCOVEREDFLEEATONCE", utils.railFenceDecipher(ciphertext3, 3));

    // rails = 2:
    // Plaintext: "Hello World" (length 11)
    // Row 0: H, l, o, o, l (indices 0, 2, 4, 6, 8, 10) -> "Hlool"
    // Row 1: e, l,  , W, r, d (indices 1, 3, 5, 7, 9) -> "el Wrd"
    // Ciphertext: "Hloolel Wrd"
    String ciphertext2 = "Hloolel Wrd";
    assertEquals("Hello World", utils.railFenceDecipher(ciphertext2, 2));
}

@Test
public void testEmptyStrings() {
    DecryptionUtils utils = new DecryptionUtils("key");
    assertEquals("", utils.caesarDecipher("", 5));
    assertEquals("", utils.vigenereDecipher(""));
    assertEquals("", utils.railFenceDecipher("", 3));
}

}