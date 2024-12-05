package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Converts int[] to modifiableList<Integer>
 * int[] nums = {1, 2, 3, 4, 5};
 *
 * .boxed().toList() is unModifiableList
 *
 * List<Integer> list = Arrays.stream(nums)
 *                            .boxed() // Converts each int to Integer
 *                            .collect(Collectors.toList());
 * System.out.println(list); // Output: [1, 2, 3, 4, 5]
 */
public class Permutation46 {

//    public static void main(String[] args) {
//        List<Character> input = new ArrayList<>(List.of('1', '2', '3', '4'));
////        List<Character> input = new ArrayList<>(new char[]{'1', '2', '3', '4'});
////        List<Character> input = new ArrayList<Character>(new Character[]{'1', '2', '3', '4'});
////        List<Character> input1 = new ArrayList<>(Arrays.<Character>asList(new char[]{ '1', '2', '3', '4'}));
//        List<Character> input2 =
//                new ArrayList<Character>(Arrays.asList('1', '2', '3', '4'));
//        permutations(3, input, new ArrayList<>());
//    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        permutations(nums.length, Arrays.stream(nums).boxed().collect(Collectors.toList()), new ArrayList<>(), result);
        return result;
    }

    private static void permutations(int size, List<Integer> input, List<Integer> permutations, List<List<Integer>> result) {

        if (size == 0) {
            System.out.println(permutations);
            result.add(new ArrayList<>(permutations));
            return;
        }

        for (int i = 0; i < input.size(); i++) {

            permutations.add(input.remove(i));
            permutations(size-1, input, permutations, result);
            input.add(i, permutations.remove(permutations.size() - 1));
        }
    }
}
