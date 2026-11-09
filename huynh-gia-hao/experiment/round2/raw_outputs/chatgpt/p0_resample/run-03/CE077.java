// BEGIN GeneratedTest_CE077
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class GeneratedTest_CE077 {

    private static final double DELTA = 1e-9;

    private StockPortfolioTracker.Stock stock(String name, double price, int quantity) {
        return new StockPortfolioTracker.Stock(name, price, quantity);
    }

    @Test
    public void testInitialPortfolioState() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
        assertTrue(tracker.getPortfolio().isEmpty());
        assertEquals(0.0, tracker.calculatePortfolioValue(), DELTA);
        assertEquals("Total Value: $0.0\n", tracker.getPortfolioSummary());
    }

    @Test
    public void testAddStock() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 5));

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals("AAPL", tracker.getPortfolio().get(0).getName());
        assertEquals(100.0, tracker.getPortfolio().get(0).getPrice(), DELTA);
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(500.0, tracker.calculatePortfolioValue(), DELTA);
        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
    }

    @Test
    public void testAddDuplicateStockMergesQuantity() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 3));
        tracker.addStock(stock("AAPL", 100.0, 2));

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(500.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testSameNameDifferentPriceCreatesSeparateEntries() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 2));
        tracker.addStock(stock("AAPL", 120.0, 3));

        assertEquals(2, tracker.getPortfolio().size());
        assertEquals(560.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testDifferentNamesSamePriceRemainSeparate() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 50.0, 2));
        tracker.addStock(stock("MSFT", 50.0, 3));

        assertEquals(2, tracker.getPortfolio().size());
        assertEquals(250.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testRemovePartialStock() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 10));

        assertTrue(tracker.removeStock(stock("AAPL", 100.0, 4)));

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(6, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(600.0, tracker.calculatePortfolioValue(), DELTA);
        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
    }

    @Test
    public void testRemoveEntireStock() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 5));

        assertTrue(tracker.removeStock(stock("AAPL", 100.0, 5)));

        assertTrue(tracker.getPortfolio().isEmpty());
        assertEquals(0.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testRemoveMoreThanOwnedFails() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 3));

        assertFalse(tracker.removeStock(stock("AAPL", 100.0, 4)));

        assertEquals(3, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(300.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testRemoveMissingStockFails() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertFalse(tracker.removeStock(stock("AAPL", 100.0, 1)));
        assertTrue(tracker.getPortfolio().isEmpty());
    }

    @Test
    public void testRemoveStockRequiresMatchingPrice() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 5));

        assertFalse(tracker.removeStock(stock("AAPL", 110.0, 2)));
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testBuyStockSuccessfully() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 4)));

        assertEquals(600.0, tracker.getCashBalance(), DELTA);
        assertEquals(400.0, tracker.calculatePortfolioValue(), DELTA);
        assertEquals(4, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testBuyStockWithExactCashBalance() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(500.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 5)));

        assertEquals(0.0, tracker.getCashBalance(), DELTA);
        assertEquals(500.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testBuyStockWithInsufficientFunds() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(100.0);

        assertFalse(tracker.buyStock(stock("AAPL", 100.0, 2)));

        assertEquals(100.0, tracker.getCashBalance(), DELTA);
        assertTrue(tracker.getPortfolio().isEmpty());
    }

    @Test
    public void testBuySameStockMultipleTimes() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 3)));
        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 2)));

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(500.0, tracker.getCashBalance(), DELTA);
    }

    @Test
    public void testSellStockSuccessfully() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 5)));
        assertTrue(tracker.sellStock(stock("AAPL", 100.0, 2)));

        assertEquals(700.0, tracker.getCashBalance(), DELTA);
        assertEquals(3, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(300.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testSellAllStockRestoresCash() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 5)));
        assertTrue(tracker.sellStock(stock("AAPL", 100.0, 5)));

        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
        assertTrue(tracker.getPortfolio().isEmpty());
    }

    @Test
    public void testSellMoreThanOwnedFails() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 3)));
        assertFalse(tracker.sellStock(stock("AAPL", 100.0, 4)));

        assertEquals(700.0, tracker.getCashBalance(), DELTA);
        assertEquals(3, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testSellMissingStockFails() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertFalse(tracker.sellStock(stock("AAPL", 100.0, 1)));
        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
    }

    @Test
    public void testSellStockWithDifferentPriceFails() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 5));

        assertFalse(tracker.sellStock(stock("AAPL", 120.0, 2)));

        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testPortfolioValueWithMultipleStocks() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(2000.0);

        tracker.addStock(stock("AAPL", 100.0, 3));
        tracker.addStock(stock("MSFT", 200.0, 2));
        tracker.addStock(stock("GOOG", 50.0, 4));

        assertEquals(900.0, tracker.calculatePortfolioValue(), DELTA);
        assertEquals(2000.0, tracker.getCashBalance(), DELTA);
    }

    @Test
    public void testPortfolioSummary() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 2));
        tracker.addStock(stock("MSFT", 50.0, 3));

        String expected =
                "AAPL: 2 shares at $100.0 each\n" +
                "MSFT: 3 shares at $50.0 each\n" +
                "Total Value: $350.0\n";

        assertEquals(expected, tracker.getPortfolioSummary());
    }

    @Test
    public void testGetPortfolioReturnsIndependentList() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 2));

        List<StockPortfolioTracker.Stock> copy = tracker.getPortfolio();
        copy.clear();

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(200.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testGetPortfolioSharesStockObjects() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 2));

        List<StockPortfolioTracker.Stock> copy = tracker.getPortfolio();
        copy.get(0).setQuantity(7);

        assertEquals(7, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(700.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testStockGettersAndSetter() {
        StockPortfolioTracker.Stock stock = stock("AAPL", 123.5, 4);

        assertEquals("AAPL", stock.getName());
        assertEquals(123.5, stock.getPrice(), DELTA);
        assertEquals(4, stock.getQuantity());

        stock.setQuantity(10);
        assertEquals(10, stock.getQuantity());
    }

    @Test
    public void testStockEqualsAndHashCode() {
        StockPortfolioTracker.Stock first = stock("AAPL", 100.0, 5);
        StockPortfolioTracker.Stock second = stock("AAPL", 100.0, 5);

        assertEquals(first, second);
        assertEquals(second, first);
        assertEquals(first.hashCode(), second.hashCode());
        assertEquals(first, first);
    }

    @Test
    public void testStockEqualsDifferentValues() {
        StockPortfolioTracker.Stock base = stock("AAPL", 100.0, 5);

        assertNotEquals(base, stock("MSFT", 100.0, 5));
        assertNotEquals(base, stock("AAPL", 110.0, 5));
        assertNotEquals(base, stock("AAPL", 100.0, 6));
        assertNotEquals(base, null);
        assertNotEquals(base, "AAPL");
    }

    @Test
    public void testStockEqualityChangesWithQuantity() {
        StockPortfolioTracker.Stock first = stock("AAPL", 100.0, 5);
        StockPortfolioTracker.Stock second = stock("AAPL", 100.0, 5);

        assertEquals(first, second);

        second.setQuantity(6);

        assertNotEquals(first, second);
    }

    @Test
    public void testStockToString() {
        StockPortfolioTracker.Stock stock = stock("AAPL", 125.5, 3);

        assertEquals("AAPL: 3 shares at $125.5 each", stock.toString());
    }

    @Test
    public void testZeroQuantityStock() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 0)));

        assertEquals(1000.0, tracker.getCashBalance(), DELTA);
        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(0, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(0.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testNegativeQuantityPurchaseIncreasesCash() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, -2)));

        assertEquals(1200.0, tracker.getCashBalance(), DELTA);
        assertEquals(-2, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(-200.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testNegativeQuantityRemovalIncreasesHoldings() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 5));

        assertTrue(tracker.removeStock(stock("AAPL", 100.0, -3)));

        assertEquals(8, tracker.getPortfolio().get(0).getQuantity());
        assertEquals(800.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testSellNegativeQuantityReducesCash() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(1000.0);

        tracker.addStock(stock("AAPL", 100.0, 5));

        assertTrue(tracker.sellStock(stock("AAPL", 100.0, -2)));

        assertEquals(800.0, tracker.getCashBalance(), DELTA);
        assertEquals(7, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testBuyAfterSellingUsesAvailableCash() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(500.0);

        assertTrue(tracker.buyStock(stock("AAPL", 100.0, 5)));
        assertFalse(tracker.buyStock(stock("MSFT", 200.0, 1)));
        assertTrue(tracker.sellStock(stock("AAPL", 100.0, 2)));
        assertTrue(tracker.buyStock(stock("MSFT", 200.0, 1)));

        assertEquals(0.0, tracker.getCashBalance(), DELTA);
        assertEquals(500.0, tracker.calculatePortfolioValue(), DELTA);
    }

    @Test
    public void testStockEqualityDistinguishesSignedZeroPrices() {
        StockPortfolioTracker.Stock positive = stock("AAPL", 0.0, 1);
        StockPortfolioTracker.Stock negative = stock("AAPL", -0.0, 1);

        assertNotEquals(positive, negative);
    }

    @Test
    public void testAddStockMergesSignedZeroPrices() {
        StockPortfolioTracker tracker = new StockPortfolioTracker(100.0);

        tracker.addStock(stock("AAPL", 0.0, 2));
        tracker.addStock(stock("AAPL", -0.0, 3));

        assertEquals(1, tracker.getPortfolio().size());
        assertEquals(5, tracker.getPortfolio().get(0).getQuantity());
    }

    @Test
    public void testIndependentTrackers() {
        StockPortfolioTracker first = new StockPortfolioTracker(1000.0);
        StockPortfolioTracker second = new StockPortfolioTracker(500.0);

        assertTrue(first.buyStock(stock("AAPL", 100.0, 3)));

        assertEquals(700.0, first.getCashBalance(), DELTA);
        assertEquals(300.0, first.calculatePortfolioValue(), DELTA);

        assertEquals(500.0, second.getCashBalance(), DELTA);
        assertEquals(0.0, second.calculatePortfolioValue(), DELTA);
        assertTrue(second.getPortfolio().isEmpty());
    }
}
// END GeneratedTest_CE077