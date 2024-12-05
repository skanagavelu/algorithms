package twopointers.fastslowpointers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RemoveDuplicateFromSortedArrayTest {

  RemoveDuplicateFromSortedArray removeDuplicateFromSortedArray =
      new RemoveDuplicateFromSortedArray();

  @Test
  void removeDuplicates() {
    /*
        Input: nums = [0,0,1,1,1,1,2,3,3]
    Output: 7, nums = [0,0,1,1,2,3,3,_,_]
    Explanation: Your function should return k = 7,
    with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
    It does not matter what you leave beyond the returned k (hence they are underscores).
         */
    int result =
        removeDuplicateFromSortedArray.removeDuplicatesBeyondSize2(new int[] {1, 1, 1, 2, 2, 3});
    assertEquals(5, result);
  }
}
