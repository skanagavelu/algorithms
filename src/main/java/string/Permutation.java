package string;

import java.util.Arrays;

/*
 * Courtesy: https://www.youtube.com/watch?v=GuTPwotSdYw&t=351s
 * Anagram is "a word or phrase spelled by rearranging the letters of another word or phrase".
 * So to be an anagram the arrangement of letters must make a word - that is, an anagram of a word must have a meaning.
 *
 * On the other hand, permutation is defined as "the act of changing the arrangement of a given number of elements". So
 * a permutation of a word can be any random assortment of characters, not necessarily having a meaning in the original language.
 *
 * So every anagram is a permutation of the word, but every permutation is not an anagram.
 */
public class Permutation {

    public static void main(String[] args) {
        char [] s = {'a','b','c'};
        int level = 0;
        int height = 2;
        permutation(s, level, height);
    }

    /*
     * Don't treat this as Binary Tree, this tree will keep reducing its branches on every level
     * So its branch out 3 leafs first as that is the length of string, then keep reducing one branch each level.
     *
     * Branches will be
     * A swap with A   ==> ABC
     * A swap with B   ==> BAC
     * A swap with C   ==> CBA
     * And in the next level A is FIXED
     *
     * At first level only root node ABC
     * B1: then A will replace with itself,
     * B2: A will replace with B
     * B3: A will replace with C
     * So branches in Level 2.
     *
     *
     * It is in order traversal, DFS.
     * For ABC at L0
     * L1. First A will be swapped with A itself
     * L2. Then A fixed B will swap B itself, now C is single char all others are already fixed, so go back to L1
     * L1. Now B can swap with C and Reach L2, go back L1, nothing to further change, go back to L0
     *
     * Next go back L2 parent B, then B will swap with child C and leaf reached L3 ACB,
     * Now Parent A is reached (step 1), A completed his both children, so back again and reach
     */
    static void permutation(char[] s, int level, int height) {

        if(level == height) { //Leaf reached,

            // All leafs are unique, so print it
            System.out.println(Arrays.toString(s));
        }
        else {

            // On every level number of branches are differing, hence we need for loop
            // if it is fixed binary tree, number of branches are fixed, no for loop required.
            // {i = level} ensures before the Levelth character is untouched/fixed
            for (int i = level; i <= height; i++) {
                swap(s, level, i);
                permutation(s, level + 1, height);  // go for next level, DFS
                swap(s, level, i); //Backtracking/reverting to old state while returning
            }
        }
    }

    static void swap (char[] s, int l, int h) {
        char low = s[l];
        s[l] = s[h];
        s[h] = low;
    }
}