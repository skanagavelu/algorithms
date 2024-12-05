package sorting;

import java.util.ArrayList;
import java.util.Arrays;

/*
 The time complexity of inserting a number into an already sorted collection depends on both:

1. **The data structure used for the collection** (e.g., array, linked list, or tree).
2. **How the insertion operation is performed** (e.g., whether elements need to be shifted or reorganized).

Here are the cases:

### 1. **Sorted Array**
   - **Search for the correct position**: You can use binary search to find the correct position, which takes \(O(\log n)\).
   - **Insert the number**: After finding the position, all elements after the insertion point must be shifted to make space for the new element, which takes \(O(n)\) in the worst case.
   - **Overall time complexity**: \(O(n)\).

### 2. **Sorted Linked List**
   - **Search for the correct position**: You need to traverse the list to find the correct position, which takes \(O(n)\).
   - **Insert the number**: Once the position is found, the insertion itself is \(O(1)\).
   - **Overall time complexity**: \(O(n)\).

### 3. **Balanced Binary Search Tree (BST)**
   - **Search for the correct position**: Takes \(O(\log n)\) in a balanced BST.
   - **Insert the number**: This is done during the traversal for search, so no additional cost.
   - **Overall time complexity**: \(O(\log n)\).

### 4. **Sorted Collection with Self-Balancing Tree (e.g., AVL Tree, Red-Black Tree, or `TreeSet` in Java)**
   - **Search and insert**: Both operations are done in \(O(\log n)\) because the tree remains balanced automatically.

### Summary
| Data Structure               | Time Complexity |
|------------------------------|-----------------|
| Sorted Array                 | \(O(n)\)        |
| Sorted Linked List           | \(O(n)\)        |
| Balanced Binary Search Tree  | \(O(\log n)\)   |
| Self-Balancing Tree          | \(O(\log n)\)   |

For a collection that needs frequent insertions, a self-balancing tree is often the most efficient option.

-----------
-----------


The time complexity of deleting a number from an already sorted collection also depends on the **data structure** being used. Here's a breakdown for common structures:

---

### 1. **Sorted Array**
   - **Search for the element**: Use binary search to locate the element, which takes \(O(\log n)\).
   - **Remove the element**: After finding the element, all elements after it must be shifted to fill the gap, which takes \(O(n)\) in the worst case.
   - **Overall time complexity**: \(O(n)\).

---

### 2. **Sorted Linked List**
   - **Search for the element**: Traverse the list to find the element, which takes \(O(n)\).
   - **Remove the element**: After locating the node, adjust pointers to bypass it, which takes \(O(1)\).
   - **Overall time complexity**: \(O(n)\).

---

### 3. **Balanced Binary Search Tree (BST)**
   - **Search for the element**: Takes \(O(\log n)\) in a balanced BST.
   - **Remove the element**: After locating the node, the complexity depends on the case:
     1. Node with no children or one child: \(O(\log n)\).
     2. Node with two children: \(O(\log n)\) (replace with the in-order predecessor or successor and remove that).
   - **Overall time complexity**: \(O(\log n)\).

---

### 4. **Sorted Collection with Self-Balancing Tree** (e.g., AVL Tree, Red-Black Tree, or `TreeSet` in Java)
   - **Search and remove**: Both operations are \(O(\log n)\) because the tree adjusts itself to maintain balance after deletion.
   - **Overall time complexity**: \(O(\log n)\).

---

### Summary

| Data Structure               | Time Complexity for Deletion |
|------------------------------|------------------------------|
| **Sorted Array**             | \(O(n)\)                     |
| **Sorted Linked List**       | \(O(n)\)                     |
| **Balanced Binary Search Tree** | \(O(\log n)\)             |
| **Self-Balancing Tree**      | \(O(\log n)\)                |

For frequent deletions, a balanced or self-balancing tree is much more efficient than an array or linked list.
 *
 */
public class InsertionSort {
	public static void main(String[] args) {
		int[] integerArray = {5,16, 6, 100, 11, 2, 3, 8, 1, 10, 9, 4, 15, 12, 20, 0};
		 sortIt(integerArray);
		 Integer[] integerObjectArray = new Integer[integerArray.length];
		 int i = 0;
		 for (int value : integerArray) {
			 integerObjectArray[i++] = Integer.valueOf(value);
		 }
		 System.out.println(new ArrayList(Arrays.asList(integerObjectArray)));
	}
	
	public static void sortIt(int[] integerArray) {
		for(int i = 2; i < integerArray.length; ++i) {
			for(int j = i - 1; (j >= 0 && integerArray[j + 1] < integerArray[j]); --j) {
				    //SWAP
					int temp = integerArray[j + 1];
					integerArray[j + 1] = integerArray[j];
					integerArray[j] = temp;
			}
		}
	}
	
	public static void sortItInReverse (int[] integerArray) {
		for(int i = 2; i < integerArray.length; ++i) {
			for(int j = i - 1; (j > 0 && integerArray[j-1] < integerArray[j]); --j) {
				    //SWAP
					int temp = integerArray[j-1];
					integerArray[j-1] = integerArray[j];
					integerArray[j] = temp;
			}
		}
	}
	
	/**
	 * Points to be noted
	 * 
	 * 1. I should starts from 2
	 * 2. I should not be used at second loop since decrement is happening.
	 * 2. Below logic 
	 * 
	 * 		for(int j = i - 1; j > 0; --j,  --i) {
				if(integerArray[i] < integerArray[j]) {
					int temp = integerArray[i];
					integerArray[i] = integerArray[j];
					integerArray[j] = temp;
				} else {
					break;
				}
			}
	 * 		
	 *	Should be like 
	 *      for(int j = i - 1; (j > 0 && integerArray[i] < integerArray[j]); --j,  --i) {
					int temp = integerArray[i];
					integerArray[i] = integerArray[j];
					integerArray[j] = temp;
			}	
	 * 
	 */
}
