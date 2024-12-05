package array;

/** 169. Majority Element
 *
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than
 * ⌊n / 2⌋ times. You may assume that the majority element
 * always exists in the array.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * */
public class MajorityElement {

  private static class Counter {
    int key;
    int count;

    public Counter(int key, int count) {
      this.key = key;
      this.count = count;
    }
  }

  public int majorityElement(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];

    var counter = new Counter(nums[0], 1);

    for (int i = 1; i < nums.length; i++) {
      if (counter.key == nums[i]) {
        counter.count++;
      } else {
        int countVal = counter.count--;
        if (countVal == 0) {
          counter.key = nums[i];
          counter.count = 1;
        }
      }
    }
    return counter.key;
  }
}
