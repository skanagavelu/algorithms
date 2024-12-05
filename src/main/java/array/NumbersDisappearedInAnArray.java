package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 * Example 1:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 *
 * Example 2:
 * Input: nums = [1,1]
 * Output: [2]
 */
public class NumbersDisappearedInAnArray {

  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i]) - 1;
      nums[index] = -(Math.abs(nums[index]));
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        list.add(i + 1);
      }
    }

    return list;
  }
}
