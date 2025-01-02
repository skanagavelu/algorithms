package intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    /**
     * you need to provide an external comparator when sorting a two-dimensional array in Java
     * because Arrays.sort does not inherently know how to compare the rows (or sub-arrays) of a 2D
     * array. Here's why this is the case:
     *
     * <p>Comparator.comparing: This method uses the generic Comparator.comparing to compare values.
     * It works with any type of data, including Integer, String, Double, or objects. However, when
     * dealing with primitives (like int), autoboxing occurs. The int values are converted to
     * Integer objects, which can add a slight performance overhead.
     *
     * <p>Comparator.comparingInt: Optimized for int. This method is specifically designed for int
     * comparisons. It avoids autoboxing by working directly with primitive int values. This makes
     * it more efficient than Comparator.comparing when dealing with int
     */
    Arrays.sort(
        intervals, Comparator.comparingInt((int[] col) -> col[0]).thenComparing(col -> col[1]));
    List<int[]> result = new LinkedList<>();
    result.add(intervals[0]);
    int start = 0;
    int end = 1;
    for (int i = 1; i < intervals.length; i++) {
      int[] prevInterval = result.getLast();
      int[] curInterval = intervals[i];
      if (curInterval[start] <= prevInterval[end]) {
        // overlaps
        prevInterval[end] = Math.max(prevInterval[end], curInterval[end]);
      } else {
        result.add(curInterval);
      }
    }
    return result.toArray(new int[result.size()][]); // NOTE: Creating two dim array
  }
}
