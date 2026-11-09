// BEGIN GeneratedTest_CE077
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE077 {

    private StockPortfolioTracker.Stock s(String n, double p, int q) {
        return new StockPortfolioTracker.Stock(n, p, q);
    }
    @Test public void startsEmptyWithInitialCash() {
        StockPortfolioTracker t = new StockPortfolioTracker(1000);
        assertEquals(1000.0, t.getCashBalance(), 0.0);
        assertTrue(t.getPortfolio().isEmpty());
        assertEquals(0.0, t.calculatePortfolioValue(), 0.0);
        assertEquals("Total Value: $0.0\n", t.getPortfolioSummary());
    }
    @Test public void addMergesOnlySameNameAndPrice() {
        StockPortfolioTracker t = new StockPortfolioTracker(1000);
        t.addStock(s("A", 10, 2));
        t.addStock(s("A", 10, 3));
        t.addStock(s("A", 20, 1));
        t.addStock(s("B", 10, 1));
        assertEquals(3, t.getPortfolio().size());
        assertEquals(5, t.getPortfolio().get(0).getQuantity());
        assertEquals(80.0, t.calculatePortfolioValue(), 0.0);
        assertEquals(1000.0, t.getCashBalance(), 0.0);
    }
    @Test public void partialAndCompleteRemoval() {
        StockPortfolioTracker t = new StockPortfolioTracker(1000);
        t.addStock(s("A", 10, 5));
        assertFalse(t.removeStock(s("A", 10, 6)));
        assertFalse(t.removeStock(s("A", 11, 1)));
        assertTrue(t.removeStock(s("A", 10, 2)));
        assertEquals(3, t.getPortfolio().get(0).getQuantity());
        assertTrue(t.removeStock(s("A", 10, 3)));
        assertTrue(t.getPortfolio().isEmpty());
    }
    @Test public void buySellUpdateCashAndInventory() {
        StockPortfolioTracker t = new StockPortfolioTracker(500);
        assertTrue(t.buyStock(s("A", 100, 5)));
        assertEquals(0.0, t.getCashBalance(), 0.0);
        assertFalse(t.buyStock(s("B", 1, 1)));
        assertTrue(t.sellStock(s("A", 100, 2)));
        assertEquals(200.0, t.getCashBalance(), 0.0);
        assertEquals(300.0, t.calculatePortfolioValue(), 0.0);
        assertFalse(t.sellStock(s("A", 100, 4)));
    }
    @Test public void stockValueEqualityAndDescription() {
        StockPortfolioTracker.Stock a = s("ABC", 12.5, 3);
        StockPortfolioTracker.Stock b = s("ABC", 12.5, 3);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals("ABC: 3 shares at $12.5 each", a.toString());
        b.setQuantity(4);
        assertNotEquals(a, b);
        assertEquals("ABC", a.getName());
        assertEquals(12.5, a.getPrice(), 0.0);
    }
    @Test public void portfolioCopySharesStockObjectsButNotList() {
        StockPortfolioTracker t = new StockPortfolioTracker(100);
        t.addStock(s("A", 10, 2));
        java.util.List<StockPortfolioTracker.Stock> copy = t.getPortfolio();
        copy.get(0).setQuantity(5);
        copy.clear();
        assertEquals(1, t.getPortfolio().size());
        assertEquals(50.0, t.calculatePortfolioValue(), 0.0);
    }

    @Test public void summaryUsesInsertionOrder() { StockPortfolioTracker t=new StockPortfolioTracker(0); t.addStock(s("A",10,2)); t.addStock(s("B",5,1)); assertEquals("A: 2 shares at $10.0 each\nB: 1 shares at $5.0 each\nTotal Value: $25.0\n",t.getPortfolioSummary()); }
    @Test public void portfolioValueExcludesCash() {
        StockPortfolioTracker t = new StockPortfolioTracker(5000.0);
        assertEquals(0.0, t.calculatePortfolioValue(), 0.0);
        assertTrue(t.buyStock(new StockPortfolioTracker.Stock("ACME", 25.0, 4)));
        assertEquals(4900.0, t.getCashBalance(), 0.0);
        assertEquals(100.0, t.calculatePortfolioValue(), 0.0);
        assertEquals("ACME: 4 shares at $25.0 each\nTotal Value: $100.0\n", t.getPortfolioSummary());
    }
}
// END GeneratedTest_CE077
