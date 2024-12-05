package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSizeSubarraySumTest {

    MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
  @Test
  void minSubArrayLen() {
      int[] input = {2,3,1,2,4,3};
      assertEquals(2, minimumSizeSubarraySum.minSubArrayLen(7, input));
  }

    @Test
    void minSubArrayLenEdge() {
        int[] input = {1,2,3,4,5};
        assertEquals(5, minimumSizeSubarraySum.minSubArrayLen(15, input));
    }
}
