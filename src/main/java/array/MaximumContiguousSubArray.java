package array;


public class MaximumContiguousSubArray {
	public static void main(String[] args) {
//      int[] array = {1,-2,-5,55,56,-11,58,-13};
//      int[] array = {1,-2,-5,55};
//      int[] array = {-100,200,-5,55,56,-11,55,-13};
//      int[] array = {-5,55,56,-11,58,-13,-100,200};
//      int[] array = {52,55,1,4};
//		int[] array = {52,55,1,5,0};
//      int[] array = {100,52,1,55,1,4};
//      int[] array = {100,52,40,55,1,30,52};
//		int[] array = {100,52,40,55,30,52};
		int[] array = {-2,-3,4,-1,-2,1,5,-3};

      
	  // the below is needed if we have found some larger sum already
	  // but still need to explore the larger sum in the remaining inputs	
      int start = 0;
      int end = 0;
      int sum = 0;
      
      // Below is to compare larger sum after encountering 0, and continuing finding
      int currentStart = 0;
      int sumSoFar = 0;

      
      for(int i = 0; i < array.length; i++) {
    	  int currentIndexVal = array[i];
    	  
    	  // When less than or equal to zero 
    	  // Then there is no use of holding the previous values, let freshly start
    	  if(currentIndexVal + sumSoFar <= 0  ) {
    		  currentStart = i + 1;
    		  sumSoFar = 0;
    	  }
    	  else if ( (currentIndexVal + sumSoFar ) >  (sum)) {
    		  start = currentStart;
    		  end = i;
    		  sum =  sumSoFar = currentIndexVal + sumSoFar;
    	  } else {
    		  sumSoFar += currentIndexVal;
    	  }
      }
      System.out.println("Start/End/Sum : " + start + "/" + end + "/" + sum);
	}
	
	
	
//	int maxSubArraySum(int a[], int size) 
//	{ 
//	    int max_so_far = INT_MIN, max_ending_here = 0; 
//	  
//	    for (int i = 0; i < size; i++) 
//	    { 
//	        max_ending_here = max_ending_here + a[i]; 
//	        if (max_so_far < max_ending_here) 
//	            max_so_far = max_ending_here; 
//	  
//	        if (max_ending_here < 0) 
//	            max_ending_here = 0; 
//	    } 
//	    return max_so_far; 
//	} 
}

