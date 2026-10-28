package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE065 {

@Test public void exactSexDomainIsCaseSensitive() {
 assertEquals("Man",new PersonRequest("A","Man","01234567890").getSex());
 assertEquals("Woman",new PersonRequest("A","Woman","01234567890").getSex());
 assertEquals("UGM",new PersonRequest("A","UGM","01234567890").getSex());
 assertNull(new PersonRequest("A","male","01234567890").getSex());
}
@Test public void validatesNameAndPhone() {
 PersonRequest ok=new PersonRequest("Ada","Man","01234567890");
 assertEquals("Ada",ok.getName());assertEquals("01234567890",ok.getPhoneNumber());
 assertNull(new PersonRequest("","Man","01234567890").getName());
 assertNull(new PersonRequest("a","Man","123").getPhoneNumber());
 assertNull(new PersonRequest("a","Man","abcdefghijk").getPhoneNumber());
}

}
