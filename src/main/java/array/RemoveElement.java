package array;

/**
 * 27. Remove Element
 *
 * Example 1:
 *
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * Explanation: Your function should return k = 2,
 * with the first two elements of nums being 2.
 * It does not matter what you leave beyond
 * the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 * Explanation: Your function should return k = 5,
 * with the first five elements of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order.
 * It does not matter what you leave beyond
 * the returned k (hence they are underscores).
 *
 * Writing all different test cases (repeated/differentPlaces/invalidSize) are important
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;

        if (j < 0) {
            return 0;
        }
        while (i < j) {
            if (nums[i] == val) {
                int temp = nums[j]; // Assign to temp before changing
                nums[j--] = nums[i]; // Change
                nums[i] = temp; // Reassign temp
            } else {
                i++;
            }
        }
        if (i == 0) {
            if (nums[0] == val) {
                return 0;
            }
            return 1;
        } else if (i == j && nums[i] == val) {
            return i;
        }
        return i + 1; // return number of items not index
    }
}
