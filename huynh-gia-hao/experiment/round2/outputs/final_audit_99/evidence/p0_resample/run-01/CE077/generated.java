// BEGIN GeneratedTest_CE077
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class GeneratedTest_CE077 {

    @Test
    public void testInitialPortfolioState() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertEquals(1000.0, tracker.getCashBalance(), 0.0001);
        assertTrue(tracker.getPortfolio().isEmpty());
        assertEquals(0.0, tracker.calculatePortfolioValue(), 0.0001);
        assertEquals("Total Value: $0.0\n", tracker.getPortfolioSummary());
    }

    @Test
    public void testAddStockAndMergeSameNameAndPrice() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 100.0, 3));
        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 100.0, 2));

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(500.0, tracker.calculatePortfolioValue(), 0.0001);
        assertEquals(1000.0, tracker.getCashBalance(), 0.0001);
    }

    @Test
    public void testDistinctStocksAndPortfolioValue() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(500.0);

        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 100.0, 2));
        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 120.0, 1));
        tracker.addStock(new StockPortfolioTracker.Stock("MSFT", 50.0, 3));

        assertEquals(3, tracker.getPortfolio().size());
        assertEquals(470.0, tracker.calculatePortfolioValue(), 0.0001);
    }

    @Test
    public void testRemoveStockPartiallyAndCompletely() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 50.0, 5));

        assertTrue(tracker.removeStock(
                new StockPortfolioTracker.Stock("AAPL", 50.0, 2)));
        assertEquals(3, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(150.0, tracker.calculatePortfolioValue(), 0.0001);

        assertTrue(tracker.removeStock(
                new StockPortfolioTracker.Stock("AAPL", 50.0, 3)));
        assertTrue(tracker.getPortfolio().isEmpty());
        assertEquals(0.0, tracker.calculatePortfolioValue(), 0.0001);
        assertEquals(1000.0, tracker.getCashBalance(), 0.0001);
    }

    @Test
    public void testRemoveStockFailure() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 50.0, 3));

        assertFalse(tracker.removeStock(
                new StockPortfolioTracker.Stock("AAPL", 50.0, 4)));
        assertFalse(tracker.removeStock(
                new StockPortfolioTracker.Stock("MSFT", 50.0, 1)));
        assertFalse(tracker.removeStock(
                new StockPortfolioTracker.Stock("AAPL", 60.0, 1)));

        assertEquals(3, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(150.0, tracker.calculatePortfolioValue(), 0.0001);
    }

    @Test
    public void testBuyStockSuccessAndInsufficientCash() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(500.0);

        assertTrue(tracker.buyStock(
                new StockPortfolioTracker.Stock("AAPL", 100.0, 3)));
        assertEquals(200.0, tracker.getCashBalance(), 0.0001);
        assertEquals(300.0, tracker.calculatePortfolioValue(), 0.0001);

        assertFalse(tracker.buyStock(
                new StockPortfolioTracker.Stock("MSFT", 150.0, 2)));
        assertEquals(200.0, tracker.getCashBalance(), 0.0001);
        assertEquals(1, tracker.getPortfolio().size());

        assertTrue(tracker.buyStock(
                new StockPortfolioTracker.Stock("AAPL", 100.0, 2)));
        assertEquals(0.0, tracker.getCashBalance(), 0.0001);
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testSellStockSuccessAndFailure() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(
                new StockPortfolioTracker.Stock("AAPL", 100.0, 5)));
        assertEquals(500.0, tracker.getCashBalance(), 0.0001);

        assertTrue(tracker.sellStock(
                new StockPortfolioTracker.Stock("AAPL", 100.0, 2)));
        assertEquals(700.0, tracker.getCashBalance(), 0.0001);
        assertEquals(3, tracker.getPortfolio().get(0).getQuantity());

        assertFalse(tracker.sellStock(
                new StockPortfolioTracker.Stock("AAPL", 100.0, 4)));
        assertFalse(tracker.sellStock(
                new StockPortfolioTracker.Stock("MSFT", 100.0, 1)));
        assertEquals(700.0, tracker.getCashBalance(), 0.0001);

        assertTrue(tracker.sellStock(
                new StockPortfolioTracker.Stock("AAPL", 100.0, 3)));
        assertEquals(1000.0, tracker.getCashBalance(), 0.0001);
        assertTrue(tracker.getPortfolio().isEmpty());
    }

    @Test
    public void testPortfolioListIsDefensiveCopy() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);
        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 25.0, 4));

        List<StockPortfolioTracker.Stock> snapshot = tracker.getPortfolio();
        snapshot.clear();

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(100.0, tracker.calculatePortfolioValue(), 0.0001);
    }

    @Test
    public void testPortfolioSummary() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(new StockPortfolioTracker.Stock("AAPL", 50.0, 2));
        tracker.addStock(new StockPortfolioTracker.Stock("MSFT", 75.0, 3));

        String expected =
                "AAPL: 2 shares at $50.0 each\n" +
                "MSFT: 3 shares at $75.0 each\n" +
                "Total Value: $325.0\n";

        assertEquals(expected, tracker.getPortfolioSummary());
    }

    @Test
    public void testStockGettersSettersAndToString() {
        StockPortfolioTracker.Stock stock =
                new StockPortfolioTracker.Stock("GOOG", 125.5, 4);

        assertEquals("GOOG", stock.getName());
        assertEquals(125.5, stock.getPrice(), 0.0001);
        assertEquals(4, stock.getQuantity());
        assertEquals("GOOG: 4 shares at $125.5 each", stock.toString());

        stock.setQuantity(7);

        assertEquals(7, stock.getQuantity());
        assertEquals("GOOG: 7 shares at $125.5 each", stock.toString());
    }

    @Test
    public void testStockEqualsAndHashCode() {
        StockPortfolioTracker.Stock first =
                new StockPortfolioTracker.Stock("AAPL", 100.0, 5);
        StockPortfolioTracker.Stock same =
                new StockPortfolioTracker.Stock("AAPL", 100.0, 5);
        StockPortfolioTracker.Stock differentName =
                new StockPortfolioTracker.Stock("MSFT", 100.0, 5);
        StockPortfolioTracker.Stock differentPrice =
                new StockPortfolioTracker.Stock("AAPL", 110.0, 5);
        StockPortfolioTracker.Stock differentQuantity =
                new StockPortfolioTracker.Stock("AAPL", 100.0, 3);

        assertEquals(first, first);
        assertEquals(first, same);
        assertEquals(same, first);
        assertEquals(first.hashCode(), same.hashCode());

        assertNotEquals(first, differentName);
        assertNotEquals(first, differentPrice);
        assertNotEquals(first, differentQuantity);
        assertNotEquals(first, null);
        assertNotEquals(first, "AAPL");

        same.setQuantity(6);
        assertNotEquals(first, same);
    }
}
// END GeneratedTest_CE077