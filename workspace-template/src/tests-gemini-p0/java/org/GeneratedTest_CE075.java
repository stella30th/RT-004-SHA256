package org.example;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE075 {

@Test
public void testSelectWithColumnsAndWhere() {
    Map<String, String> where = new LinkedHashMap<>();
    where.put("status", "active");
    where.put("role", "admin");

    String query = SQLQueryBuilder.select("users", "id, name", where);
    assertEquals("SELECT id, name FROM users WHERE status='active' AND role='admin'", query);

    String starQuery = SQLQueryBuilder.select("users", (String) null, null);
    assertEquals("SELECT * FROM users", starQuery);
}

@Test
public void testInsert() {
    Map<String, String> data = new LinkedHashMap<>();
    data.put("name", "Alice");
    data.put("city", "Boston");

    String query = SQLQueryBuilder.insert("members", data);
    assertEquals("INSERT INTO members (name, city) VALUES ('Alice', 'Boston')", query);
}

@Test
public void testDelete() {
    Map<String, String> where = new LinkedHashMap<>();
    where.put("id", "10");

    String query = SQLQueryBuilder.delete("orders", where);
    assertEquals("DELETE FROM orders WHERE id='10'", query);

    assertEquals("DELETE FROM orders", SQLQueryBuilder.delete("orders", null));
}

@Test
public void testUpdate() {
    Map<String, String> data = new LinkedHashMap<>();
    data.put("email", "alice@example.com");

    Map<String, String> where = new LinkedHashMap<>();
    where.put("username", "alice");

    String query = SQLQueryBuilder.update("accounts", data, where);
    assertEquals("UPDATE accounts SET email='alice@example.com' WHERE username='alice'", query);
}

}
