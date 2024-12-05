package array;

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * <p>Input: prices = [7,1,5,3,6,4] Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day
 * 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6),
 * profit = 6-3 = 3. Total profit is 4 + 3 = 7.
 */
public class BuyAndSellStock2 {
  public static void main(String[] args) {}

  public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) {
      return 0;
    }
    int low = prices[0];
    int profit = 0;
    int sumProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (profit < prices[i] - low) {
        profit = prices[i] - low;
      } else if (prices[i] < prices[i - 1]) {
        low = prices[i];
        sumProfit += profit;
        profit = 0;
      }
    }
    return sumProfit += profit;
  }
}
