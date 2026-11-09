package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE077 {

@Test public void valueExcludesCash() {
 StockPortfolioTracker t=new StockPortfolioTracker(1000);
 assertEquals(0.0,t.calculatePortfolioValue(),0.0);
 assertEquals(1000.0,t.getCashBalance(),0.0);
 assertTrue(t.buyStock(new StockPortfolioTracker.Stock("ABC",10.0,4)));
 assertEquals(40.0,t.calculatePortfolioValue(),0.0);
 assertEquals(960.0,t.getCashBalance(),0.0);
 assertTrue(t.sellStock(new StockPortfolioTracker.Stock("ABC",10.0,2)));
 assertEquals(20.0,t.calculatePortfolioValue(),0.0);
 assertEquals(980.0,t.getCashBalance(),0.0);
}
@Test public void aggregatesMatchingNameAndPrice() {
 StockPortfolioTracker t=new StockPortfolioTracker(0);
 t.addStock(new StockPortfolioTracker.Stock("A",10,2));
 t.addStock(new StockPortfolioTracker.Stock("A",10,3));
 t.addStock(new StockPortfolioTracker.Stock("A",20,1));
 assertEquals(2,t.getPortfolio().size());
 assertEquals(70.0,t.calculatePortfolioValue(),0.0);
 assertFalse(t.removeStock(new StockPortfolioTracker.Stock("A",10,6)));
}

}
