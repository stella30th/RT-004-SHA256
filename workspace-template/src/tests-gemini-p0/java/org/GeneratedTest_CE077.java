package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE077 {

private static final double DELTA = 1e-4;

@Test
public void testAddAndRemoveStock() {
    StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);
    StockPortfolioTracker.Stock apple = new StockPortfolioTracker.Stock("AAPL", 150.0, 2);

    tracker.addStock(apple);
    assertEquals(1, tracker.getPortfolio().size());
    assertEquals(300.0, tracker.calculatePortfolioValue(), DELTA);

    // Remove 1 share
    assertTrue(tracker.removeStock(new StockPortfolioTracker.Stock("AAPL", 150.0, 1)));
    assertEquals(1, tracker.getPortfolio().get(0).getQuantity());

    // Remove remaining 1 share
    assertTrue(tracker.removeStock(new StockPortfolioTracker.Stock("AAPL", 150.0, 1)));
    assertTrue(tracker.getPortfolio().isEmpty());
}

@Test
public void testBuyStockSufficientAndInsufficientFunds() {
    StockPortfolioTracker tracker = new StockPortfolioTracker(500.0);
    StockPortfolioTracker.Stock stock = new StockPortfolioTracker.Stock("GOOG", 200.0, 2); // cost 400

    assertTrue(tracker.buyStock(stock));
    assertEquals(100.0, tracker.getCashBalance(), DELTA);
    assertEquals(400.0, tracker.calculatePortfolioValue(), DELTA);

    // Cannot afford another 400
    assertFalse(tracker.buyStock(stock));
}

@Test
public void testSellStock() {
    StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);
    StockPortfolioTracker.Stock stock = new StockPortfolioTracker.Stock("MSFT", 100.0, 5);
    tracker.buyStock(stock);
    assertEquals(500.0, tracker.getCashBalance(), DELTA);

    assertTrue(tracker.sellStock(new StockPortfolioTracker.Stock("MSFT", 100.0, 2))); // revenue 200
    assertEquals(700.0, tracker.getCashBalance(), DELTA);
    assertEquals(300.0, tracker.calculatePortfolioValue(), DELTA);

    // Sell non-owned stock
    assertFalse(tracker.sellStock(new StockPortfolioTracker.Stock("TSLA", 50.0, 1)));
}

@Test
public void testPortfolioSummaryAndStockEquals() {
    StockPortfolioTracker tracker = new StockPortfolioTracker(500.0);
    StockPortfolioTracker.Stock stock = new StockPortfolioTracker.Stock("AMZN", 50.0, 2);
    tracker.buyStock(stock);

    String summary = tracker.getPortfolioSummary();
    assertTrue(summary.contains("AMZN"));
    assertTrue(summary.contains("Total Value: $100.0"));

    StockPortfolioTracker.Stock duplicate = new StockPortfolioTracker.Stock("AMZN", 50.0, 2);
    assertEquals(stock, duplicate);
    assertEquals(stock.hashCode(), duplicate.hashCode());
}

}
