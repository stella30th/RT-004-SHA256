package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE090 {

@Test
public void testAddAndGetProductQuantity() {
    Warehouse warehouse = new Warehouse();
    assertEquals(-1, warehouse.getProductQuantity(101));

    warehouse.addProduct(101, "Widget", 50);
    assertEquals(50, warehouse.getProductQuantity(101));

    // Add additional quantity
    warehouse.addProduct(101, "Widget", 25);
    assertEquals(75, warehouse.getProductQuantity(101));
}

@Test
public void testUpdateProductQuantity() {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct(102, "Gadget", 30);
    warehouse.updateProductQuantity(102, 10);
    assertEquals(40, warehouse.getProductQuantity(102));
}

@Test
public void testCreateOrderSuccessAndFailure() {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct(103, "Box", 20);

    assertTrue(warehouse.createOrder(1, 103, 15));
    assertEquals(5, warehouse.getProductQuantity(103));
    assertEquals("Shipped", warehouse.trackOrder(1));

    // Insufficient inventory
    assertFalse(warehouse.createOrder(2, 103, 10));
}

@Test
public void testChangeOrderStatusAndTrackOrder() {
    Warehouse warehouse = new Warehouse();
    warehouse.addProduct(104, "Cable", 10);
    warehouse.createOrder(5, 104, 2);

    assertEquals("Shipped", warehouse.trackOrder(5));
    assertTrue(warehouse.changeOrderStatus(5, "Delivered"));
    assertEquals("Delivered", warehouse.trackOrder(5));

    assertFalse(warehouse.changeOrderStatus(999, "Delivered"));
    assertNull(warehouse.trackOrder(999));
}

}
