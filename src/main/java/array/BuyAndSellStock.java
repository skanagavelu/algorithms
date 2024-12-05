package array;

/**
 * 121. Best Time to Buy and Sell Stock
 *
 * <p>Input: prices = [7,1,5,3,6,4] Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day
 * 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed
 * because you must buy before you sell.
 */
public class BuyAndSellStock {

  public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) {
      return 0;
    }
    int low = prices[0];
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (profit < prices[i] - low) {
        // Though found profit, still find the max profit
        // This will get once have maxHigh, and maxLow
        // Record the profit, sometimes in last only see maxLow,
        // no maxRight after maxLow (2, 5, 7, 1) 1 is maxLow but no maxHigh
        profit = prices[i] - low;
      } else if (prices[i] < low) {
        // Best low price (maxLow) for one time buy
        low = prices[i];
      }
    }
    return profit;
  }
}
