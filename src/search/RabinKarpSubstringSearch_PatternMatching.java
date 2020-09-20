package search;

public class RabinKarpSubstringSearch_PatternMatching {
	
	/**
	 * A hashtable works by taking the modulus of the hash over the number of buckets.

           It's important in a hashtable not to produce collisions for likely cases, 
	   since collisions reduce the efficiency of the hashtable.

     	   Now, suppose someone puts a whole bunch of values into a hashtable
	   that have some relationship between the items, like all having the same first character.
	   This is a fairly predictable usage pattern, 
           I'd say, so we don't want it to produce too many collisions.
	   <PRE>
		  
	 * Input       Modulo 8    Modulo 7
		0           0           0
		4           4           4
		8           0           1
		12          4           5
		16          0           2
		20          4           6
		24          0           3
		28          4           0
		
	* Input       Modulo 9    Modulo 7
		0           0           0
		3           3           3
		6           6           6
		9           0           2
		12          3           5
		15          6           1
		18          0           4
		21          3           0

         </PRE>
	 * @param args
	 */
        char[] mainString = "aabc".toCharArray();
        char[] subString = "abc".toCharArray();
        int subStringHash = buildHash(subString, 0, subString.length);
        //hash for first three char
        int mainStringHash = buildHash(mainString, 0, subString.length); //exclusive subString.length

        //This is needed for initial match found, useful when mainString=abc... and subString=abc
        if (mainStringHash == subStringHash &&
            Arrays.equals(Arrays.copyOfRange(mainString, 0, subString.length), subString)) {
            System.out.println(true);
            return;
        }

        //we have to remove 0th char, and add with new char in the hash, since it is not matched above
        for (int i = 0; i < mainString.length-subString.length; i++) {

            /*  building rolling hash:
             * -------------------------
             *  new_hash = old_hash - val(old_char)
             *  new_hash = new_hash / prime
             *  new_hash = new_hash + Math.pow(prime, subStringLength) + val(new_char)
             */
            mainStringHash = mainStringHash - mainString[i];
            mainStringHash = mainStringHash / hash;


            // mainString[i+subString.length] : "babc" initial char "b" is ignored, and we need to include till "c",
            // hence subString.length
            // Math.pow(hash, subString.length-1):first char pow is 0, so for three char length the pow goes to max 2
            mainStringHash = mainStringHash + (int)Math.pow(hash, subString.length-1) * mainString[i+subString.length];

            // i+1;  because it is computed for i+1th char after removing ith char, ith char is already not matched
            // before entering this loop, still we should start with 0, to remove 0th index from initial hash.
            if (mainStringHash == subStringHash &&
                Arrays.equals(Arrays.copyOfRange(mainString, i+1, i+1  + subString.length), subString)) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }

    public static int buildHash(char[] subString, int offset, int length) {

        int result = 0; // Ensure first char is simple ASCII value, so that we can remove
        int power = 0;  // Is need so that we can divide and move the char back
        for (int i = offset; i < length; i++) {
            result = result + ((int)Math.pow(hash, power++) * subString[i]); //power is 0, 1, 2  == for 3 char length.
        }
        return result;
    }
}
