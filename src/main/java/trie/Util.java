package trie;

import java.util.Arrays;
import java.util.Random;

public class Util {

    public static String generateRandomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        int targetStringLength = random.nextInt((10 - 1) + 1) + 1;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public static class Sequence implements CharSequence {

        char[] arr;

        public Sequence(String seq) {
            arr = seq.toCharArray();
        }

        @Override
        public int length() {

            return arr.length;
        }

        @Override
        public char charAt(int index) {

            return arr[index];
        }

        @Override
        public CharSequence subSequence(int start, int end) {

            return null;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sequence sequence = (Sequence) o;
            return Arrays.equals(arr, sequence.arr);
        }

        @Override
        public int hashCode() {

            return Arrays.hashCode(arr);
        }

        @Override
        public String toString() {

            return String.valueOf(arr) ;
        }
    }
}
