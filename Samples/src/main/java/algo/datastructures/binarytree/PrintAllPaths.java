package main.java.algo.datastructures.binarytree;

/*
Given a binary tree, prints out all of its root-to-leaf 
paths, one per line. 

              5 
             / \ 
            4   8 
           /   / \ 
          11  13  4 
         / \       \ 
        7   2       1
             \
              3
              
Root-to-leaf paths: 
   path 1: 5 4 11 7 
   path 2: 5 4 11 2 3
   path 3: 5 8 13 
   path 4: 5 8 4 1

*/

public class PrintAllPaths {

	public void printPaths() {
		
		Object[] path = new Object[1000];
		//say we have a binary tree and we know its root.
		Node root = new BinaryTrees().getRoot();
		printPaths(root, path, 0);
		
	}
	
	public void printPaths(Node node, Object[] path, int pathLength) {
		if (node == null)
			return;
		
		// whenever moving down the tree and the data is not null, that means
		// that node has to be part of a path. so add it to path.
		
		path[pathLength] = node.data;
		pathLength++;
		
		//when leaf node is reached - that means end of path. so print it and no any more 
		//recursion calls for that node. now handle the recursive calls before this.
		
		if(node.left == null && node.right == null) {
			print(path, pathLength);
		}
		

		// left most node, then start with right nodes, again check for left
		// nodes in right sub-trees.
		
		printPaths(node.left, path, pathLength);
		printPaths(node.right, path, pathLength);
		
		
	}
	
	// we are keeping a track of pathLength coz we have declared an array of
	// 1000 elements, so we want to print only the elements till that node.
	// we could have avoided it if we were using Arraylist instead of an array
	
	public void print (Object[] path, int pathLength) {
		for(int i = 0 ; i < pathLength; i++) {
			System.out.println(path[i]);
		}
	}
	
	// Without Recusrion: As implemented in MaxDepth.maxDepthWithoutRecursion()
	// Same approach can be used here -  store the 
	// path upto the node with additional param in Node data-structure
	
	
}
