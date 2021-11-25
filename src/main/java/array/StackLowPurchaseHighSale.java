package array;

/**
 *
 * Sliding window problem:
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * https://www.youtube.com/watch?v=1pkOgXD63yU
 *
 * Buy Low (over all the time) with feasible sell at High
 * Mean if you very low value but there is very high or profit, it is of no meaning.
 *
 * It works on two pointer approach,
 * one pointer represents low (left) and another pointer(right) will represent high
 * when high - low is negative, then low takes new low value.
 * when it is positive right pointer keep moving on.
 *
 * Both of these pointer moves in one direction as time moves in one direction.
 */
public class StackLowPurchaseHighSale {
	public static void main(String[] args) {
//      int[] array = {1,-2,-5,55,56,-11,58,-13};
//      int[] array = {1,-2,-5,55};
//      int[] array = {-100,200,-5,55,56,-11,58,-13};
//      int[] array = {-5,55,56,-11,58,-13,-100,200};
//      int[] array = {52,55,1,4};
//		int[] array = {52,55,1,5,0};
//      int[] array = {100,52,1,55,1,4};
//      int[] array = {100,52,40,55,1,30,52};
//		int[] array = {100,52,40,55,30,52};
		int[] array = {-2,-3,4,-1,-2,1,5,-3};

      
      int min = array[0];
      int max = 0;
      int minTmp = array[0];
      
      for(int i = 1; i < array.length; i++) {

    	  int currentIndexVal = array[i];
    	  if(currentIndexVal < min && currentIndexVal < minTmp) {
    		  minTmp = currentIndexVal;
    	  }
    	  else if ( (currentIndexVal - minTmp ) >  (max - min)) {
    		  min = minTmp;
    		  max = currentIndexVal;
    	  }
      }
      System.out.println("Min/Max : " + min + "/" + max);
	}
}

