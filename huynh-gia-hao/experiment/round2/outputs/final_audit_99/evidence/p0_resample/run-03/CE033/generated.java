package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE033 {
    @Test
    public void caesarDeciphersMixedCaseLetters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("AbC xYz", utils.caesarDecipher("DeF aBc", 3));
    }

    @Test
    public void caesarWrapsAroundAlphabet() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("Zz Aa", utils.caesarDecipher("Aa Bb", 1));
    }

    @Test
    public void caesarKeepsNonLetters() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("Hi, 123!", utils.caesarDecipher("Ij, 123!", 1));
    }

    @Test
    public void caesarNormalizesShiftModuloTwentySix() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("Abc", utils.caesarDecipher("Bcd", 27));
        assertEquals("Bcd", utils.caesarDecipher("Bcd", 52));
    }

    @Test
    public void caesarAcceptsEmptyCiphertext() {
        assertEquals("", new DecryptionUtils("KEY").caesarDecipher("", 4));
    }

    @Test
    public void vigenereDeciphersRepeatingKey() {
        DecryptionUtils utils = new DecryptionUtils("LEMON");
        assertEquals("ATTACKATDAWN", utils.vigenereDecipher("LXFOPVEFRNHR"));
    }

    @Test
    public void vigenerePreservesCaseAndSkipsPunctuationInKeyIndex() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("AbC!", utils.vigenereDecipher("KfA!"));
    }

    @Test
    public void vigenereWithSingleAKeyLeavesAsciiLettersUnchanged() {
        DecryptionUtils utils = new DecryptionUtils("a");
        assertEquals("Hello, World! 123", utils.vigenereDecipher("Hello, World! 123"));
    }

    @Test
    public void vigenereEmptyCiphertextReturnsEmptyString() {
        assertEquals("", new DecryptionUtils("KEY").vigenereDecipher(""));
    }

    @Test
    public void railFenceTwoRailsDeciphersAlternatingCharacters() {
        DecryptionUtils utils = new DecryptionUtils("ignored");
        assertEquals("ABCDEF", utils.railFenceDecipher("ACEBDF", 2));
    }

    @Test
    public void railFenceThreeRailsDeciphersZigzag() {
        DecryptionUtils utils = new DecryptionUtils("ignored");
        assertEquals("ABCDEFG", utils.railFenceDecipher("AEBDFCG", 3));
    }

    @Test
    public void railFenceHandlesSingleCharacterAndEmptyText() {
        DecryptionUtils utils = new DecryptionUtils("ignored");
        assertEquals("X", utils.railFenceDecipher("X", 3));
        assertEquals("", utils.railFenceDecipher("", 3));
    }

    @Test
    public void railFenceHandlesClassicThreeRailExample() {
        DecryptionUtils utils = new DecryptionUtils("ignored");
        assertEquals("WEAREDISCOVEREDFLEEATONCE",
                utils.railFenceDecipher("WECRLTEERDSOEEFEAOCAIVDEN", 3));
    }
}
