package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE074 {

@Test
public void testSelect() {
    SQLGenerator gen = new SQLGenerator("users");
    String[] fields = {"name", "email"};

    assertEquals("SELECT name, email FROM users WHERE id = 1;", gen.select(fields, "id = 1"));
    assertEquals("SELECT * FROM users;", gen.select(null, null));
}

@Test
public void testInsert() {
    SQLGenerator gen = new SQLGenerator("customers");
    Map<String, String> data = new HashMap<>();
    data.put("name", "John");
    data.put("age", "30");

    // TreeMap ensures alphabetical ordering: age, name
    String expected = "INSERT INTO customers (age, name) VALUES ('30', 'John');";
    assertEquals(expected, gen.insert(data));
}

@Test
public void testUpdateAndDelete() {
    SQLGenerator gen = new SQLGenerator("products");
    Map<String, String> data = new HashMap<>();
    data.put("price", "99");

    assertEquals("UPDATE products SET price = '99' WHERE id = 5;", gen.update(data, "id = 5"));
    assertEquals("DELETE FROM products WHERE id = 5;", gen.delete("id = 5"));
}

@Test
public void testSpecialSelects() {
    SQLGenerator gen = new SQLGenerator("students");
    assertEquals("SELECT * FROM students WHERE age < 18 AND gender = 'female';", gen.selectFemaleUnderAge(18));
    assertEquals("SELECT * FROM students WHERE age BETWEEN 18 AND 25;", gen.selectByAgeRange(18, 25));
}

}
