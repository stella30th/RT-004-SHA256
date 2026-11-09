// BEGIN GeneratedTest_CE090
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE090 {

    @Test public void missingProductsAndOrders() {
        Warehouse w = new Warehouse();
        assertEquals(-1, w.getProductQuantity(1));
        assertNull(w.trackOrder(100));
        assertFalse(w.changeOrderStatus(100, "Delivered"));
        assertFalse(w.createOrder(100, 1, 1));
    }
    @Test public void productAddAndUpdateAccumulate() {
        Warehouse w = new Warehouse();
        w.addProduct(1, "A", 10);
        w.addProduct(1, "different", 5);
        assertEquals(15, w.getProductQuantity(1));
        w.updateProductQuantity(1, -3);
        assertEquals(12, w.getProductQuantity(1));
        w.updateProductQuantity(2, 20);
        assertEquals(-1, w.getProductQuantity(2));
    }
    @Test public void validOrdersDeductStockAndTrackStatus() {
        Warehouse w = new Warehouse();
        w.addProduct(1, "A", 10);
        assertTrue(w.createOrder(100, 1, 4));
        assertEquals(6, w.getProductQuantity(1));
        assertEquals("Shipped", w.trackOrder(100));
        assertTrue(w.changeOrderStatus(100, "Delivered"));
        assertEquals("Delivered", w.trackOrder(100));
        assertEquals(6, w.getProductQuantity(1));
    }
    @Test public void failedOrdersDoNotChangeStockOrOrders() {
        Warehouse w = new Warehouse();
        w.addProduct(1, "A", 3);
        assertFalse(w.createOrder(10, 1, 4));
        assertFalse(w.createOrder(11, 2, 1));
        assertEquals(3, w.getProductQuantity(1));
        assertNull(w.trackOrder(10));
        assertNull(w.trackOrder(11));
    }
    @Test public void duplicateOrderIdOverwritesStatus() {
        Warehouse w = new Warehouse();
        w.addProduct(1, "A", 10);
        assertTrue(w.createOrder(1, 1, 2));
        assertTrue(w.changeOrderStatus(1, "Done"));
        assertTrue(w.createOrder(1, 1, 3));
        assertEquals("Shipped", w.trackOrder(1));
        assertEquals(5, w.getProductQuantity(1));
    }
    @Test public void zeroAndNegativeOrderQuantities() {
        Warehouse w = new Warehouse();
        w.addProduct(1, "A", 5);
        assertTrue(w.createOrder(1, 1, 0));
        assertEquals(5, w.getProductQuantity(1));
        assertTrue(w.createOrder(2, 1, -3));
        assertEquals(8, w.getProductQuantity(1));
        assertTrue(w.changeOrderStatus(2, null));
        assertNull(w.trackOrder(2));
    }

    @Test public void updateCanMakeStockNegative() { Warehouse w=new Warehouse(); w.addProduct(1,"A",3); w.updateProductQuantity(1,-5); assertEquals(-2,w.getProductQuantity(1)); }
}
// END GeneratedTest_CE090
