package array;

import java.util.Arrays;

/** 88. Merge Sorted Array */
public class MergeSortedArray {

  public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
//    int[] nums1 = {2, 0};
//    int[] nums2 = {1};
    merge(nums1, nums1.length, nums2, nums2.length);
    System.out.println(Arrays.toString(nums1));
  }

  // Travel in reverse direction
  public static void merge(int[] nums1, int k, int[] nums2, int n) {
    k--;
    n--;
    int m = n;
    while (m >= 0 && n >= 0) {
      if (nums1[m] < nums2[n]) {
        nums1[k--] = nums2[n--];
      } else {
        nums1[k--] = nums1[m--];
      }
    }
    while (n >= 0) {
      nums1[k--] = nums2[n--];
    }
  }
}
