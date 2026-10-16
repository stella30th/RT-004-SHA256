package humaneval.reference;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTest_ME001 {

    @Test
    public void benchmarkCases() {
        assertArrayEquals(new Integer[]{0, 1},
                humaneval.correct.SUM_PRODUCT.sum_product(Collections.<Integer>emptyList()));

        assertArrayEquals(new Integer[]{3, 1},
                humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1, 1, 1)));

        assertArrayEquals(new Integer[]{100, 0},
                humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(100, 0)));

        assertArrayEquals(new Integer[]{15, 105},
                humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(3, 5, 7)));

        assertArrayEquals(new Integer[]{10, 10},
                humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(10)));
    }
}
