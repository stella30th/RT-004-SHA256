package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE033 {

@Test public void caesarPreservesPunctuationAndCase() {
 DecryptionUtils d=new DecryptionUtils("key");
 assertEquals("Abc-Z!",d.caesarDecipher("Def-C!",3));
 assertEquals("Hello",d.caesarDecipher("Hello",26));
}
@Test public void vigenereUsesLetterOnlyKeyAdvance() {
 DecryptionUtils d=new DecryptionUtils("b");
 assertEquals("Ab-c!",d.vigenereDecipher("Bc-d!"));
}
@Test public void railFenceUsesSourceColumnOrdering() {
 DecryptionUtils d=new DecryptionUtils("x");
 assertEquals("ADBEC",d.railFenceDecipher("ABCDE",2));
 assertEquals("",d.railFenceDecipher("",3));
}

}
