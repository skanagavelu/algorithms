package hashmap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class IsomorphicStringsTest {

  IsomorphicStrings isomorphicStrings = new IsomorphicStrings();

  @ParameterizedTest
  @MethodSource
  void isIsomorphic(String a, String b, boolean result) {
    assertEquals(result, isomorphicStrings.isIsomorphic(a, b));
  }

  static Stream<Arguments> isIsomorphic() {
    return Stream.of(
        arguments("egg", "add", true),
        arguments("badc", "baba", false),
        arguments("paper", "title", true),
        arguments("foo", "bar", false));
  }
}
