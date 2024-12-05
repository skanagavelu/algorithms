package tree;


/**
 * 104. maximum-depth-of-binary-tree
 */
public class MaxDepth {
    public int maxDepth(BTNode root) {
        if (root == null) {return 0;}
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}


