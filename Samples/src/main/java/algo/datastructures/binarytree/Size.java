package main.java.algo.datastructures.binarytree;

/*Given a binary tree, count the number of nodes in the tree.*/


/*
 **********************************************
                    5

	           /           \
	
	         2               8
	
	       /   \           /   \
	
	     1       4       7       9
	
	   / \      /       /
	
	0   1.5     3       6

**********************************************
*/
public class Size {
	
	public int size() {
		//say we have a binary tree and we know its root.
		Node root = new BinaryTrees().getRoot();
		return size(root);
	}

	//At every node, the size is sum of left-subtree size + node itself + right-subtree size
	public int size(Node node) {
		//at leaft node, node.left or node.right is not there - so size(node.left) or size(node.right) is zero
		if(node == null)
			return 0;
		else {
			return size(node.left) + 1 + size(node.right);
		}
	}
}
