// BEGIN GeneratedTest_CE090
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE090 {

    @Test
    public void testInitialWarehouseState() {
        Warehouse warehouse = new Warehouse();

        assertEquals(-1, warehouse.getProductQuantity(1));
        assertNull(warehouse.trackOrder(100));
        assertFalse(warehouse.changeOrderStatus(100, "Delivered"));
        assertFalse(warehouse.createOrder(100, 1, 1));
    }

    @Test
    public void testAddProductsAndAccumulateQuantity() {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(1, "Laptop", 10);
        warehouse.addProduct(2, "Mouse", 20);
        warehouse.addProduct(1, "Different Name", 5);

        assertEquals(15, warehouse.getProductQuantity(1));
        assertEquals(20, warehouse.getProductQuantity(2));
        assertEquals(-1, warehouse.getProductQuantity(3));
    }

    @Test
    public void testUpdateProductQuantityAddsRatherThanReplaces() {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(1, "Laptop", 10);
        warehouse.updateProductQuantity(1, 5);
        assertEquals(15, warehouse.getProductQuantity(1));

        warehouse.updateProductQuantity(1, -3);
        assertEquals(12, warehouse.getProductQuantity(1));

        warehouse.updateProductQuantity(1, 0);
        assertEquals(12, warehouse.getProductQuantity(1));

        warehouse.updateProductQuantity(99, 10);
        assertEquals(-1, warehouse.getProductQuantity(99));
    }

    @Test
    public void testCreateOrderDeductsInventory() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);

        assertTrue(warehouse.createOrder(100, 1, 4));
        assertEquals(6, warehouse.getProductQuantity(1));
        assertEquals("Shipped", warehouse.trackOrder(100));

        assertTrue(warehouse.createOrder(101, 1, 6));
        assertEquals(0, warehouse.getProductQuantity(1));
        assertEquals("Shipped", warehouse.trackOrder(101));
    }

    @Test
    public void testInsufficientStockRejectsOrder() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 5);

        assertFalse(warehouse.createOrder(100, 1, 6));
        assertEquals(5, warehouse.getProductQuantity(1));
        assertNull(warehouse.trackOrder(100));

        assertFalse(warehouse.createOrder(101, 99, 1));
        assertNull(warehouse.trackOrder(101));
    }

    @Test
    public void testZeroQuantityOrder() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);

        assertTrue(warehouse.createOrder(100, 1, 0));
        assertEquals(10, warehouse.getProductQuantity(1));
        assertEquals("Shipped", warehouse.trackOrder(100));
    }

    @Test
    public void testNegativeQuantityOrderIncreasesInventory() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);

        assertTrue(warehouse.createOrder(100, 1, -3));
        assertEquals(13, warehouse.getProductQuantity(1));
        assertEquals("Shipped", warehouse.trackOrder(100));
    }

    @Test
    public void testChangeOrderStatus() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);
        assertTrue(warehouse.createOrder(100, 1, 3));

        assertTrue(warehouse.changeOrderStatus(100, "Delivered"));
        assertEquals("Delivered", warehouse.trackOrder(100));

        assertTrue(warehouse.changeOrderStatus(100, "Cancelled"));
        assertEquals("Cancelled", warehouse.trackOrder(100));

        assertEquals(7, warehouse.getProductQuantity(1));
    }

    @Test
    public void testChangeOrderStatusToNullAndEmptyString() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);
        assertTrue(warehouse.createOrder(100, 1, 2));

        assertTrue(warehouse.changeOrderStatus(100, null));
        assertNull(warehouse.trackOrder(100));

        assertTrue(warehouse.changeOrderStatus(100, ""));
        assertEquals("", warehouse.trackOrder(100));
    }

    @Test
    public void testMissingOrderStatusChangeFails() {
        Warehouse warehouse = new Warehouse();

        assertFalse(warehouse.changeOrderStatus(999, "Delivered"));
        assertNull(warehouse.trackOrder(999));
    }

    @Test
    public void testDuplicateOrderIdOverwritesStatus() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 10);
        warehouse.addProduct(2, "Mouse", 20);

        assertTrue(warehouse.createOrder(100, 1, 3));
        assertTrue(warehouse.changeOrderStatus(100, "Delivered"));

        assertTrue(warehouse.createOrder(100, 2, 5));

        assertEquals("Shipped", warehouse.trackOrder(100));
        assertEquals(7, warehouse.getProductQuantity(1));
        assertEquals(15, warehouse.getProductQuantity(2));
    }

    @Test
    public void testFailedDuplicateOrderPreservesExistingStatus() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 5);

        assertTrue(warehouse.createOrder(100, 1, 3));
        assertTrue(warehouse.changeOrderStatus(100, "Delivered"));

        assertFalse(warehouse.createOrder(100, 1, 10));
        assertEquals("Delivered", warehouse.trackOrder(100));
        assertEquals(2, warehouse.getProductQuantity(1));
    }

    @Test
    public void testMultipleProductsAndOrdersAreIndependent() {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(1, "Laptop", 10);
        warehouse.addProduct(2, "Mouse", 20);

        assertTrue(warehouse.createOrder(100, 1, 4));
        assertTrue(warehouse.createOrder(101, 2, 7));

        assertEquals(6, warehouse.getProductQuantity(1));
        assertEquals(13, warehouse.getProductQuantity(2));

        assertTrue(warehouse.changeOrderStatus(100, "Delivered"));
        assertEquals("Delivered", warehouse.trackOrder(100));
        assertEquals("Shipped", warehouse.trackOrder(101));
    }

    @Test
    public void testRestockingAllowsPreviouslyFailedOrder() {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(1, "Laptop", 3);

        assertFalse(warehouse.createOrder(100, 1, 5));

        warehouse.updateProductQuantity(1, 4);

        assertTrue(warehouse.createOrder(100, 1, 5));
        assertEquals(2, warehouse.getProductQuantity(1));
        assertEquals("Shipped", warehouse.trackOrder(100));
    }

    @Test
    public void testZeroAndNegativeProductIds() {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(0, "Zero", 5);
        warehouse.addProduct(-1, "Negative", 8);

        assertEquals(5, warehouse.getProductQuantity(0));
        assertEquals(8, warehouse.getProductQuantity(-1));

        assertTrue(warehouse.createOrder(-100, 0, 2));
        assertTrue(warehouse.createOrder(0, -1, 3));

        assertEquals(3, warehouse.getProductQuantity(0));
        assertEquals(5, warehouse.getProductQuantity(-1));
        assertEquals("Shipped", warehouse.trackOrder(-100));
        assertEquals("Shipped", warehouse.trackOrder(0));
    }

    @Test
    public void testNegativeInventoryQuantity() {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(1, "Laptop", -5);
        assertEquals(-5, warehouse.getProductQuantity(1));

        assertFalse(warehouse.createOrder(100, 1, 0));
        assertNull(warehouse.trackOrder(100));

        warehouse.updateProductQuantity(1, 10);
        assertEquals(5, warehouse.getProductQuantity(1));

        assertTrue(warehouse.createOrder(100, 1, 5));
        assertEquals(0, warehouse.getProductQuantity(1));
    }

    @Test
    public void testIntegerOverflowInQuantityAddition() {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(1, "Laptop", Integer.MAX_VALUE);
        warehouse.updateProductQuantity(1, 1);

        assertEquals(Integer.MIN_VALUE, warehouse.getProductQuantity(1));
    }

    @Test
    public void testIndependentWarehouseInstances() {
        Warehouse first = new Warehouse();
        Warehouse second = new Warehouse();

        first.addProduct(1, "Laptop", 10);
        second.addProduct(1, "Laptop", 20);

        assertTrue(first.createOrder(100, 1, 4));

        assertEquals(6, first.getProductQuantity(1));
        assertEquals(20, second.getProductQuantity(1));

        assertEquals("Shipped", first.trackOrder(100));
        assertNull(second.trackOrder(100));
        assertFalse(second.changeOrderStatus(100, "Delivered"));
    }
}
// END GeneratedTest_CE090