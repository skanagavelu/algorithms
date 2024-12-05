package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import twopointers.ThreeSum;

class ThreeSumTest {

    ThreeSum threeSum = new ThreeSum();

  @Test
  void findThreeSum() {
      int[] inputs = {-1,0,1,2,-1,-4};
    assertThat(List.of(List.of(-1,0,1), List.of(-1,-1,2))).containsExactlyInAnyOrderElementsOf(ThreeSum.findThreeSum(inputs, 0));
  }
}
