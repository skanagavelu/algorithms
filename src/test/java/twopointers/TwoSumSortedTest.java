package twopointers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumSortedTest {

  TwoSumSorted twoSumSorted = new TwoSumSorted();

  @Test
  void twoSum() {
    int[] input = {2,7,11,15};
    assertEquals(Arrays.toString(new int[]{1, 2}), Arrays.toString(twoSumSorted.twoSum(new int[]{2, 7, 11, 15}, 9)));
  }
}
