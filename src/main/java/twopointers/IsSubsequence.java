package twopointers;

/**
 * 392. Is Subsequence
 */
public class IsSubsequence {
  public static void main(String[] args) {
    System.out.println(
    isSubsequence("abc", "ahbgdc")); // true
    System.out.println(
    isSubsequence("axc", "ahbgdc")); // false
  }

  /*
   No need to look for recursively
   */
  public static boolean isSubsequence(String s, String t) {
    int i = 0, j = 0;
    // while loop is awesome
    while (j < t.length() && i < s.length()) {
      if (s.charAt(i) == t.charAt(j)) {
        i++;
      }
      j++;
    }
    return i == s.length(); // Ternary operator for true false can be avoided
  }
}
