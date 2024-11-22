package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveElementTest  {

    RemoveElement r = new RemoveElement();
    @Test
    public void testRemoveElement() {
        int[] nums = {3,2,2,3};
        int val = 3;
        assertEquals(r.removeElement(nums, val), 2);
    }

    @Test
    public void testRemoveElementWithSingleElement() {
        int[] nums = {3};
        int val = 3;
        assertEquals(r.removeElement(nums, val), 0);
    }

    @Test
    public void testRemoveElementWithRepeatedSameElement() {
        int[] nums = {3, 3, 3};
        int val = 3;
        assertEquals(r.removeElement(nums, val), 0);
    }

    @Test
    public void testRemoveElementWithTwoElementValueLast() {
        int[] nums = {4, 5};
        int val = 5;
        assertEquals(r.removeElement(nums, val), 1);
    }

    @Test
    public void testRemoveElementWithTwoElementValueFirst() {
        int[] nums = {5, 4};
        int val = 5;
        assertEquals(r.removeElement(nums, val), 1);
    }

    @Test
    public void testRemoveElementWithEmptyArray() {
        int[] nums = {};
        int val = 5;
        assertEquals(r.removeElement(nums, val), 0);
    }
}
