package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE037 {

@Test
public void testCaesarCipher() {
    EncryptionUtils utils = new EncryptionUtils("dummy");
    assertEquals("Khoor Zruog!", utils.caesarCipher("Hello World!", 3));
    assertEquals("def", utils.caesarCipher("abc", 29)); // 29 % 26 = 3
}

@Test
public void testVigenereCipher() {
    EncryptionUtils utils = new EncryptionUtils("lemon");
    // "Attack at dawn" with "lemon" -> "Lxfopv ef rnhr"
    assertEquals("Lxfopv ef rnhr", utils.vigenereCipher("Attack at dawn"));
}

@Test
public void testRailFenceCipher() {
    EncryptionUtils utils = new EncryptionUtils("dummy");
    // "HELLO WORLD" with rails=2:
    // H   L   O   O   L
    //   E   L   W   R   D
    // Result: "HLOOLELWRD"
    assertEquals("HLOOLELWRD", utils.railFenceCipher("HELLO WORLD", 2));
}

@Test
public void testCaesarCipherPreservesNonLetters() {
    EncryptionUtils utils = new EncryptionUtils("dummy");
    assertEquals("123 #$%", utils.caesarCipher("123 #$%", 5));
}

}
