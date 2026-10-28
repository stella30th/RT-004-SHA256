package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE090 {

@Test public void updatesAreAdditiveNotReplacement() {
 Warehouse w=new Warehouse();
 assertEquals(-1,w.getProductQuantity(7));
 w.addProduct(7,"item",10);w.updateProductQuantity(7,4);
 assertEquals(14,w.getProductQuantity(7));
 w.addProduct(7,"renamed",3); assertEquals(17,w.getProductQuantity(7));
 w.updateProductQuantity(99,100);assertEquals(-1,w.getProductQuantity(99));
}
@Test public void ordersDebitInventoryAndStartShipped() {
 Warehouse w=new Warehouse();
 w.addProduct(1,"x",5);
 assertFalse(w.createOrder(2,1,6));assertNull(w.trackOrder(2));
 assertTrue(w.createOrder(2,1,3));
 assertEquals(2,w.getProductQuantity(1));
 assertEquals("Shipped",w.trackOrder(2));
 assertTrue(w.changeOrderStatus(2,"Delivered"));
 assertEquals("Delivered",w.trackOrder(2));
 assertFalse(w.changeOrderStatus(99,"Cancelled"));
}

}
