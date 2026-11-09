package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE063 {

private static final double DELTA = 1e-4;

@Test
public void testAddDishSuccessAndStockReduction() {
    Order order = new Order();
    Map<String, Object> menuDish = new HashMap<>();
    menuDish.put("dish", "Pizza");
    menuDish.put("price", 10.0);
    menuDish.put("count", 5);
    order.menu.add(menuDish);

    Map<String, Object> orderDish = new HashMap<>();
    orderDish.put("dish", "Pizza");
    orderDish.put("price", 10.0);
    orderDish.put("count", 2);

    assertTrue(order.addDish(orderDish));
    assertEquals(3, menuDish.get("count"));
    assertEquals(1, order.selectedDishes.size());
}

@Test
public void testAddDishFailsWhenOutOfStockOrMissingFields() {
    Order order = new Order();
    Map<String, Object> menuDish = new HashMap<>();
    menuDish.put("dish", "Burger");
    menuDish.put("price", 5.0);
    menuDish.put("count", 1);
    order.menu.add(menuDish);

    Map<String, Object> orderDishExcess = new HashMap<>();
    orderDishExcess.put("dish", "Burger");
    orderDishExcess.put("price", 5.0);
    orderDishExcess.put("count", 3);
    assertFalse(order.addDish(orderDishExcess));

    Map<String, Object> incomplete = new HashMap<>();
    incomplete.put("dish", "Burger");
    assertFalse(order.addDish(incomplete));
    assertFalse(order.addDish(null));
}

@Test
public void testCalculateTotalWithSalesDiscount() {
    Order order = new Order();
    Map<String, Object> dish1 = new HashMap<>();
    dish1.put("dish", "Pasta");
    dish1.put("price", 15.0);
    dish1.put("count", 2);
    order.selectedDishes.add(dish1);

    // Without discount -> 15.0 * 2 * 1.0 = 30.0
    assertEquals(30.0, order.calculateTotal(), DELTA);

    // Apply 20% off (multiplier 0.8)
    order.sales.put("Pasta", 0.8);
    assertEquals(24.0, order.calculateTotal(), DELTA);
}

@Test
public void testCheckoutClearsSelectedDishes() {
    Order order = new Order();
    // Empty checkout returns false
    assertEquals(false, order.checkout());

    Map<String, Object> dish = new HashMap<>();
    dish.put("dish", "Salad");
    dish.put("price", 8.0);
    dish.put("count", 1);
    order.selectedDishes.add(dish);

    Object total = order.checkout();
    assertTrue(total instanceof Double);
    assertEquals(8.0, (Double) total, DELTA);
    assertTrue(order.selectedDishes.isEmpty());
}

}
