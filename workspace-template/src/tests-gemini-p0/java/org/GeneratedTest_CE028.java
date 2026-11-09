package org.example;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class GeneratedTest_CE028 {

private static final double DELTA = 1e-4;

@Test
public void testConvertKnownCurrencies() {
    CurrencyConverter converter = new CurrencyConverter();

    // USD to USD
    assertEquals(100.0, converter.convert(100.0, "USD", "USD"), DELTA);

    // USD (1.0) to EUR (0.85) -> (100 / 1.0) * 0.85 = 85.0
    assertEquals(85.0, converter.convert(100.0, "USD", "EUR"), DELTA);

    // EUR (0.85) to USD (1.0) -> (85 / 0.85) * 1.0 = 100.0
    assertEquals(100.0, converter.convert(85.0, "EUR", "USD"), DELTA);
}

@Test
public void testConvertUnsupportedCurrencyReturnsNegativeOne() {
    CurrencyConverter converter = new CurrencyConverter();
    assertEquals(-1.0, converter.convert(50.0, "USD", "UNKNOWN"), DELTA);
    assertEquals(-1.0, converter.convert(50.0, "UNKNOWN", "EUR"), DELTA);
}

@Test
public void testAddCurrencyRate() {
    CurrencyConverter converter = new CurrencyConverter();

    assertTrue(converter.addCurrencyRate("SGD", 1.35));
    assertFalse(converter.addCurrencyRate("SGD", 1.40)); // already exists
    assertFalse(converter.addCurrencyRate("USD", 1.10)); // default exists

    assertEquals(135.0, converter.convert(100.0, "USD", "SGD"), DELTA);
}

@Test
public void testUpdateCurrencyRate() {
    CurrencyConverter converter = new CurrencyConverter();

    assertTrue(converter.updateCurrencyRate("EUR", 0.90));
    assertEquals(90.0, converter.convert(100.0, "USD", "EUR"), DELTA);

    assertFalse(converter.updateCurrencyRate("NON_EXISTENT", 2.0));
}

@Test
public void testGetSupportedCurrenciesAndRates() {
    CurrencyConverter converter = new CurrencyConverter();
    Set<String> currencies = converter.getSupportedCurrencies();
    assertTrue(currencies.contains("USD"));
    assertTrue(currencies.contains("JPY"));

    Map<String, Double> rates = converter.getRates();
    assertEquals(Double.valueOf(1.0), rates.get("USD"));
    assertEquals(Double.valueOf(110.15), rates.get("JPY"));
}

}
