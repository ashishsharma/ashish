package main.java.algo.datastructures.binarytree;

/*
 builds the following little 1-2-3 binary search tree...
 
    2 
   / \ 
  1   3
  
  */

public class BuildTree {

	public void buildUsingThreePointers() {
		Node root = new Node(2);
		Node left = new Node(1);
		Node right = new Node(3);

		root.left = left;
		root.right = right;
	}

	public void buildUsingOnlyOnePointer() {
		Node root = new Node(2);
		root.left = new Node(1);
		root.right = new Node(3);
	}

	public void buildUsingInsert() {
		Node root = null;
		BinaryTrees bt = new BinaryTrees();
		try {
			// in this insert function, what ever we pass a node that has to be
			// root (or root of subtree) under which 2 has to be inserted. If
			// root is null - root itself gets value 2 and gets created.
			bt.insert(root, 2);
			bt.insert(root, 1);
			bt.insert(root, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
