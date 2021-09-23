package trie;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import org.junit.Test;
import static trie.Util.generateRandomString;

public class TrieMapTest extends TestCase {

    @Test
    public void testValidations() {


        TrieMap<String, String> map = new TrieMap<>();
        map.toString();
        map.put("hello", "world");
        assertEquals(map.get("hello"), "world");

        map.put("hello", "replace world");
        assertEquals(map.get("hello"),"replace world");

        map.put("hello_big_hash", "big hash world");
        assertEquals(map.get("hello_big_hash"),"big hash world");

        map.put("he", "small hash world");
        assertEquals(map.get("he"),"small hash world");

        map.put("AaAa", "hashCode-AaAa");
        assertEquals(map.get("AaAa"),"hashCode-AaAa");

        map.put("BBBB", "hashCode-BBBB");
        assertEquals(map.get("BBBB"),"hashCode-BBBB");
        assertEquals(map.get("hello"),"replace world");
    }

    @Test
    public void testValidationWithLargeSet() {

        TrieMap<String, String> map = new TrieMap<>();

        Set<String> tokens = new HashSet<>();
        int size = 50000;
        while (tokens.size() < size) {

            String token = generateRandomString();
            tokens.add(token);
        }

        int i = 0;
        for (String token : tokens) {
            i++;
            map.put(token, token);
            assertEquals(map.get(token), token);
        }
    }
}