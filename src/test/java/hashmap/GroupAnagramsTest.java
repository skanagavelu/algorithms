package hashmap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupAnagramsTest {

  GroupAnagrams groupAnagrams = new GroupAnagrams();

  @Test
  void groupAnagrams() {

    List<List<String>> expected = new ArrayList<>();
    expected.add(List.of("ate", "eat", "tea"));
    expected.add(List.of("bat"));
    expected.add(List.of("nat", "tan"));

    expected.sort(
        Comparator.comparing(
            (List<String> l) -> l.size())); // Explicit cast((List<String> l)) inside lambda

    List<List<String>> result =
        groupAnagrams.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
    result.sort(Comparator.comparing(List::size));
    for (int i = 0; i < expected.size(); i++) {
      Assertions.assertThat(expected.get(i)).containsExactlyInAnyOrderElementsOf(result.get(i));
    }

    //    assertEquals(Map.of("Hello", "World"), Map.of("Hello", "World"));
  }
}
