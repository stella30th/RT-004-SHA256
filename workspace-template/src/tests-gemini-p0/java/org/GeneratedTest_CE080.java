package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE080 {

private static final double DELTA = 1e-4;

@Test
public void testSetAndGetProperties() {
    Thermostat thermostat = new Thermostat(20.0, 25.0, "heat");
    assertEquals(25.0, thermostat.getTargetTemperature(), DELTA);
    assertEquals("heat", thermostat.getMode());

    thermostat.setTargetTemperature(22.0);
    assertEquals(22.0, thermostat.getTargetTemperature(), DELTA);

    assertTrue(thermostat.setMode("cool"));
    assertEquals("cool", thermostat.getMode());

    assertFalse(thermostat.setMode("invalid_mode"));
    assertEquals("cool", thermostat.getMode());
}

@Test
public void testAutoSetMode() {
    Thermostat thermostat = new Thermostat(18.0, 24.0, "cool");
    thermostat.autoSetMode();
    assertEquals("heat", thermostat.getMode());

    thermostat.setTargetTemperature(15.0);
    thermostat.autoSetMode();
    assertEquals("cool", thermostat.getMode());
}

@Test
public void testAutoCheckConflict() {
    // current > target and mode is cool -> no conflict (true)
    Thermostat coolValid = new Thermostat(30.0, 22.0, "cool");
    assertTrue(coolValid.autoCheckConflict());

    // current > target and mode is heat -> conflict! returns false and auto-sets to cool
    Thermostat heatConflict = new Thermostat(30.0, 22.0, "heat");
    assertFalse(heatConflict.autoCheckConflict());
    assertEquals("cool", heatConflict.getMode());
}

@Test
public void testSimulateOperation() {
    Thermostat thermostat = new Thermostat(18.0, 22.0, "heat");
    int time = thermostat.simulateOperation();

    assertEquals(4, time);
    assertEquals(22.0, thermostat.currentTemperature, DELTA);
}

}
