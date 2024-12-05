package array;

import java.util.Objects;

public class RotateArray {
    public int[] rotate(int[] nums, int k) {
        Objects.requireNonNull(nums);
        if (nums.length == 0 || nums.length == 1 || k < 1) {
            return nums;
        }

        if (nums.length < k) {
            k = k % nums.length;
        }

//        for(int i = 0; i < k; i++) {
//            for(int j = nums.length - 1; j > 0; j--) {
//                swap(nums, j, j - 1);
//            }
//        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return nums;
    }

    private void reverse(int[] nums, int start, int end){
        while (start < end) {
            swap(nums, start++, end--);
        }
//        for(int i = start, j = end; i < j ; i++, j--) {
//            swap(nums, i, j);
//        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
