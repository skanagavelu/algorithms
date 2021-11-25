package dynamicProgramming;

import java.util.Arrays;
import java.util.Set;

public class WordBreak {

    public static void main(String[] args) {
        String word = "iamace";
        Set<CharSequence> dictionary = Set.of("i", "a", "am", "ace");

        /**
         * row is the start, and column is end of the substring and value says it is part of dictionary.
         * if row == col then it represents single char, table[row][col] = true, then that char is in dictionary.
         */
        boolean[][] table = new boolean[word.length()][word.length()];
        buildWordBreakTable(word, dictionary, table);
        printTable(table);

    }

    private static void buildWordBreakTable(String word, Set<CharSequence> dictionary, boolean[][] table) {

        for (int length = 1; length <= word.length(); length++) {
            for (int start = 0; start + length <= word.length(); start++) {
                CharSequence token = word.subSequence(start, start + length);
                if (dictionary.contains(token)) {
                    int end = start + length - 1;
                    table[start][end] =  true;
                    continue;
                }
                for (int splitAt = 1; splitAt < token.length(); splitAt++) {
                    if (table[start][start + splitAt - 1] && table[start + splitAt][start + length - 1]) {
                        int end = start + length - 1;
                        table[start][end] =  true;
                    }
                }
            }
            printTable(table);
        }
    }


    static void printTable(boolean[][] table) {
        System.out.println();
        System.out.println();
        for (boolean[] row : table) {
            System.out.println(Arrays.toString(row));
        }
    }
}
