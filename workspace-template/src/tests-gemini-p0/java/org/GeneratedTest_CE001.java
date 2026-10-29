package org.example;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE001 {

@Test
public void testIsStartWithApiOrLogin() {
    AccessGatewayFilter filter = new AccessGatewayFilter();
    assertTrue(filter.isStartWith("/api/v1/users"));
    assertTrue(filter.isStartWith("/login"));
    assertFalse(filter.isStartWith("/dashboard"));
    assertFalse(filter.isStartWith("/admin/login"));
}

@Test
public void testFilterBypassesForWhitelistedPrefix() {
    AccessGatewayFilter filter = new AccessGatewayFilter();
    Map<String, Object> request = new HashMap<>();
    request.put("path", "/api/data");
    request.put("method", "GET");

    boolean result = filter.filter(request);
    assertTrue(result);
}

@Test
public void testFilterAllowsHighLevelUserWithValidJwt() {
    AccessGatewayFilter filter = new AccessGatewayFilter();

    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String username = "alice";

    Map<String, Object> user = new HashMap<>();
    user.put("name", username);
    user.put("level", 3);
    user.put("address", "127.0.0.1");

    Map<String, Object> token = new HashMap<>();
    token.put("user", user);
    token.put("jwt", username + today);

    Map<String, Object> headers = new HashMap<>();
    headers.put("Authorization", token);

    Map<String, Object> request = new HashMap<>();
    request.put("path", "/secure/profile");
    request.put("method", "GET");
    request.put("headers", headers);

    boolean result = filter.filter(request);
    assertTrue(result);
}

@Test
public void testFilterRejectsLowLevelUser() {
    AccessGatewayFilter filter = new AccessGatewayFilter();

    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String username = "bob";

    Map<String, Object> user = new HashMap<>();
    user.put("name", username);
    user.put("level", 2);
    user.put("address", "127.0.0.1");

    Map<String, Object> token = new HashMap<>();
    token.put("user", user);
    token.put("jwt", username + today);

    Map<String, Object> headers = new HashMap<>();
    headers.put("Authorization", token);

    Map<String, Object> request = new HashMap<>();
    request.put("path", "/secure/profile");
    request.put("method", "GET");
    request.put("headers", headers);

    boolean result = filter.filter(request);
    assertFalse(result);
}

@Test
public void testGetJwtUserReturnsNullWhenExpired() {
    AccessGatewayFilter filter = new AccessGatewayFilter();

    String expiredDate = LocalDate.now().minusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String username = "charlie";

    Map<String, Object> user = new HashMap<>();
    user.put("name", username);
    user.put("level", 5);

    Map<String, Object> token = new HashMap<>();
    token.put("user", user);
    token.put("jwt", username + expiredDate);

    Map<String, Object> headers = new HashMap<>();
    headers.put("Authorization", token);

    Map<String, Object> request = new HashMap<>();
    request.put("path", "/restricted/resource");
    request.put("headers", headers);

    Map<String, Object> jwtUser = filter.getJwtUser(request);
    assertNull(jwtUser);
    assertFalse(filter.filter(request));
}

}
