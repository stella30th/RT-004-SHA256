
// BEGIN GeneratedTest_CE090
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE090 {

    @Test
    public void testAddProduct() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);
        warehouse.addProduct(1, "Laptop", 5);
        assertEquals(15, warehouse.getProductQuantity(1));
        assertEquals(-1, warehouse.getProductQuantity(99));
    }

    @Test
    public void testUpdateProductQuantity() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Mouse", 10);
        warehouse.updateProductQuantity(1, 5);
        assertEquals(15, warehouse.getProductQuantity(1));
        warehouse.updateProductQuantity(1, -3);
        assertEquals(12, warehouse.getProductQuantity(1));
    }

    @Test
    public void testCreateOrder() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Keyboard", 10);
        assertTrue(warehouse.createOrder(100, 1, 4));
        assertEquals(6, warehouse.getProductQuantity(1));
        assertEquals("Shipped", warehouse.trackOrder(100));
        assertFalse(warehouse.createOrder(101, 1, 7));
        assertFalse(warehouse.createOrder(102, 99, 1));
    }

    @Test
    public void testChangeAndTrackOrderStatus() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Monitor", 5);
        assertTrue(warehouse.createOrder(200, 1, 2));
        assertTrue(warehouse.changeOrderStatus(200, "Delivered"));
        assertEquals("Delivered", warehouse.trackOrder(200));
        assertFalse(warehouse.changeOrderStatus(999, "Cancelled"));
        assertNull(warehouse.trackOrder(999));
    }
}
// END GeneratedTest_CE090
