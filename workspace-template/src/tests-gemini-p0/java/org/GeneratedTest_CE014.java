package org.example;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE014 {

@Test
public void testAddBookAndQuantity() {
    BookManagement bm = new BookManagement();
    bm.addBook("Clean Code", 5);
    assertEquals(5, bm.viewBookQuantity("Clean Code"));

    bm.addBook("Clean Code", 3);
    assertEquals(8, bm.viewBookQuantity("Clean Code"));
    assertEquals(0, bm.viewBookQuantity("Unknown Book"));
}

@Test
public void testRemoveBookPartialAndFull() throws Exception {
    BookManagement bm = new BookManagement();
    bm.addBook("Refactoring", 10);

    bm.removeBook("Refactoring", 4);
    assertEquals(6, bm.viewBookQuantity("Refactoring"));

    bm.removeBook("Refactoring", 6);
    assertEquals(0, bm.viewBookQuantity("Refactoring"));
    assertFalse(bm.viewInventory().containsKey("Refactoring"));
}

@Test(expected = Exception.class)
public void testRemoveBookThrowsWhenBookDoesNotExist() throws Exception {
    BookManagement bm = new BookManagement();
    bm.removeBook("Nonexistent", 1);
}

@Test(expected = Exception.class)
public void testRemoveBookThrowsWhenQuantityInsufficient() throws Exception {
    BookManagement bm = new BookManagement();
    bm.addBook("Effective Java", 2);
    bm.removeBook("Effective Java", 5);
}

@Test
public void testViewInventoryReturnsCopy() {
    BookManagement bm = new BookManagement();
    bm.addBook("Design Patterns", 4);

    Map<String, Integer> inventory = bm.viewInventory();
    assertEquals(1, inventory.size());
    assertEquals(Integer.valueOf(4), inventory.get("Design Patterns"));

    inventory.put("Design Patterns", 100);
    assertEquals(4, bm.viewBookQuantity("Design Patterns"));
}

}
