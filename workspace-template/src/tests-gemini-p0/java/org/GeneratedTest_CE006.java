package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE006 {

@Test
public void testInterpretStandardTune() {
    AutomaticGuitarSimulator simulator = new AutomaticGuitarSimulator("C4 Dm5 G7");
    List<AutomaticGuitarSimulator.ChordTune> tunes = simulator.interpret(false);

    assertNotNull(tunes);
    assertEquals(3, tunes.size());

    assertEquals("C", tunes.get(0).getChord());
    assertEquals("4", tunes.get(0).getTune());

    assertEquals("Dm", tunes.get(1).getChord());
    assertEquals("5", tunes.get(1).getTune());

    assertEquals("G", tunes.get(2).getChord());
    assertEquals("7", tunes.get(2).getTune());
}

@Test
public void testInterpretNullOrEmpty() {
    AutomaticGuitarSimulator simulatorNull = new AutomaticGuitarSimulator(null);
    assertNull(simulatorNull.interpret(false));

    AutomaticGuitarSimulator simulatorEmpty = new AutomaticGuitarSimulator("   ");
    assertNull(simulatorEmpty.interpret(false));
}

@Test
public void testDisplayMethod() {
    AutomaticGuitarSimulator simulator = new AutomaticGuitarSimulator("Am1");
    String formatted = simulator.display("Am", "123");
    assertEquals("Normal Guitar Playing -- Chord: Am, Play Tune: 123", formatted);
}

@Test
public void testChordTuneEqualsAndHashCode() {
    AutomaticGuitarSimulator.ChordTune ct1 = new AutomaticGuitarSimulator.ChordTune("Em", "6");
    AutomaticGuitarSimulator.ChordTune ct2 = new AutomaticGuitarSimulator.ChordTune("Em", "6");
    AutomaticGuitarSimulator.ChordTune ct3 = new AutomaticGuitarSimulator.ChordTune("E", "6");

    assertEquals(ct1, ct2);
    assertNotEquals(ct1, ct3);
    assertEquals(ct1.hashCode(), ct2.hashCode());
}

}
