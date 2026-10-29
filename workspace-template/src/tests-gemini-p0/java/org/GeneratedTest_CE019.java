package org.example;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GeneratedTest_CE019 {

@Test
public void testPutAndGetConvertsSnakeCaseToCamelCase() {
    CamelCaseMap map = new CamelCaseMap();
    map.put("user_first_name", "John");

    assertEquals("John", map.get("user_first_name"));
    assertEquals("John", map.get("userFirstName"));
}

@Test
public void testRemove() {
    CamelCaseMap map = new CamelCaseMap();
    map.put("item_count", 42);
    assertEquals(1, map.size());

    map.remove("item_count");
    assertNull(map.get("item_count"));
    assertEquals(0, map.size());
}

@Test
public void testKeySetAndSize() {
    CamelCaseMap map = new CamelCaseMap();
    map.put("first_key", 1);
    map.put("second_key", 2);

    assertEquals(2, map.size());
    Set<String> keys = map.keySet();
    assertTrue(keys.contains("firstKey"));
    assertTrue(keys.contains("secondKey"));
}

@Test
public void testConvertKeyNullAndPlain() {
    CamelCaseMap map = new CamelCaseMap();
    map.put("simple", "value");
    assertEquals("value", map.get("simple"));

    assertNull(map._convertKey(null));
    assertNull(map.get(null));
}

}
