package backtracking;

import java.util.ArrayList;
import java.util.List;

//@NotThreadSafe
//@Mutable

/**
 *
 */
public class Combinations {

    public static void main(String[] args) {
        List<Character> input = new ArrayList<>(List.of('1', '2', '3', '4'));
        combinations(0,3, input, new ArrayList<>());
    }

    private static void combinations(int start, int size, List<Character> input, List<Character> combinations) {

        if (combinations.size() == size) {
            System.out.println(combinations);
            return;
        }

        for (int i = start; i < input.size(); i++) {
            combinations.add(input.get(i));
            combinations(i+1, size, input, combinations);
            combinations.remove(combinations.size() - 1);
        }
    }
}
