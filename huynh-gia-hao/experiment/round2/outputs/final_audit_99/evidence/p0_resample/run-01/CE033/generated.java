
// BEGIN GeneratedTest_CE033
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE033 {

    @Test
    public void testCaesarDecipherBasic() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("ABC", utils.caesarDecipher("DEF", 3));
        assertEquals("abc", utils.caesarDecipher("def", 3));
        assertEquals("Hello World",
                utils.caesarDecipher("Khoor Zruog", 3));
    }

    @Test
    public void testCaesarDecipherWrapAround() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("XYZ", utils.caesarDecipher("ABC", 3));
        assertEquals("xyz", utils.caesarDecipher("abc", 3));
        assertEquals("Zz", utils.caesarDecipher("Aa", 1));
    }

    @Test
    public void testCaesarDecipherZeroAndFullRotation() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("Hello, World! 123",
                utils.caesarDecipher("Hello, World! 123", 0));
        assertEquals("Hello",
                utils.caesarDecipher("Hello", 26));
        assertEquals("ABC",
                utils.caesarDecipher("DEF", 29));
        assertEquals("ABC",
                utils.caesarDecipher("DEF", 55));
    }

    @Test
    public void testCaesarDecipherNegativeShift() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("BCD", utils.caesarDecipher("ABC", -1));
        assertEquals("bcd", utils.caesarDecipher("abc", -1));
        assertEquals("DEF", utils.caesarDecipher("ABC", -3));
        assertEquals("ABC", utils.caesarDecipher("ABC", -26));
    }

    @Test
    public void testCaesarDecipherPreservesNonLetters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("Abc 123! @# Zz",
                utils.caesarDecipher("Def 123! @# Cc", 3));
        assertEquals("123 !? -_",
                utils.caesarDecipher("123 !? -_", 7));
        assertEquals("", utils.caesarDecipher("", 5));
    }

    @Test
    public void testCaesarDecipherMixedCase() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("AbCdEf",
                utils.caesarDecipher("DeFgHi", 3));
        assertEquals("aBcDeF",
                utils.caesarDecipher("bCdEfG", 1));
    }

    @Test
    public void testCaesarDecipherNullInput() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertThrows(NullPointerException.class,
                () -> utils.caesarDecipher(null, 3));
    }

    @Test
    public void testCaesarDecipherAllUppercaseLetters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                utils.caesarDecipher(
                        "BCDEFGHIJKLMNOPQRSTUVWXYZA", 1));
    }

    @Test
    public void testCaesarDecipherAllLowercaseLetters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("abcdefghijklmnopqrstuvwxyz",
                utils.caesarDecipher(
                        "bcdefghijklmnopqrstuvwxyza", 1));
    }

    @Test
    public void testVigenereDecipherStandardExample() {
        DecryptionUtils utils = new DecryptionUtils("LEMON");

        assertEquals("ATTACKATDAWN",
                utils.vigenereDecipher("LXFOPVEFRNHR"));
    }

    @Test
    public void testVigenereDecipherMixedCase() {
        DecryptionUtils utils = new DecryptionUtils("LEMON");

        assertEquals("AttackAtDawn",
                utils.vigenereDecipher("LxfopvEfRnhr"));
    }

    @Test
    public void testVigenereDecipherRepeatedKey() {
        DecryptionUtils utils = new DecryptionUtils("ABC");

        assertEquals("AAAAAA",
                utils.vigenereDecipher("ABCABC"));
        assertEquals("aaaaaa",
                utils.vigenereDecipher("abcabc"));
        assertEquals("ZZZZZZ",
                utils.vigenereDecipher("ZABZAB"));
    }

    @Test
    public void testVigenereDecipherPreservesNonLettersAndKeyPosition() {
        DecryptionUtils utils = new DecryptionUtils("ABC");

        assertEquals("AAA AAA!",
                utils.vigenereDecipher("ABC ABC!"));
        assertEquals("A-B-C",
                utils.vigenereDecipher("A-C-E"));
        assertEquals("123 !? @#",
                utils.vigenereDecipher("123 !? @#"));
    }

    @Test
    public void testVigenereDecipherSingleCharacterKey() {
        DecryptionUtils utils = new DecryptionUtils("D");

        assertEquals("Hello World",
                utils.vigenereDecipher("Khoor Zruog"));
        assertEquals("XYZ",
                utils.vigenereDecipher("ABC"));
    }

    @Test
    public void testVigenereDecipherCaseInsensitiveKey() {
        DecryptionUtils upper = new DecryptionUtils("LEMON");
        DecryptionUtils lower = new DecryptionUtils("lemon");
        DecryptionUtils mixed = new DecryptionUtils("LeMoN");

        assertEquals("ATTACKATDAWN",
                upper.vigenereDecipher("LXFOPVEFRNHR"));
        assertEquals("ATTACKATDAWN",
                lower.vigenereDecipher("LXFOPVEFRNHR"));
        assertEquals("ATTACKATDAWN",
                mixed.vigenereDecipher("LXFOPVEFRNHR"));
    }

    @Test
    public void testVigenereDecipherWrapAround() {
        DecryptionUtils first = new DecryptionUtils("B");
        DecryptionUtils second = new DecryptionUtils("Z");

        assertEquals("Zz",
                first.vigenereDecipher("Aa"));
        assertEquals("Bb",
                second.vigenereDecipher("Aa"));
    }

    @Test
    public void testVigenereDecipherEmptyCiphertext() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("", utils.vigenereDecipher(""));
    }

    @Test
    public void testVigenereDecipherEmptyKey() {
        DecryptionUtils utils = new DecryptionUtils("");

        assertEquals("", utils.vigenereDecipher(""));
        assertEquals("123!?", utils.vigenereDecipher("123!?"));

        assertThrows(ArithmeticException.class,
                () -> utils.vigenereDecipher("A"));
    }

    @Test
    public void testVigenereDecipherNullKey() {
        DecryptionUtils utils = new DecryptionUtils(null);

        assertEquals("", utils.vigenereDecipher(""));
        assertEquals("123", utils.vigenereDecipher("123"));

        assertThrows(NullPointerException.class,
                () -> utils.vigenereDecipher("A"));
    }

    @Test
    public void testVigenereDecipherNullCiphertext() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertThrows(NullPointerException.class,
                () -> utils.vigenereDecipher(null));
    }

    @Test
    public void testRailFenceDecipherThreeRails() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("WEAREDISCOVEREDFLEEATONCE",
                utils.railFenceDecipher(
                        "WECRLTEERDSOEEFEAOCAIVDEN", 3));
    }

    @Test
    public void testRailFenceDecipherTwoRails() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("HELLOWORLD",
                utils.railFenceDecipher("HLOOLELWRD", 2));
        assertEquals("ABCDE",
                utils.railFenceDecipher("ACEBD", 2));
        assertEquals("ABCD",
                utils.railFenceDecipher("ACBD", 2));
    }

    @Test
    public void testRailFenceDecipherFourRails() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("ABCDEFGHIJ",
                utils.railFenceDecipher("AGBFHCEIDJ", 4));
    }

    @Test
    public void testRailFenceDecipherPreservesCharacters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("A B!C",
                utils.railFenceDecipher("ABC !", 2));
        assertEquals("aBcDe",
                utils.railFenceDecipher("aceBD", 2));
        assertEquals("A1B2C",
                utils.railFenceDecipher("ABC12", 2));
    }

    @Test
    public void testRailFenceDecipherEmptyInput() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("", utils.railFenceDecipher("", 2));
        assertEquals("", utils.railFenceDecipher("", 3));
        assertEquals("", utils.railFenceDecipher("", 0));
    }

    @Test
    public void testRailFenceDecipherSingleCharacter() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("A",
                utils.railFenceDecipher("A", 2));
        assertEquals("Z",
                utils.railFenceDecipher("Z", 5));
    }

    @Test
    public void testRailFenceDecipherTwoCharacters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("AB",
                utils.railFenceDecipher("AB", 2));
        assertEquals("AB",
                utils.railFenceDecipher("AB", 3));
    }

    @Test
    public void testRailFenceDecipherMoreRailsThanCharacters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertEquals("ABC",
                utils.railFenceDecipher("ABC", 5));
        assertEquals("ABCD",
                utils.railFenceDecipher("ABCD", 10));
    }

    @Test
    public void testRailFenceDecipherInvalidRails() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> utils.railFenceDecipher("ABC", 0));

        assertThrows(NegativeArraySizeException.class,
                () -> utils.railFenceDecipher("ABC", -1));

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> utils.railFenceDecipher("ABC", 1));
    }

    @Test
    public void testRailFenceDecipherNullInput() {
        DecryptionUtils utils = new DecryptionUtils("KEY");

        assertThrows(NullPointerException.class,
                () -> utils.railFenceDecipher(null, 3));
    }

    @Test
    public void testIndependentOperationsOnSameInstance() {
        DecryptionUtils utils = new DecryptionUtils("ABC");

        assertEquals("ABC",
                utils.caesarDecipher("DEF", 3));

        assertEquals("AAA",
                utils.vigenereDecipher("ABC"));

        assertEquals("ABCDE",
                utils.railFenceDecipher("ACEBD", 2));

        assertEquals("XYZ",
                utils.caesarDecipher("ABC", 3));

        assertEquals("AAA",
                utils.vigenereDecipher("ABC"));
    }

    @Test
    public void testDifferentKeysProduceDifferentVigenereResults() {
        DecryptionUtils first = new DecryptionUtils("A");
        DecryptionUtils second = new DecryptionUtils("B");

        assertEquals("ABCXYZ",
                first.vigenereDecipher("ABCXYZ"));

        assertEquals("ZABWXY",
                second.vigenereDecipher("ABCXYZ"));
    }
}
// END GeneratedTest_CE033
