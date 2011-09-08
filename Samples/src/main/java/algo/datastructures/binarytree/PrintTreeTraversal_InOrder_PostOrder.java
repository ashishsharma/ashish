package main.java.algo.datastructures.binarytree;

/*
 * Given a binary search tree (aka an "ordered binary tree"), 
 * iterate over the nodes to print them out in increasing order. 
 * 
 * So the tree...
       4 
      / \ 
     2   5 
    / \ 
   1   3
  / 
0.5
 
* Produces the output "0.5 1 2 3 4 5". 
* 
* This is known as an "inorder" traversal of the tree.
* 
*/

public class PrintTreeTraversal_InOrder_PostOrder {

	//INORDER
	//For each node, the strategy is: recur left, print the node data, recur right.
	public void printInOrderTravrsal(Node node) {
		
		if (node == null)
			return;
		 
		// left, node itself, right 

		if (node.left != null)
			printInOrderTravrsal(node.left);
		
		System.out.println(node.data);
		
		if (node.right != null)
			printInOrderTravrsal(node.right);

	}
	
	
	/* POST ORDER
	 * Given a binary tree, print out the nodes of the tree according to a
	 * bottom-up "postorder" traversal -- both subtrees of a node are printed
	 * out completely before the node itself is printed, and each left subtree
	 * is printed before the right subtree.
	 */

	//Produces the output "1 3 2 5 4".
	public void printPostorder(Node node) {
		
		if (node == null)
			return;

		// first recur on both subtrees
		printPostorder(node.left);
		printPostorder(node.right);

		// then deal with the node
		System.out.print(node.data);
	}

}
