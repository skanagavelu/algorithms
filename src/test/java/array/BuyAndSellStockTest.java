package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyAndSellStockTest {

  BuyAndSellStock stock = new BuyAndSellStock();

  @Test
  void maxProfitWhenInputIsEmpty() {
    assertEquals(0, stock.maxProfit(new int[0]));
  }

  @Test
  void maxProfitSingleElement() {
    int[] input = {7};
    assertEquals(0, stock.maxProfit(input));
  }

  @Test
  void maxProfitWhenAscendingOrder() {
    int[] input = {7,6,5,4,3};
    assertEquals(0, stock.maxProfit(input));
  }


  @Test
  void maxProfit() {
    int[] input = {7,1,5,3,6,4};
    assertEquals(5, stock.maxProfit(input));
  }
}
