package org.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class GeneratedTest_CE037 {
    @Test
    public void testCaesarCipherBasicAndWrapAround() {
        EncryptionUtils utils = new EncryptionUtils("key");
        String result = utils.caesarCipher("Hello, World! 123", 3);
        assertEquals("Khoor, Zruog! 123", result);

        String wrapResult = utils.caesarCipher("xyzXYZ", 3);
        assertEquals("abcABC", wrapResult);
    }

    @Test
    public void testCaesarCipherShiftZeroAndEmpty() {
        EncryptionUtils utils = new EncryptionUtils("key");
        assertEquals("NoChangeHere 99", utils.caesarCipher("NoChangeHere 99", 0));
        assertEquals("abcABC", utils.caesarCipher("abcABC", 26));
        assertEquals("", utils.caesarCipher("", 5));
    }

    @Test
    public void testVigenereCipherBasic() {
        EncryptionUtils utils = new EncryptionUtils("key");
        String encrypted = utils.vigenereCipher("Hello, World!");
        assertEquals("Rijvs, Uyvjn!", encrypted);

        EncryptionUtils upperKeyUtils = new EncryptionUtils("KEY");
        String encryptedUpperKey = upperKeyUtils.vigenereCipher("Hello, World!");
        assertEquals("Rijvs, Uyvjn!", encryptedUpperKey);
    }

    @Test
    public void testVigenereCipherIdentityAndEmpty() {
        EncryptionUtils utils = new EncryptionUtils("a");
        assertEquals("Test Case 123!", utils.vigenereCipher("Test Case 123!"));
        assertEquals("", utils.vigenereCipher(""));
    }

    @Test
    public void testRailFenceCipherTwoRails() {
        EncryptionUtils utils = new EncryptionUtils("key");
        String encrypted = utils.railFenceCipher("HELLOWORLD", 2);
        assertEquals("HLOOLELWRD", encrypted);
    }

    @Test
    public void testRailFenceCipherThreeRailsAndEmpty() {
        EncryptionUtils utils = new EncryptionUtils("key");
        String encrypted = utils.railFenceCipher("HELLOWORLD", 3);
        assertEquals("HOLELWRDLO", encrypted);
        assertEquals("", utils.railFenceCipher("", 3));
    }
}