package main.java.algo.datastructures.binarytree;

public class Trees_TO_DO {
	
	//12. countTrees() Solution (Java)

	/** 
	 For the key values 1...numKeys, how many structurally unique 
	 binary search trees are possible that store those keys?
	 Strategy: consider that each value could be the root. 
	 Recursively find the size of the left and right subtrees. 
	*/ 
	
	// The number of possible tree = (2 power of n) - n.
	// A tree contain three node. So if n=3,the possible number of trees = 8 - 3 = 5.
	public static int countTrees(int numKeys) { 
	  if (numKeys <=1) { 
	    return(1); 
	  } 
	  else { 
	    // there will be one value at the root, with whatever remains 
	    // on the left and right each forming their own subtrees. 
	    // Iterate through all the values that could be the root... 
	    int sum = 0; 
	    int left, right, root;

	    for (root=1; root<=numKeys; root++) { 
	      left = countTrees(root-1); 
	      right = countTrees(numKeys - root);

	      // number of possible trees with this root == left*right 
	      sum += left*right; 
	    }

	    return(sum); 
	  } 
	} 
	 

	//13. isBST1() Solution (Java)

	/** 
	 Tests if a tree meets the conditions to be a 
	 binary search tree (BST). 
	*/ 
	//public boolean isBST() { 
	  //return(isBST(root)); 
	//}
	/** 
	 Recursive helper -- checks if a tree is a BST 
	 using minValue() and maxValue() (not efficient). 
	*/ 
	private boolean isBST(Node node) { 
	  if (node==null) return(true);

	  // do the subtrees contain values that do not 
	  // agree with the node? 
	  
	  //uncomment this - this is corect implementation
	  //if (node.left!=null && findMax(node.left) > node.data) return(false); 
	  //if (node.right!=null && findMin(node.right) <= node.data) return(false);

	  // check that the subtrees themselves are ok 
	 return( isBST(node.left) && isBST(node.right) ); 
	} 
	  
	//right most in right subtree
	private Node findMax(Node n){
		//to-do
		return null;
	}
	 
	//left most in left subtreee
	private Node findMin(Node n){
		//to-do
		return null;
	}

	//14. isBST2() Solution (Java)

	/** 
	 Tests if a tree meets the conditions to be a 
	 binary search tree (BST). Uses the efficient 
	 recursive helper. 
	*/ 
	public boolean isBST2() { 
	Node root = new BinaryTrees().getRoot();
	 return( isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE) ); 
	}
	/** 
	  Efficient BST helper -- Given a node, and min and max values, 
	  recurs down the tree to verify that it is a BST, and that all 
	  its nodes are within the min..max range. Works in O(n) time -- 
	  visits each node only once. 
	*/ 
	private boolean isBST2(Node node, int min, int max) { 
	  if (node==null) { 
	    return(true); 
	  } 
	  else { 
	   // left should be in range  min...node.data 
	    boolean leftOk = isBST2(node.left, min, Integer.parseInt(node.data.toString()));

	    // if the left is not ok, bail out 
	    if (!leftOk) return(false);

	    // right should be in range node.data+1..max 
	    boolean rightOk = isBST2(node.right, Integer.parseInt(node.data.toString())+1, max);

	    return(rightOk); 
	  } 
	} 
	  
	  
	 

}
