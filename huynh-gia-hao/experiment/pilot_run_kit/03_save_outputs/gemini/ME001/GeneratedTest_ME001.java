package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;

public class GeneratedTest_ME001 {
    @Test
    public void testSumProduct() {
        assertArrayEquals(new Integer[]{0, 1},
                humaneval.correct.SUM_PRODUCT.sum_product(Collections.emptyList()));
        assertArrayEquals(new Integer[]{10, 24},
                humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1, 2, 3, 4)));
        assertArrayEquals(new Integer[]{0, 0},
                humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(0, 1, 2)));
    }
}
