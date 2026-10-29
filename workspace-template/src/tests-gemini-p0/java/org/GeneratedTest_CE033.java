package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE033 {

@Test
public void testCaesarDecipher() {
    DecryptionUtils utils = new DecryptionUtils("dummy");
    // "Khoor Zruog!" with shift 3 decrypted -> "Hello World!"
    assertEquals("Hello World!", utils.caesarDecipher("Khoor Zruog!", 3));
    assertEquals("abc", utils.caesarDecipher("def", 29)); // 29 % 26 = 3
}

@Test
public void testVigenereDecipher() {
    DecryptionUtils utils = new DecryptionUtils("lemon");
    // "Attack at dawn" encrypted with "lemon" -> "Lxfopv ef rnhr"
    assertEquals("Attack at dawn", utils.vigenereDecipher("Lxfopv ef rnhr"));
}

@Test
public void testRailFenceDecipher() {
    DecryptionUtils utils = new DecryptionUtils("dummy");
    // "WE ARE DISCOVERED FLEE AT ONCE" -> "WECRLTEERDSOEEFEAOCAIVDEN" with rails=3
    assertEquals("WECRLTEERDSOEEFEAOCAIVDEN", utils.railFenceDecipher("WECRLTEERDSOEEFEAOCAIVDEN", 1));
    assertEquals("HELLO WORLD", utils.railFenceDecipher("HOLELWRD LO", 2));
}

@Test
public void testCaesarDecipherNonAlphabetCharactersPreserved() {
    DecryptionUtils utils = new DecryptionUtils("dummy");
    assertEquals("1234 !?-$", utils.caesarDecipher("1234 !?-$", 5));
}

}
