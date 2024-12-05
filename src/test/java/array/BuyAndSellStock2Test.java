package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyAndSellStock2Test {

  BuyAndSellStock2 stock = new BuyAndSellStock2();

  @Test
  void maxProfit() {
    int[] input = {7,1,5,3,6,4};
    assertEquals(7, stock.maxProfit(input));
  }

  @Test
  void maxProfitAscending() {
    int[] input = {1,2,3,4,5};
    assertEquals(4, stock.maxProfit(input));
  }
}
