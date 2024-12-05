package twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerWithMostWaterTest {
ContainerWithMostWater container = new ContainerWithMostWater();
  @Test
  void maxArea() {

    int[] input = {1,8,6,2,5,4,8,3,7};
    assertEquals(49, container.maxArea(input));
  }

  @Test
  void maxAreaEdge() {
    int[] input = {1,2,1};
    assertEquals(2, container.maxArea(input));
  }
}
