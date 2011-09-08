package main.java.algo.datastructures.binarytree;

public class SameTrees {

	/*
	 * Given two binary trees, return true if they are structurally identical -- 
	 * they are made of nodes with the same values arranged in the same way.
	 */

	public boolean sameTrees (Node rootOfTreeOne, Node rootOfTreeTwo) {
		
		// 1. both empty -> true
		if (rootOfTreeOne == null && rootOfTreeTwo == null)
			return (true);

		// 2. both non-empty -> compare them
		else if (rootOfTreeOne != null && rootOfTreeTwo != null) {
			return (rootOfTreeOne.data == rootOfTreeTwo.data
					&& sameTrees(rootOfTreeOne.left, rootOfTreeTwo.left) 
					&& sameTrees(rootOfTreeOne.right, rootOfTreeTwo.right));
		}
		// 3. one empty, one not -> false
		return (false);
	}

}
