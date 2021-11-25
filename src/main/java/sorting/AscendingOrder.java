package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class AscendingOrder {

    public static void main(String[] args) {

        // Code for ascending order
        char[] input = "ZOhO".toCharArray();
        Arrays.sort(input);
        System.out.println(Arrays.toString(input));

        Character[] charObjectArray =
                "ZOhO".chars().mapToObj(c -> (char)c).toArray(Character[]::new);

        IntStream.range(0, "ZOhO".length()).map(i -> (Character)"ZOhO".charAt(i)).toArray();

        List<Entry<String, Integer>> list = new ArrayList<>(new HashMap<String, Integer>().entrySet());

        List<String>  OPEN_PARENTHESES = Arrays.asList("{", "(", "[");

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());


        // Code to see ASCII values
        for (int i = 0; i < 255; i++) {
            System.out.println(i + ":" +(char) i);
        }

        int array[] = { 1, 2, 3, 4, 5 };
        int[] copy = Arrays.copyOf(array, 5);
    }
}
