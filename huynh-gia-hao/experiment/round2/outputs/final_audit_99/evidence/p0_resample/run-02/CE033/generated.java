// BEGIN GeneratedTest_CE033
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE033 {

    @Test
    public void testCaesarDecipher() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("Hello, World!",
                utils.caesarDecipher("Khoor, Zruog!", 3));
        assertEquals("Abc XYZ",
                utils.caesarDecipher("Abc XYZ", 0));
    }

    @Test
    public void testCaesarDecipherWraparound() {
        DecryptionUtils utils = new DecryptionUtils("KEY");
        assertEquals("XyZ",
                utils.caesarDecipher("AbC", 3));
        assertEquals("Hello",
                utils.caesarDecipher("Hello", 26));
        assertEquals("",
                utils.caesarDecipher("", 5));
    }

    @Test
    public void testVigenereDecipher() {
        DecryptionUtils utils = new DecryptionUtils("LEMON");
        assertEquals("ATTACKATDAWN",
                utils.vigenereDecipher("LXFOPVEFRNHR"));
        assertEquals("ATTACK AT DAWN!",
                utils.vigenereDecipher("LXFOPV EF RNHR!"));
    }

    @Test
    public void testVigenereDecipherPreservesCaseAndSymbols() {
        DecryptionUtils utils = new DecryptionUtils("b");
        assertEquals("AbC 123!",
                utils.vigenereDecipher("BcD 123!"));
        assertEquals("",
                utils.vigenereDecipher(""));
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
        assertEquals("",
                utils.railFenceDecipher("", 2));
    }
}
// END GeneratedTest_CE033