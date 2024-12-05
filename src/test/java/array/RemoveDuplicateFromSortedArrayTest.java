package array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import twopointers.fastslowpointers.RemoveDuplicateFromSortedArray;

public class RemoveDuplicateFromSortedArrayTest  {

  RemoveDuplicateFromSortedArray instance = new RemoveDuplicateFromSortedArray();

  @Test
  public void testCountUniqueNumbersWhenEmptyArray() {

    assertEquals(0, instance.countUniqueNumbers(new int[]{}));
  }

  @Test
  public void testCountUniqueNumbersWhenOneElement() {
    assertEquals(1, instance.countUniqueNumbers(new int[]{1}));
  }

  @Test
  public void testCountUniqueNumbersWhenTwoDuplicateElements() {
    assertEquals(1, instance.countUniqueNumbers(new int[]{1, 1}));
  }

  @Test
  public void testCountUniqueNumbers() {
    assertEquals(9, instance.countUniqueNumbers(new int[]{1,1,2,3,3,4,5,5,6,6,6,6,7,8,9,9}));
  }

  @Test
  public void testRemoveDuplicates() {
    int[] input = new int[]{1,1,2,3,3,4,5,5,6,6,6,6,7,8,9,9};
    assertEquals(9, instance.removeDuplicates(input));
    assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,6,6,6,7,8,9,9}, input);
  }
}
