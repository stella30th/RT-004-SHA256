package org.example;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE070 {

private static final double DELTA = 1e-4;

@Test
public void testAddItemNewAndExisting() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Apple", 1.5, 3);

    Map<String, ShoppingCart.Item> items = cart.viewItems();
    assertEquals(1, items.size());
    assertEquals(3, items.get("Apple").getQuantity());
    assertEquals(1.5, items.get("Apple").getPrice(), DELTA);

    // Adding more to existing item
    cart.addItem("Apple", 2.0, 2);
    assertEquals(5, cart.viewItems().get("Apple").getQuantity());
    assertEquals(2.0, cart.viewItems().get("Apple").getPrice(), DELTA);
}

@Test
public void testRemoveItemPartialAndTotal() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Banana", 0.5, 10);

    cart.removeItem("Banana", 4);
    assertEquals(6, cart.viewItems().get("Banana").getQuantity());

    cart.removeItem("Banana", 6);
    assertFalse(cart.viewItems().containsKey("Banana"));
}

@Test
public void testTotalPrice() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Book", 12.0, 2);  // 24.0
    cart.addItem("Pen", 1.5, 4);    // 6.0

    assertEquals(30.0, cart.totalPrice(), DELTA);
}

@Test
public void testItemEqualsAndHashCode() {
    ShoppingCart.Item i1 = new ShoppingCart.Item(10.0, 2);
    ShoppingCart.Item i2 = new ShoppingCart.Item(10.0, 2);
    ShoppingCart.Item i3 = new ShoppingCart.Item(15.0, 2);

    assertEquals(i1, i2);
    assertNotEquals(i1, i3);
    assertEquals(i1.hashCode(), i2.hashCode());
}

}
