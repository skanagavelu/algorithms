package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * In Java, you can cast the number 1 as a character '1' by converting it to its corresponding
 * Unicode character. Here's how you can do it:
 *
 * <p>java char ch = (char) ('0' + 1); System.out.println(ch); // Outputs: 1
 *
 * <p>Explanation: '0' is the character representation of the digit 0, and its Unicode value is 48.
 * Adding 1 to '0' results in the Unicode value of the character '1' (48 + 1 = 49). Casting this
 * value to char gives the character '1'.
 */
public class Combinations77 {

  public static void main(String[] args) {
    Combinations77 c = new Combinations77();
    System.out.println(c.combine(4, 2));
  }

  public List<List<Integer>> combine(int n, int k) {

    List<Integer> input = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      input.add(i);
    }

    List<List<Integer>> result = new ArrayList<>();
    combinations(0, k, input, new ArrayList<>(), result);
    return result;
  }

  private void combinations(
      int start,
      int size,
      List<Integer> input,
      List<Integer> combinations,
      List<List<Integer>> result) {

    if (combinations.size() == size) {
      System.out.println(combinations);
      result.add(new ArrayList<>(combinations));
      return;
    }

    for (int i = start; i < input.size(); i++) {
      combinations.add(input.get(i));
      combinations(i + 1, size, input, combinations, result);
      combinations.remove(combinations.size() - 1);
    }
  }
}
