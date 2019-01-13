package tree;

import java.util.concurrent.atomic.AtomicInteger;


public class BTNodesCounter {

	public static void main(String[] args) {

		Integer[] dataArray = { 8, 4, 2, 1, null, null, 3, null, null, 6, 5,
				null, null, 7, null, null, 14, 12, 10, 9, null, null, 11, null,
				null, 13, null, null, 16, 15, null, null, 17, null, null };

		// Build Tree from dataArray using PreOrder
		BTNode<Integer> root = BTNode.buildBTWithPreOrder(dataArray,
				new Counter(0));
		System.out.println("Tree built using PreOrder:");
		BTDisplay.printTreeNode(root);


		System.out.println(count(root));
		
		System.out.println(findMax(root));

	}
	
	private static <T extends Comparable<T>> int count(BTNode<T> root) {
		if (root == null) {
			return 0;
		}

		return  1 + count(root.left) + count(root.right);
	}
	
	
	private static <T extends Comparable<T>> BTNode<T>  findMax(BTNode<T> root) {
         
		BTNode<T> max = root;
		if(root.left != null)
		max = root.data.compareTo(findMax(root.left).data) > 0 ? root : findMax(root.left);
		
		if(root.right != null)
		max = max.data.compareTo(findMax(root.right).data) > 0 ?  max : findMax(root.right);

		return  max;
	}
}
