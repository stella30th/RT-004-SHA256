package org.example;

import org.junit.Test; import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE037 {

@Test
public void testCaesarCipherNormalAndWrap() {
    EncryptionUtils utils = new EncryptionUtils("key");
    assertEquals("Khoor, Zruog!", utils.caesarCipher("Hello, World!", 3));
    assertEquals("abcABC", utils.caesarCipher("xyzXYZ", 3));
}

@Test
public void testCaesarCipherSpecialAndEdgeCases() {
    EncryptionUtils utils = new EncryptionUtils("key");
    assertEquals("", utils.caesarCipher("", 5));
    assertEquals("123 !?$", utils.caesarCipher("123 !?$", 4));
    assertEquals("Java", utils.caesarCipher("Java", 0));
    assertEquals("Java", utils.caesarCipher("Java", 26));
}

@Test
public void testVigenereCipherNormal() {
    EncryptionUtils utils = new EncryptionUtils("lemon");
    assertEquals("LXFOPVEFRNHR", utils.vigenereCipher("ATTACKATDAWN"));
}

@Test
public void testVigenereCipherMixedCaseAndPunctuation() {
    EncryptionUtils utils = new EncryptionUtils("lemon");
    assertEquals("Lxfopv ef rnhr!", utils.vigenereCipher("Attack at dawn!"));

    EncryptionUtils upperKeyUtils = new EncryptionUtils("LEMON");
    assertEquals("Lxfopv ef rnhr!", upperKeyUtils.vigenereCipher("Attack at dawn!"));
    assertEquals("", upperKeyUtils.vigenereCipher(""));
}

@Test
public void testRailFenceCipherRailsTwo() {
    EncryptionUtils utils = new EncryptionUtils("dummy");
    assertEquals("HloWrdel ol", utils.railFenceCipher("Hello World", 2));
}

@Test
public void testRailFenceCipherRailsThree() {
    EncryptionUtils utils = new EncryptionUtils("dummy");
    assertEquals("HOREL OLLWD", utils.railFenceCipher("HELLO WORLD", 3));
}

@Test
public void testRailFenceCipherEdgeCases() {
    EncryptionUtils utils = new EncryptionUtils("dummy");
    assertEquals("", utils.railFenceCipher("", 3));
    assertEquals("AB", utils.railFenceCipher("AB", 4));
}

}