package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/
 * @author kanagavelusugumar
 *
 */
public class IsMirrorOrSymmetric {
	public static void main(String[] args) {
		BTNode<String> rt;
		String[] dataArray = {"1","2","3","4",null,null,"5",null,null,"6",null,null,"7","8","9","10",null,null,"11",null,null,null,null};
		rt = BTNode.buildBTWithPreOrder(dataArray, new Counter(0));
		BTDisplay.printTreeNode(rt);
		isMirror(rt.left, rt.right);
		System.out.println();

	}

	//Level order traversal with iterative approach.
	private static <T extends Comparable<T>> boolean isMirror(BTNode<T> node1, BTNode<T> node2) {
		if(node1 == null && node2 == null) {
			return true;
		}
		if(node1 != null && node2 != null && node1.data.equals(node2.data) ) {
			return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
		}
		
		return false;
	}
	
	//Level order traversal with iterative approach.
	private static <T extends Comparable<T>> boolean isSymmetric(BTNode<T> node) {
		return isMirror(node, node);
	}

}
