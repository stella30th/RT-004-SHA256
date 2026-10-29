package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE007 {

@Test
public void testSetNumEvenDivision() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    AvgPartition partition = new AvgPartition(list, 2);

    int[] result = partition.setNum();
    assertArrayEquals(new int[]{2, 0}, result);
}

@Test
public void testSetNumWithRemainder() {
    List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);
    AvgPartition partition = new AvgPartition(list, 2);

    int[] result = partition.setNum();
    assertArrayEquals(new int[]{2, 1}, result);
}

@Test
public void testGetSubListEven() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    AvgPartition partition = new AvgPartition(list, 2);

    List<Integer> part0 = partition.get(0);
    List<Integer> part1 = partition.get(1);

    assertEquals(Arrays.asList(1, 2), part0);
    assertEquals(Arrays.asList(3, 4), part1);
}

@Test
public void testGetSubListUnevenDistribution() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    AvgPartition partition = new AvgPartition(list, 2);

    List<Integer> part0 = partition.get(0);
    List<Integer> part1 = partition.get(1);

    assertEquals(Arrays.asList(1, 2, 3), part0);
    assertEquals(Arrays.asList(4, 5), part1);
}

}
