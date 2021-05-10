package array;


public class FindOddNumberOfRepeat {

	public static void main(String[] args) {

		int[] v = {1,1,2,2,3,3,3,4,4,5,5};
		
		int oddRepeatCount = 0;
		int numberCount = 1;
		for (int i = 1; i < v.length; i++) {
			
			if (v[i] == v[i-1]) {
				numberCount++;
			} else {
				int s = numberCount & 1;
				if (s == 1) {
					oddRepeatCount++;
				}
				numberCount = 1;
			}
		}
		
		System.out.println(oddRepeatCount);
	}

}
