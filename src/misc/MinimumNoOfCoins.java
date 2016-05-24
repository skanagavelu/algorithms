package misc;

/**
 * 
 * http://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 * 
 * Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change?

	Examples:

	Input: coins[] = {25, 10, 5}, V = 30
	Output: Minimum 2 coins required
	We can use one coin of 25 cents and one of 5 cents 

	Input: coins[] = {9, 6, 5, 1}, V = 11
	Output: Minimum 2 coins required
	We can use one coin of 6 cents and 1 coin of 5 cents
	
 * @author ksugumar
 *
 */
public class MinimumNoOfCoins {
	public static void main(String[] args) {
		int[] coins = { 25, 10, 5 };
		int value = 30;
		int noOfCoins = findNoOfCoins(coins, value);
		System.out.println(noOfCoins);
	}

	private static int findNoOfCoins(int[] coins, int value, int level) {
		if(level >= coins.length) {
			return -1;
		}
		
		int noOfCoinsSelected = 0;
		int result = 0;
		while(coins[level] <= value ) {
			noOfCoinsSelected ++;
			value = value - coins[level];
		}
		if(value != 0) {
			while( findNoOfCoins(coins, value, level+1) == -1) {
				noOfCoinsSelected --;
				value = value + coins[level];
			}

		}
		
		return result;
	}
}
