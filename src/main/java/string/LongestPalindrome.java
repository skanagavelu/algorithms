package string;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LongestPalindrome {

    public static void main(String[] args) {

        //        longestPalindrome("babad"); //bab
        //        longestPalindrome("dababad"); //dababad
        //        longestPalindrome("edababad"); //dababad
        //        longestPalindrome("ccc"); //ccc
        //        longestPalindrome("cccc"); //cccc
        longestPalindrome("acccca"); //ccc
        //        longestPalindrome("caba"); //aba
        //        longestPalindrome("aba"); //aba

    }
    public static String longestPalindrome(String s) {

        int palindromeLength = 0;
        int[] match = new int[2];
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {

            if(!charSet.add(s.charAt(i))) {

                int[] matched = getPalindromeLength(s, i);
                if (palindromeLength < (matched[1] - matched[0])) {

                    palindromeLength = (matched[1] - matched[0]);
                    match = matched;
                }
            }
        }

//        System.out.println(s.subSequence(match[0], match[1] + 1).toString());
        return s.subSequence(match[0], match[1] + 1).toString(); // + 1 : exclusive
    }

    private static int[] getPalindromeLength(String s, int start) {

        int[] match = new int[2];
        int forward = start;
        int backward = forward - 1;

        // Handle non matching middle char e.g. "aba", b is the middle
        if (s.charAt(forward) != s.charAt(backward) ) {

            backward--;
        } else {
            // Handle repeated chars e.g. "aaaaa"
            while (backward > 0 && s.charAt(forward) == s.charAt(backward - 1)) {

                backward--;
            }
        }

        while (forward < s.length() && backward >= 0) {

            if (s.charAt(forward) == s.charAt(backward)) {

                backward--;
                forward++;
            } else {

                break;
            }
        }

        //matched till
        match[0] = (backward + 1);
        match[1] = (forward - 1);
        return match;
    }


//    public static String longestPalindrome1(String s) {
//
//        int palindromeLength = 0;
//        int[] match = new int[2];
//        Stack<Character> stack = new Stack<>();
//        for (int i = 0; i < s.length(); i++) {
//
//            while(!stack.isEmpty()) {
//                Stack<Character> matchStack = new Stack<>();
//                if (stack.peek() != s.charAt(i) ) {
//
//                    matchStack.push(stack.pop());
//                }
//
//                if ()
//
//                int[] matched = getPalindromeLength(s, i);
//                if (palindromeLength < (matched[1] - matched[0])) {
//
//                    palindromeLength = (matched[1] - matched[0]);
//                    match = matched;
//                }
//            }
//        }
//
//        //        System.out.println(s.subSequence(match[0], match[1] + 1).toString());
//        return s.subSequence(match[0], match[1] + 1).toString(); // + 1 : exclusive
//    }

}
