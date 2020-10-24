package mathematics;

import java.util.BitSet;

import mathematics.ConvertBase10ToBaseX.Base;

/**
 * 
 * This class has two implementation
 * 
 * 1) The effective way of finding the number is prime 
 * 2) The effective way of printing all the primes till number N.
 *
 */
public class PrimeNumbers {

	private static boolean isPrime(int n) {

	    if ((n > 2 && (n & 1) == 0) // check is it even
	       || n <= 1  //check for -ve
	       || (n > 3 && (n % 3 ==  0))) {  //check for 3 divisiable
	            return false;
	    }

	    int maxLookup = (int) Math.sqrt(n);
	    for (int i = 3; (i+2) <= maxLookup; i = i + 6) {
	        if (n % (i+2) == 0 || n % (i+4) == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static BitSet computePrimes(int limit)
	{
	    final BitSet primes = new BitSet();
	    primes.set(0, false);
	    primes.set(1, false);
	    primes.set(2, limit, true);
	    for (int i = 0; i * i < limit; i++) //finding till sqrt(limit)
	    {
	        if (primes.get(i))
	        {
	            for (int j = i * i; j < limit; j += i) //initial skip i * i
	            {
	                primes.clear(j);
	            }
	        }
	    }
	    return primes;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			if (isPrime(i)) {
				System.out.print(i + "  ");
			}
		}
		
		System.out.println();
		
		
		BitSet primes = computePrimes(1000);
		for (int i = 0; i < 1000; i++) {
			if (primes.get(i)) {
				System.out.print(i + "  ");
			}
		}
		
	}

}
