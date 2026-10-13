package HE001;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void addPositiveNumbers() {
        assertEquals(5, Solution.add(2, 3));
    }

    @Test
    void addNegativeNumbers() {
        assertEquals(-5, Solution.add(-2, -3));
    }
}