package hashmap;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
//        Below returns only the sorted string, not the original
//        Map<String, List<String>> l = Arrays.stream(strs).map(s -> s.toCharArray()).map(s -> {Arrays.sort(s); return s; }).map(String::new).collect(Collectors.groupingBy(s -> s));
        
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String original = new String(str);
//             map.putIfAbsent(str.chars(), ArrayList::new); //WARN requires created value not supplier
            char[] sorted = str.toCharArray();
            Arrays.sort(sorted);  //WARN 'char[] sorted' cannot be used as key since equals is not override
            String sortedString = new String(sorted);
            map.computeIfAbsent(sortedString, k -> new ArrayList<>() );
            map.get(sortedString).add(original);
        }
//        return map.values().stream().toList(); // Below is better performing
        return new ArrayList<>(map.values());
    }
}
