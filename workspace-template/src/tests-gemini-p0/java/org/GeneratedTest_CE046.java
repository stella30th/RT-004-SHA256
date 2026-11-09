package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE046 {

@Test
public void testIsValidIpv4() {
    assertTrue(IpUtil.isValidIpv4("127.0.0.1"));
    assertTrue(IpUtil.isValidIpv4("192.168.0.1"));

    assertFalse(IpUtil.isValidIpv4("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    assertFalse(IpUtil.isValidIpv4("not.an.ip.address"));
    assertFalse(IpUtil.isValidIpv4("999.999.999.999"));
}

@Test
public void testIsValidIpv6() {
    assertTrue(IpUtil.isValidIpv6("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    assertTrue(IpUtil.isValidIpv6("::1"));

    assertFalse(IpUtil.isValidIpv6("192.168.1.1"));
    assertFalse(IpUtil.isValidIpv6("invalid_ipv6"));
}

@Test
public void testGetHostnameZeroAddress() {
    String hostname = IpUtil.getHostname("0.0.0.0");
    assertNotNull(hostname);
    assertFalse(hostname.isEmpty());
}

@Test
public void testGetHostnameInvalidIp() {
    assertNull(IpUtil.getHostname("invalid.domain.xyz123"));
}

}
