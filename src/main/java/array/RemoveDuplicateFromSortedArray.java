package array;

import java.util.Arrays;

/**
 * 26. Remove Duplicates from Sorted Array
 *
 * @author ksugumar
 */
public class RemoveDuplicateFromSortedArray {
  static int[] arr = {1, 1, 2, 3, 3, 4, 5, 5, 6, 6, 6, 6, 8, 9, 9};

  public static void main(String[] args) {
    print();
    int writer = 0;
    for (int i = 1; i < arr.length; i++) {
      if (arr[writer] != arr[i]) {
        arr[++writer] = arr[i];
      }
    }

    System.out.println(writer);
    Arrays.fill(arr, writer + 1, arr.length, 0);
    print();
  }

  static void print() {
    StringBuilder sb = new StringBuilder();
    for (int val : arr) {
      sb.append(val).append(" | ");
    }
    System.out.println(sb);
  }

  public int removeDuplicates(int[] nums) {
	  if (nums == null || nums.length == 0) {
		  return 0;
	  } else if (nums.length == 1) {
		  return 1;
	  }

	  int i = 0; //uniqueCount
	  for (int j = 1; j < nums.length; j++) {
		  if (nums[i] != nums[j]) {
			  nums[++i] = nums[j];
		  }
	  }
	  return i + 1;
  }

  public int countUniqueNumbers(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    } else if (nums.length == 1) {
      return 1;
    }

    int uniqueCount = 1;
    for (int i = 0, j = 1; j < nums.length; j++) {
      if (nums[i] != nums[j]) {
        uniqueCount++;
        i = j;
      }
    }
    return uniqueCount;
  }
}
