package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE045 {

@Test
public void testValidIPv4() {
    IPAddress ip = new IPAddress("192.168.1.1");
    assertTrue(ip.isValid());
    assertEquals(Arrays.asList("192", "168", "1", "1"), ip.getOctets());
    assertEquals("11000000.10101000.00000001.00000001", ip.getBinary());
}

@Test
public void testInvalidOctetCount() {
    IPAddress ipTooFew = new IPAddress("192.168.1");
    assertFalse(ipTooFew.isValid());
    assertTrue(ipTooFew.getOctets().isEmpty());
    assertEquals("", ipTooFew.getBinary());

    IPAddress ipTooMany = new IPAddress("192.168.1.1.5");
    assertFalse(ipTooMany.isValid());
}

@Test
public void testInvalidOctetValues() {
    IPAddress ipNegative = new IPAddress("192.168.-1.1");
    assertFalse(ipNegative.isValid());

    IPAddress ipTooHigh = new IPAddress("192.168.1.256");
    assertFalse(ipTooHigh.isValid());

    IPAddress ipLetters = new IPAddress("192.168.abc.1");
    assertFalse(ipLetters.isValid());
}

@Test
public void testBoundaryValidValues() {
    IPAddress ipMin = new IPAddress("0.0.0.0");
    assertTrue(ipMin.isValid());
    assertEquals("00000000.00000000.00000000.00000000", ipMin.getBinary());

    IPAddress ipMax = new IPAddress("255.255.255.255");
    assertTrue(ipMax.isValid());
    assertEquals("11111111.11111111.11111111.11111111", ipMax.getBinary());
}

}
