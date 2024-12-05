package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RotateArrayTest {

  RotateArray rotateArray = new RotateArray();

  @Test
  void rotateWithEmptyArrayReturnsEmptyArray() {
    int[] input = {};
    assertArrayEquals(input, rotateArray.rotate(input, 10));
  }

  @Test
  void rotateWithSingleElementArrayReturnsSame() {
    int[] input = {1};
    assertArrayEquals(input, rotateArray.rotate(input, 10));
  }

  @Test
  void rotateMoreTimesThanSizeOfArray() {
    int[] input = {1,2,3,4,5,6,7};
    int[] output = {5,6,7,1,2,3,4};
    assertArrayEquals(output, rotateArray.rotate(input, 10));
  }

  @Test
  void rotate() {
    int[] input = {1,2,3,4,5,6,7};
    int[] output = {5,6,7,1,2,3,4};
    assertArrayEquals(output, rotateArray.rotate(input, 3));
  }

}