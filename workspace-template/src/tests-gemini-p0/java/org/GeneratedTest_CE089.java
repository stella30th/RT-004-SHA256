package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE089 {

private static final double DELTA = 1e-4;

@Test
public void testAddItemAndDisplayItems() {
    VendingMachine vm = new VendingMachine();
    assertEquals(false, vm.displayItems());

    vm.addItem("Soda", 1.5, 5);
    Object display = vm.displayItems();
    assertTrue(display instanceof String);
    assertTrue(((String) display).contains("Soda"));
    assertTrue(((String) display).contains("$1.50"));
}

@Test
public void testInsertCoinAndBalance() {
    VendingMachine vm = new VendingMachine();
    assertEquals(0.0, vm.getBalance(), DELTA);

    double balance = vm.insertCoin(2.0);
    assertEquals(2.0, balance, DELTA);
    assertEquals(2.0, vm.getBalance(), DELTA);
}

@Test
public void testPurchaseItemSuccessAndFailure() {
    VendingMachine vm = new VendingMachine();
    vm.addItem("Chips", 1.25, 2);

    // Insufficient balance
    assertEquals(false, vm.purchaseItem("Chips"));

    // Sufficient balance
    vm.insertCoin(2.0);
    Object remainingBalance = vm.purchaseItem("Chips");
    assertTrue(remainingBalance instanceof Double);
    assertEquals(0.75, (Double) remainingBalance, DELTA);
    assertEquals(1, vm.getInventory().get("Chips").getQuantity());

    // Purchase non-existent item
    assertEquals(false, vm.purchaseItem("Unknown"));
}

@Test
public void testRestockItem() {
    VendingMachine vm = new VendingMachine();
    assertFalse(vm.restockItem("Candy", 10));

    vm.addItem("Candy", 0.75, 5);
    assertTrue(vm.restockItem("Candy", 10));
    assertEquals(15, vm.getInventory().get("Candy").getQuantity());
}

}
