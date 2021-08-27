package string;

import java.util.HashSet;
import java.util.Set;

// https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String input) {

        Set<Character> uniqueChars = new HashSet<>(input.length() / 2);
        int maxUniqueCharsCount = 0;
        int uniqueSubStringStart = 0;

        for (int j = uniqueSubStringStart, size = input.length(); j < size; j++) {

            char currentChar = input.charAt(j);
            if (!uniqueChars.contains(currentChar)) {

                uniqueChars.add(currentChar);
                maxUniqueCharsCount = Math.max(maxUniqueCharsCount, uniqueChars.size());
            } else {

                while(input.charAt(uniqueSubStringStart) != currentChar) {

                    uniqueChars.remove(input.charAt(uniqueSubStringStart));
                    ++uniqueSubStringStart;
                }
                ++uniqueSubStringStart;
            }

        }
        return maxUniqueCharsCount;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("pwwkew")); //"wke" :: 3
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // "abc" :: 3
        System.out.println(lengthOfLongestSubstring("bbbbb")); // "b" :: 1
        System.out.println(lengthOfLongestSubstring("")); // 0
    }
}
