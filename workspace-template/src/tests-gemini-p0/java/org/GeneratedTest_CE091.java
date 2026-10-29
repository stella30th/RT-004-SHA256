package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE091 {

private static final double DELTA = 1e-4;

@Test
public void testTemperatureConversions() {
    WeatherSystem system = new WeatherSystem("Hanoi");
    system.setTemperature(0.0);
    assertEquals(32.0, system.celsiusToFahrenheit(), DELTA);

    system.setTemperature(100.0);
    assertEquals(212.0, system.celsiusToFahrenheit(), DELTA);

    system.setTemperature(32.0);
    assertEquals(0.0, system.fahrenheitToCelsius(), DELTA);
}

@Test
public void testQuerySameUnit() {
    WeatherSystem system = new WeatherSystem("Tokyo");
    Map<String, Map<String, Object>> weatherList = new HashMap<>();

    Map<String, Object> tokyoWeather = new HashMap<>();
    tokyoWeather.put("temperature", 25.0);
    tokyoWeather.put("weather", "Sunny");
    tokyoWeather.put("temperature units", "celsius");
    weatherList.put("Tokyo", tokyoWeather);

    Object[] result = system.query(weatherList, "celsius");
    assertEquals(25.0, (Double) result[0], DELTA);
    assertEquals("Sunny", result[1]);
}

@Test
public void testQueryUnitConversion() {
    WeatherSystem system = new WeatherSystem("London");
    Map<String, Map<String, Object>> weatherList = new HashMap<>();

    Map<String, Object> londonWeather = new HashMap<>();
    londonWeather.put("temperature", 68.0);
    londonWeather.put("weather", "Rainy");
    londonWeather.put("temperature units", "fahrenheit");
    weatherList.put("London", londonWeather);

    // Convert fahrenheit 68 to celsius: (68 - 32) * 5 / 9 = 20.0
    Object[] result = system.query(weatherList, "celsius");
    assertEquals(20.0, (Double) result[0], DELTA);
    assertEquals("Rainy", result[1]);
}

@Test
public void testQueryCityNotFound() {
    WeatherSystem system = new WeatherSystem("Atlantis");
    Map<String, Map<String, Object>> weatherList = new HashMap<>();

    Object[] result = system.query(weatherList, "celsius");
    assertEquals(1, result.length);
    assertEquals(false, result[0]);
}

}
