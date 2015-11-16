package tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This code will ensure holding of chain(links) of nodes from the root to till the level of the tree.
 * So maximum height of the tree is extra nodes in the memory other than tree.
 * 
 * This chain is the parent chain for any node from the top to till its immediate parent.
 * This code will not require any altering of existing BinaryTree (NO flag/parent on all the nodes.
 *  
 * @author ksugumar
 *
 */
public class InOrderTraversalIterative {
	public static void main(String[] args) {
		BTNode<String> rt = new BTNode<String>();
		String[] dataArray = {"1","2","3","4","#","#","5","#","#","6","#","#","7","8","9","10","#","#","11","#","#","#","#"};
		rt = BTNode.buildBTWithPreOrder(dataArray, new AtomicInteger(0));
		BTDisplay.printTreeNode(rt);
		inOrderTravesal(rt);
	}
	
	public static void inOrderTravesal(BTNode<String> root) {
		ParentChain node = new ParentChain(root);
		node.Parent = new ParentChain(null);;
		
		while (root != null) {
			
			if(node.leftVisited && node.rightVisited) {
				ParentChain parentNode = node.Parent;
				node.Parent = null; //Avoid the leak
				node = parentNode;
				root = node.root;
				continue;
			}
			
			//Traverse Left
			if(!node.leftVisited) {
				node.leftVisited = true;
				if (root.left != null) {
					ParentChain local = new ParentChain(root.left); //It is better to use pool to reuse the instances.
					local.Parent = node;
					node = local;
					root = root.left;
					continue;
				}
			} 
			
			System.out.println(root.data); //Visit the node.
			
			//Traverse RIGHT
			if(!node.rightVisited) {
				node.rightVisited = true;
				if (root.right != null) {
					ParentChain local = new ParentChain(root.right); //It is better to use pool to reuse the instances.
					local.Parent = node;
					node = local;
					root = root.right;
					continue;
				}
			}
		}
	}
}

class ParentChain {
	BTNode<String> root;
	ParentChain Parent;
	boolean leftVisited = false;
	boolean rightVisited = false;
	
	public ParentChain(BTNode<String> node) {
		this.root = node; 
	}
	
	@Override
	public String toString() {
		return root.toString();
	}
}