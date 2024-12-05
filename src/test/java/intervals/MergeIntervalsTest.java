package intervals;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MergeIntervalsTest {

  MergeIntervals m = new MergeIntervals();

  @ParameterizedTest
  @MethodSource
  void merge(int[][] input, int[][] expected) {
    assertThat(expected).isDeepEqualTo(m.merge(input));
  }

  static Stream<Arguments> merge() {
    return Stream.of(
        arguments(new int[][] {{1, 3}, {2, 6}, {8, 10}}, new int[][] {{1, 6}, {8, 10}}));
  }
}
