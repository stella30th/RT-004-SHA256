package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE034 {

private static final double DELTA = 1e-4;

@Test
public void testTotalWithoutPromotion() {
    DiscountStrategy.Customer customer = new DiscountStrategy.Customer("Alice", 500);
    DiscountStrategy.Cart cart = new DiscountStrategy.Cart(
            new DiscountStrategy.Product("Apple", 5, 2.0),
            new DiscountStrategy.Product("Banana", 10, 1.0)
    );

    DiscountStrategy strategy = new DiscountStrategy(customer, cart, null);
    assertEquals(20.0, strategy.total(), DELTA);
    assertEquals(20.0, strategy.due(), DELTA);
}

@Test
public void testFidelityPromotion() {
    DiscountStrategy.Customer highFidelity = new DiscountStrategy.Customer("Bob", 1200);
    DiscountStrategy.Cart cart = new DiscountStrategy.Cart(
            new DiscountStrategy.Product("Laptop", 1, 1000.0)
    );

    DiscountStrategy strategy = new DiscountStrategy(highFidelity, cart, DiscountStrategy.FIDELITY_PROMO);
    assertEquals(1000.0, strategy.total(), DELTA);
    assertEquals(50.0, strategy.promotion(strategy), DELTA); // 5% discount
    assertEquals(950.0, strategy.due(), DELTA);

    DiscountStrategy.Customer lowFidelity = new DiscountStrategy.Customer("Charlie", 800);
    DiscountStrategy strategyLow = new DiscountStrategy(lowFidelity, cart, DiscountStrategy.FIDELITY_PROMO);
    assertEquals(0.0, strategyLow.promotion(strategyLow), DELTA);
    assertEquals(1000.0, strategyLow.due(), DELTA);
}

@Test
public void testBulkItemPromotion() {
    DiscountStrategy.Customer customer = new DiscountStrategy.Customer("David", 100);
    DiscountStrategy.Cart cart = new DiscountStrategy.Cart(
            new DiscountStrategy.Product("Pen", 25, 2.0), // 25 >= 20 -> 25 * 2.0 * 0.1 = 5.0
            new DiscountStrategy.Product("Notebook", 5, 10.0) // 5 < 20 -> no discount
    );

    DiscountStrategy strategy = new DiscountStrategy(customer, cart, DiscountStrategy.BULK_ITEM_PROMO);
    assertEquals(100.0, strategy.total(), DELTA);
    assertEquals(5.0, strategy.promotion(strategy), DELTA);
    assertEquals(95.0, strategy.due(), DELTA);
}

@Test
public void testLargeOrderPromotion() {
    DiscountStrategy.Customer customer = new DiscountStrategy.Customer("Eve", 100);
    DiscountStrategy.Cart cart = new DiscountStrategy.Cart();
    for (int i = 0; i < 10; i++) {
        cart.addProduct(new DiscountStrategy.Product("Item" + i, 1, 10.0));
    }

    DiscountStrategy strategy = new DiscountStrategy(customer, cart, DiscountStrategy.LARGE_ORDER_PROMO);
    assertEquals(100.0, strategy.total(), DELTA);
    assertEquals(7.0, strategy.promotion(strategy), DELTA); // 7% discount
    assertEquals(93.0, strategy.due(), DELTA);
}

}
