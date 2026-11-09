package org.example;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GeneratedTest_CE003 {

@Test
public void testParseArgumentsHappyPath() {
    ArgumentParser parser = new ArgumentParser();
    parser.addArgument("port", true, Integer.class);
    parser.addArgument("host", false, String.class);
    parser.addArgument("debug", false, Boolean.class);

    ArgumentParser.Tuple<Boolean, Set<String>> result =
            parser.parseArguments("app.jar --port=8080 -host localhost --debug");

    assertTrue(result.x);
    assertNull(result.y);
    assertEquals(8080, parser.getArgument("port"));
    assertEquals("localhost", parser.getArgument("host"));
    assertEquals(true, parser.getArgument("debug"));
}

@Test
public void testParseArgumentsMissingRequiredArgument() {
    ArgumentParser parser = new ArgumentParser();
    parser.addArgument("configFile", true, String.class);
    parser.addArgument("port", false, Integer.class);

    ArgumentParser.Tuple<Boolean, Set<String>> result =
            parser.parseArguments("run.sh -port 9000");

    assertFalse(result.x);
    assertNotNull(result.y);
    assertTrue(result.y.contains("configFile"));
}

@Test
public void testFlagArgumentsWithoutValue() {
    ArgumentParser parser = new ArgumentParser();
    parser.addArgument("verbose", false, Boolean.class);
    parser.addArgument("flag", false, Boolean.class);

    ArgumentParser.Tuple<Boolean, Set<String>> result =
            parser.parseArguments("main --verbose -flag");

    assertTrue(result.x);
    assertEquals(true, parser.getArgument("verbose"));
    assertEquals(true, parser.getArgument("flag"));
}

@Test
public void testConvertTypeFallbacksToRawStringOnException() {
    ArgumentParser parser = new ArgumentParser();
    parser.addArgument("count", false, Integer.class);

    ArgumentParser.Tuple<Boolean, Set<String>> result =
            parser.parseArguments("cmd -count notANumber");

    assertTrue(result.x);
    assertEquals("notANumber", parser.getArgument("count"));
}

}
