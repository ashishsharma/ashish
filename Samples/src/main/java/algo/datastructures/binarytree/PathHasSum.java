package main.java.algo.datastructures.binarytree;

/*
 * We'll define a "root-to-leaf path" to be a sequence of nodes in a tree starting with 
 * the root node and proceeding downward to a leaf (a node with no children). 
 * We'll say that an empty tree contains no root-to-leaf paths. So for example, 
 * the following tree has exactly four root-to-leaf paths:
 * 
              5 
             / \ 
            4   8 
           /   / \ 
          11  13  4 
         /  \      \ 
        7    2      1

Root-to-leaf paths: 
   path 1: 5 4 11 7 
   path 2: 5 4 11 2 
   path 3: 5 8 13 
   path 4: 5 8 4 1

* For this problem, we will be concerned with the sum of the values of such a path -- 
* for example, the sum of the values on the 5-4-11-7 path is 5 + 4 + 11 + 7 = 27.
* Given a binary tree and a sum, return true if the tree has a root-to-leaf path 
* such that adding up all the values along the path equals the given sum. Return false 
* if no such path can be found.
*  
*/

public class PathHasSum {
	
	public boolean pathHasSum(int sumToBeFound) {
		//say we have a binary tree and we know its root.
		Node root = new BinaryTrees().getRoot();
		return pathHasSum(root, sumToBeFound);
	}

	/*
	 * Logic : 
	 * 
	 * Subtract the node value from the sum when recurring down, 
	 * and check to see if the sum is 0 when you run out of tree.
	 */
	private boolean pathHasSum(Node node, int sum) {
		//node==null means  it is the last node of path. 
		//at that point if the subtracted sum is 0, that means that 
		//path was the one with the required sum.
		if(node == null) {
			if(sum == 0)
				return true;
			return false;
		}
		int subSum = sum - Integer.parseInt( node.data.toString() );
		
		//we keep on doing subtractions in sum as we move down.
		//at every node, the sum gets zero with either the left or right "subtree",
		//it means that left or right subtree path is the one with required sum
		
		boolean leftSubtreeHasSum = pathHasSum(node.left, subSum);

		boolean rightSubTreeHasSum = false;
		
		if (!leftSubtreeHasSum)
			rightSubTreeHasSum = pathHasSum(node.right, subSum);

		return (leftSubtreeHasSum || rightSubTreeHasSum);
			
	}

	// Without Recusrion: As implemented in MaxDepth.maxDepthWithoutRecursion()
	// Same approach can be used to Find if path has sum problem, here store the 
	// sum upto the node with additional param in Node data-structure


	// If problem says that the path does not necessarily start at root, then do recursive 
	// setps first and then calculate the sum. It will be then bottom up approach.
	
	// Note - if objects are passed across recusion, same object gets modified. 
	// If you want to retain the object state with respect to every recusrion, create a new object 
	// in every recursive step.
	
}

