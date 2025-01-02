package array;

/*
125. Valid Palindrome
Two pointer towards each other

Think of edge cases:
1. It can be case sensitive
2. It can contain any non letter character
 */
public class ValidPalindrome {
    public static void main(String[] args) {
    System.out.println(
    isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        int l = 0;              // left
        int r = s.length() - 1; // right

        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;

            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;
            r--;
        }
        return true;
    }
}
