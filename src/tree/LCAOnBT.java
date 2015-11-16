package tree;

import java.util.concurrent.atomic.AtomicInteger;

public class LCAOnBT {
	public static void main(String[] args) {
		BTNode<String> rt = new BTNode<String>();
		String[] dataArray = {"1","2","3","4",null,null,"5",null,null,"6",null,null,"7","8","9","10",null,null,"11",null,null,null,null};
		rt = BTNode.buildBTWithPreOrder(dataArray, new AtomicInteger(0));
		BTNode<String> one = BTNode.findBTNodeForData(rt, "10");
		BTNode<String> two = BTNode.findBTNodeForData(rt, "5");
		BTDisplay.printTreeNode(rt);
		BTNode<String> lca = findLCAOnBinaryTree(rt, one, two);
		System.out.println(lca);
	}
	
	public static BTNode findLCAOnBinaryTree(BTNode<String> root, BTNode<String> one, BTNode<String> two) {
		if(root == null) {
			return null;
		}
		
		if(one.data.equals(root.data) || two.data.equals(root.data)) {
			return root;
		}
		
		BTNode<String> foundAtLeft = findLCAOnBinaryTree(root.left, one, two);
		
		//Optimization to return the LCA if it is found only from its subtree, then avoid going to the right subtree
		if(foundAtLeft != null && (!foundAtLeft.data.equals(one.data) && !foundAtLeft.data.equals(two.data))) {
			return foundAtLeft;
		}
			
		BTNode<String> foundAtRight = findLCAOnBinaryTree(root.right, one, two);
		
		if(foundAtLeft != null && foundAtRight != null) {
			//Found LCA
			return root;
		}
		else {
			return foundAtLeft != null ? foundAtLeft : foundAtRight;
		}
	}
}
