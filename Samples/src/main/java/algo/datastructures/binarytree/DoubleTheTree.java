package main.java.algo.datastructures.binarytree;

/*
 * For each node in a binary search tree, create a new duplicate node, 
 * and insert the duplicate as the left child of the original node. 
 * The resulting tree should still be a binary search tree.

 
 So the tree... 
 
    2 
   / \ 
  1   3

 is changed to... 
    
       2 
      / \ 
     2   3 
    /   / 
   1   3 
  / 
 1
 
 */

public class DoubleTheTree {
	
	public void duplicate() {
		// say we have a binary tree and we know its root.
		Node root = new BinaryTrees().getRoot();
		duplicate(root);

	}

	
	// LOGIC IS SIMPLE
	// recursively traverse every element
	// while moving bottom to up, duplicate the node to its left
	public void duplicate(Node node) {

		if(node == null)
			return;
		
		//traverse the trees
		duplicate(node.left);
		duplicate(node.right);
		
		//at every node, be in right or left subtree - duplicate it 
		//we are assuming that less than equal to lies in left subtree
		Node origNode = node;
		node.left = new Node(node.data);
		node.left.left = origNode.left;	
		
	}	

}
