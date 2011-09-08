package main.java.algo.datastructures.binarytree;

import java.util.Stack;


/************************************************************
 * Change a tree so that the roles of the left and right pointers are swapped at every node.
 
 So the tree... 
 
       4 
      / \ 
     2   5 
    / \ 
   1   3

 is changed to... 
 
       4 
      / \ 
     5   2 
        / \ 
       3   1
       
************************************************************/

public class MirrorOfTree {

	public void mirror() {
		// say we have a binary tree and we know its root.
		Node root = new BinaryTrees().getRoot();
		mirror(root);

	}

	public void mirror(Node node) {
		if (node == null)
			return;

		//logic is simple
		// recursively traverse.. and coming bottom to up, swap the pointers to the left right childs.
		mirror(node.left);
		mirror(node.right);
		
		Node tmp = node.left;
		node.left = node.right;
		node.left = tmp;

	}
	
	
	/* ******************************************************
	 
	 Mirror a Tree - without Recursion
	 
	 Note - Anything that can be done via recursion, can also be done via stacks.
	 
	      1
	  2       3
	4   5   6   7
	
	
	stack: 1
	pop 1: swap 2 and 3. Push 2 and 3 into the stack
	
	stack: 2 3
	pop 2: swap 4 and 5. Push 4 and 5 into the stack
	
	stack: 4 5 3
	pop 4: no children return
	
	stack: 5 3
	pop 5: no children return
	
	stack: 3
	pop 3: swap 6 and 7. Push 6 and 7 into the stack
	
	stack: 6 7
	pop 6: no children return
	
	stack: 7
	pop 7: no children return
	
	
	
	Output -->
	
	      1
	  3      2
	7   6  5   4
	
 	****************************************************** */
	
	public void mirrorTreeWithOutRecursion(Node root) {

		Stack<Node> stack = new Stack<Node>();

		stack.push(root);

		Node current = null;
		Node temp = null;

		while (!stack.isEmpty()) {
			current = stack.pop();
			temp = current.left;
			current.left = current.right;
			current.right = temp;

			if (current.left != null)
				stack.push(current.left);
			if (current.right != null)
				stack.push(current.right);
		}
		return;
	}

}
