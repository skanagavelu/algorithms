package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@NotThreadSafe
//@Mutable
public class Permutation {

    public static void main(String[] args) {
        List<Character> input = new ArrayList<>(List.of('S', 'P', 'M', 'A', 'P'));
//        List<Character> input = new ArrayList<>(new char[]{'1', '2', '3', '4'});
//        List<Character> input = new ArrayList<Character>(new Character[]{'1', '2', '3', '4'});
//        List<Character> input1 = new ArrayList<>(Arrays.<Character>asList(new char[]{ '1', '2', '3', '4'}));
        List<Character> input2 =
                new ArrayList<Character>(Arrays.asList('1', '2', '3', '4'));
        permutations(5, input, new ArrayList<>());
    }


    private static void permutations(int size, List<Character> input, List<Character> permutations) {

        if (permutations.size() == size) {
            System.out.println(permutations.toString().replaceAll(", ", "") );
            return;
        }

        for (int i = 0; i < input.size(); i++) {

            permutations.add(input.remove(i));
            permutations(size, input, permutations);
            input.add(i, permutations.remove(permutations.size() - 1));
        }
    }
}
