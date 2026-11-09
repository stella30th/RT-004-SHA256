// BEGIN GeneratedTest_CE033
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE033 {

    @Test public void caesarHandlesCaseWrapAndPunctuation() {
        DecryptionUtils d = new DecryptionUtils("key");
        assertEquals("Hello, World!", d.caesarDecipher("Khoor, Zruog!", 3));
        assertEquals("XyZ", d.caesarDecipher("AbC", 3));
        assertEquals("Hello", d.caesarDecipher("Hello", 26));
        assertEquals("", d.caesarDecipher("", 7));
    }
    @Test public void caesarAllowsNegativeAndLargeShifts() {
        DecryptionUtils d = new DecryptionUtils("x");
        assertEquals("bcd", d.caesarDecipher("abc", -1));
        assertEquals("abc", d.caesarDecipher("abc", 52));
        assertEquals("a1 ?", d.caesarDecipher("b1 ?", 1));
    }
    @Test public void vigenereUsesKeyAndSkipsNonletters() {
        DecryptionUtils d = new DecryptionUtils("LEMON");
        assertEquals("ATTACKATDAWN", d.vigenereDecipher("LXFOPVEFRNHR"));
        assertEquals("ATTACK AT DAWN!", d.vigenereDecipher("LXFOPV EF RNHR!"));
        assertEquals("", d.vigenereDecipher(""));
    }
    @Test public void vigenerePreservesCase() {
        DecryptionUtils d = new DecryptionUtils("b");
        assertEquals("AbC 123!", d.vigenereDecipher("BcD 123!"));
        assertEquals("!?", d.vigenereDecipher("!?"));
    }
    @Test public void railFenceDecodesKnownExamples() {
        DecryptionUtils d = new DecryptionUtils("x");
        assertEquals("WEAREDISCOVEREDFLEEATONCE", d.railFenceDecipher("WECRLTEERDSOEEFEAOCAIVDEN", 3));
        assertEquals("HELLOWORLD", d.railFenceDecipher("HLOOLELWRD", 2));
        assertEquals("", d.railFenceDecipher("", 2));
    }
    @Test(expected = ArithmeticException.class) public void vigenereEmptyKeyWithLetterThrows() {
        new DecryptionUtils("").vigenereDecipher("A");
    }

    @Test public void caesarShiftOne() { assertEquals("abc", new DecryptionUtils("x").caesarDecipher("bcd", 1)); }
    @Test public void railFenceOneRailBehavior() {
        DecryptionUtils d = new DecryptionUtils("key");
        assertEquals("", d.railFenceDecipher("", 1));
        assertEquals("A", d.railFenceDecipher("A", 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> d.railFenceDecipher("AB", 1));
    }
}
// END GeneratedTest_CE033
