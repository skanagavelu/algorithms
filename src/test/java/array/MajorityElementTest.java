package array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MajorityElementTest {

  MajorityElement majorityElement = new MajorityElement();

  @Test
  public void testMajorityElementWhenEmptyInput() {
    assertEquals(0, majorityElement.majorityElement(new int[]{}));
  }

  @Test
  public void testMajorityElementWhenSingleInput() {
    assertEquals(1, majorityElement.majorityElement(new int[]{1}));
  }

  @Test
  public void testMajorityElement() {
    assertEquals(1, majorityElement.majorityElement(new int[]{1,2,2,1,1}));
  }
}
