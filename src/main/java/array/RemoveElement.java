package array;

/**
 * 27. Remove Element
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
