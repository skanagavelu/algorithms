package tree;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class BTNode<T extends Comparable<T>> {
	public static  BTNodeComparator compator = new BTNodeComparator();
	
	public BTNode<T> left;
	public BTNode<T> right;
	public T data;
	
	public BTNode() {
		
	}
	
	public BTNode(T data) {
		super();
		this.data = data;
	}

	@Override
	public String toString() {
		
		return this.data.toString();
	}
	public static void main(String[] args) {
		BTNode<String> rt = new BTNode<String>();
//		rt.data = "5";
//		rt.left = new BTNode<String>();
//		rt.left.data = "10";
//		rt.right = new BTNode<String>();
//		rt.right.data = "20";
		
		String[] dataArray = {"1","2","3","4","#","#","5","#","#","6","#","#","7","8","9","10","#","#","11","#","#","#","#"};
		rt = buildBTWithPreOrder(dataArray, new AtomicInteger(0));
		BTDisplay.printTreeNode(rt);
		
	}
	


	public static BTNode<String> buildBTWithPreOrder(String[] dataArray, AtomicInteger i){
		if(i.get() > dataArray.length || dataArray[i.get()].equals("#")) {
			return null;
		}
		BTNode<String> node = new BTNode<String>(dataArray[i.get()]);
		i.incrementAndGet();
		node.left = buildBTWithPreOrder(dataArray, i);
		i.incrementAndGet();
		node.right = buildBTWithPreOrder(dataArray, i);
		return node;
	}
	
	//Since binary tree is is unbalanced and not sorted, we have to lookup on all the nodes.
	//The search is n here on worst case not log(n)
	//The search is log(n) in BST
	public static BTNode<String> findBTNodeForData(BTNode<String> root, String data){
        if(root == null || root.data.equals(data)) {
        	return root;
        }
        BTNode<String> foundNode =  findBTNodeForData(root.left, data);
        if(foundNode == null) {
        	foundNode = findBTNodeForData(root.right, data);
        }
		return foundNode;
	}
	
	public static void buildBTWithInOrder(Object[] dataArray){
//		BTNode<Object> rt = new BTNode<Object>(dataArray[0]);
		
	}
	

	
	private static <T extends Comparable<T>> BTNode<T> buildBTWithInOrder(T data, BTNode<T> parent, BTNode<T> child, boolean isLeft){
		if(child == null) {
			BTNode<T> node = new BTNode<T>(data);
			if(isLeft) {
				parent.left = node;
			} else {
				parent.right = node;
			}
			return node;
		}
		
		return null;
//		if(parent.data < data) {
//			buildBTWithInOrder(data, parent, parent.left, true);
//		}
//		if(parent.data > data) {
//			buildBTWithInOrder(data, parent, parent.left, true);
//		}
	}
	
	static class BTNodeComparator<T extends Comparable<T>> implements Comparator<BTNode<T>>{

		@Override
		public int compare(BTNode<T> o1, BTNode<T> o2) {
			return o1.data.compareTo(o2.data);
		}
		
	}
}
