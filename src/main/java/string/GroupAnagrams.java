package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<Anagram, List<String>> data = new HashMap<>();

        for (String str : strs) {

            Anagram anagram = new Anagram(str);
            data.compute(anagram, (key, value) -> {
                if (value == null) {
                    value = new ArrayList<>();
                }
                value.add(str);
                return value;
            });
        }

        List<List<String>> result = new ArrayList<>(strs.length);
        result.addAll(data.values());
        return result;
    }

    public static void main(String[] args) {

        groupAnagrams(new String[]{ "eat", "tea", "tan", "ate", "nat", "bat" });
    }

    private static class Anagram implements CharSequence {

        private final char[] anagram;

        public Anagram(String anagram) {

            this.anagram = anagram.toCharArray();
            Arrays.sort(this.anagram);
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Anagram that = (Anagram) o;
            return Arrays.equals(this.anagram, that.anagram);
        }

        @Override
        public int hashCode() { return Arrays.hashCode(this.anagram);}

        @Override
        public int length() {return anagram.length;}

        @Override
        public char charAt(int index) {return anagram[index];}

        @Override
        public CharSequence subSequence(int start, int end) {return new String(anagram).subSequence(start, end);}

        @Override
        public String toString() {return Arrays.toString(anagram);}
    }
}
