package hashmap;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i)) || map.containsValue(t.charAt(i))){
                if(map.get(s.charAt(i)) != (Character) t.charAt(i)) { //WARN Don't compare null with char, cast it
                    return false;
                }
            }
            map.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }
}
