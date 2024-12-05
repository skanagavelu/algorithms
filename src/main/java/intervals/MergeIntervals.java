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
        intervals, Comparator.comparingInt((int[] row) -> row[0]).thenComparing(row -> row[1]));
    List<int[]> result = new LinkedList<>();
    result.add(intervals[0]);
    for (int i = 0; i < intervals.length - 1; i++) {
      int[] first = result.getLast();
      int[] second = intervals[i + 1];
      if (second[0] <= first[1]) {
        // overlaps
        first[1] = Math.max(first[1], second[1]);
      } else {
        result.add(second);
      }
    }
    return result.toArray(new int[result.size()][]); // NOTE: Creating two dim array
  }
}
