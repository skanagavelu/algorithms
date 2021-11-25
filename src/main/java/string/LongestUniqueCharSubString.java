package string;
import java.util.LinkedList;

/**
 * https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 *
 * Given a string str, find the length of the longest substring without repeating characters.
 *
 * For “ABDEFGABEF”, the longest substring are “BDEFGA” and “DEFGAB”, with length 6.
 * For “BBBB” the longest substring is “B”, with length 1.
 */
public class LongestUniqueCharSubString {
	
	public static void main(String[] args) {
		System.out.println(longestUniqueCharSubString1("ABDEFGABEF"));
		System.out.println(longestUniqueCharSubString2("ABDEFGABEF"));

		System.out.println(longestUniqueCharSubString1("BBBB"));
		System.out.println(longestUniqueCharSubString2("BBBB"));

		System.out.println(longestUniqueCharSubString1("ABCDEFSRCQWUVZIOP"));
		System.out.println(longestUniqueCharSubString2("ABCDEFSRCQWUVZIOP"));
	}
	
	//Solution 1
	public static int longestUniqueCharSubString1(String input) {
		if (input == null) {
			return 0;
		}
		
        LinkedList<Character> list = new LinkedList<Character>();
		int result = 0;
		char[] arr = input.toCharArray();

		for (int i = 0; i < arr.length; i++) {
			char current = arr[i];
			if (list.contains(current)) {
				result = Math.max(result, list.size());
				for (;;) {
					char c = list.pollFirst();
					if (c == current) {
						break;
					}
				}
			}
			list.offerLast(current);
		}
		result = Math.max(result, list.size());
		return result;
	}
	
	//Solution 2
	public static int longestUniqueCharSubString2(String input) {
		if (input == null) {
			return 0;
		}
		int result = 0;
		int start = 0;
		char[] arr = input.toCharArray();
		boolean[] flag = new boolean[256];

		for (int i = 0; i < arr.length; i++) {
			char current = arr[i];
			if (flag[current]) {
				result = Math.max(result, i - start);
				for (int j = start; j < i; j++) {
					if (arr[j] == current) {
						start = j + 1;
						break;
					}
					flag[arr[j]] = false;
				}
			} else {
				flag[current] = true;
			}
		}
		result = Math.max(result, arr.length - start);
		return result;
	}
}
