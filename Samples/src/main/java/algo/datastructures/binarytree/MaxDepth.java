package main.java.algo.datastructures.binarytree;

import java.util.Stack;

/* *
 * Given a binary tree, compute its "maxDepth".
 * The number of nodes along the longest path from the root node down to the farthest leaf node. 
 * The maxDepth of the empty tree is 0 
 * */


/*
 **********************************************
                    5

	           /           \
	
	         2               8
	
	       /   \           /   \
	
	     1       4       7       9
	
	   / \      /       /
	
	0   1.5     3       6
                 
                 \
                 
                  3.1
                  
                     \
                     
                      3.2
                     
                         \
                    
                           3.5
                           
**********************************************
*/


public class MaxDepth {
	
	public int maxDepth() {
		//say we have a binary tree and we know its root.
		Node root = new BinaryTrees().getRoot();
		return maxDepth(root);
	}
	
	public int maxDepth(Node node) {
		if (node == null)
			return 0;
		//starting from the root, at every node we want to take into consideration that node which has max depth
		//with next 2 lines of recursion - it'll eventually hit the bottom most leaf node
		//then from there start coming towards up, adding 1 to the level of node.
		//at every sub-tree's root node - depth of left vs right is compared and the max one is picked.
		int leftSubtreeMaxDepth = maxDepth(node.left);
		int rightSubtreeMaxDepth = maxDepth(node.right);
		return (Math.max(leftSubtreeMaxDepth, rightSubtreeMaxDepth)) + 1;
	}
	
	
	// Anything that can be done via recursion, can also be done visa using stacks.
	// Here we modified a the Node data-structure a bit to store the depth of every node.
	
	// Same approach can be used to Find if path has sum problem, here store the 
	// sum upto the node with additional param in Node data-structure
	
	public int maxDepthWithoutRecursion (NodeWithDepth root) {
		
		root.depth = 1;
		
		Stack<NodeWithDepth> stack = new Stack<NodeWithDepth>();
		stack.push(root);
		
		NodeWithDepth current = null;
		int depth = 0;
		int maxDepth = 0;
		
		while(!stack.isEmpty()) {
			
			current = stack.pop();
			depth = current.depth;
			
			maxDepth = Math.max(depth, maxDepth);
			
			if(current.left != null) {
				current.left.depth = depth +1;
				stack.push(current.left);
			}
			if(current.right != null) {
				current.right.depth = depth +1;
				stack.push(current.right);
			}	
		}
		return maxDepth;	
	}

	class NodeWithDepth {
		Object node = null;
		NodeWithDepth left = null;
		NodeWithDepth right = null;
		int depth = 0;
	}

	

}


/*NOTES - minimum depth
 * to find min, the same logic will be used, but instead we'll pick the min 
 * from leftSubtreeMaxDepth, rightSubtreeMaxDepth instead of max. 
 * it will be Math.min(leftSubtreeMaxDepth, rightSubtreeMaxDepth)) + 1;
 * */
