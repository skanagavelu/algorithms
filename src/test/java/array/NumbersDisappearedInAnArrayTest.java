package array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumbersDisappearedInAnArrayTest {

  NumbersDisappearedInAnArray numbersDisappearedInAnArray = new NumbersDisappearedInAnArray();

  @Test
  void testFindDisappearedNumbers() {
    List<Integer> result = numbersDisappearedInAnArray.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1});
    assertThat(result).containsExactlyInAnyOrderElementsOf(List.of(5, 6));
  }

  // 98442 54051
}
