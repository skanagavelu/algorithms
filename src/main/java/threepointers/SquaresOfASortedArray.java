package threepointers;


/**
 * 977. Squares of a Sorted Array
 */
public class SquaresOfASortedArray {


  public int[] sortedSquares(int[] A) {
    int n = A.length;

    // It is not possible to merge without additional array
    int[] result = new int[n];
    // One from start, Two from end
    int i = 0, j = n - 1;

    // Third from end
    for (int p = n - 1; p >= 0; p--) {
      if (Math.abs(A[i]) > Math.abs(A[j])) {
        result[p] = A[i] * A[i];
        i++;
      } else {
        result[p] = A[j] * A[j];
        j--;
      }
    }
    return result;
  }

//  public int[] sortedSquares(int[] A) {
//
//    int mid = findMid(A);
//    int i = 0, j = mid;
//
//    // Third from end
//    for (int p = 0; i < j; p++) {
//      if (Math.abs(A[i]) < Math.abs(A[j])) {
//
//        A[p] = A[i] * A[i];
//        i++;
//      } else {
//        swap(A, i, j);
//        A[p] = A[i] * A[i];
//      }
//    }
//    return result;
//  }
//
//  int findMid(int[] nums) {
//    for (int i = 0; i < nums.length - 1; i++) {
//      if (Math.sqrt(nums[i]) > Math.sqrt(nums[i + 1])) {
//        return i + 1;
//      }
//    }
//  }
//
//  void swap(int[] nums, int i, int j) {
//    int temp = nums[i];
//    nums[i] = nums[j];
//    nums[j] = temp;
//  }


}
