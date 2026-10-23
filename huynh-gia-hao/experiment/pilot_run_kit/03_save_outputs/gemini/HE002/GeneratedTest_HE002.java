package humaneval.generated;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class GeneratedTest_HE002 {
private static final double DELTA = 1e-12;

@Test
public void testSimpleDecimal() {
    assertEquals(0.5,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(3.5),
            DELTA);
}

@Test
public void testNumberBetweenZeroAndOne() {
    assertEquals(0.75,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(0.75),
            DELTA);
}

@Test
public void testIntegerInput() {
    assertEquals(0.0,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(10.0),
            DELTA);
}

@Test
public void testSmallDecimalPart() {
    assertEquals(0.125,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(7.125),
            DELTA);
}

@Test
public void testLargeIntegerPart() {
    assertEquals(0.625,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(123456.625),
            DELTA);
}

@Test
public void testVerySmallPositiveNumber() {
    assertEquals(0.001,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(0.001),
            DELTA);
}

@Test
public void testExactBinaryFraction() {
    assertEquals(0.25,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(42.25),
            DELTA);
}

@Test
public void testAnotherExactBinaryFraction() {
    assertEquals(0.875,
            humaneval.correct.TRUNCATE_NUMBER.truncate_number(19.875),
            DELTA);
}

}